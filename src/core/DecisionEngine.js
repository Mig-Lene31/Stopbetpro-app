import Storage from '../services/storage';
import { shouldBlock } from './DecisionRules';
import { getState, updateState } from './DecisionState';
import {
  startConfirmation,
  confirmBalance,
  isPendingConfirmation,
  getConfirmationReason
} from './ConfirmationState';

import { activateGlobalBlock } from './BlockController';

const STABLE_THRESHOLD = 3;
const DECISION_COOLDOWN_MS = 5000;

export async function processBalance(balance) {
  const state = getState();
  const now = Date.now();

  if (state.blocked) return;

  if (state.lastDecisionAt && now - state.lastDecisionAt < DECISION_COOLDOWN_MS) {
    return;
  }

  if (balance === state.lastBalance) {
    updateState({ stableCount: state.stableCount + 1 });
  } else {
    updateState({ lastBalance: balance, stableCount: 1 });
  }

  if (getState().stableCount < STABLE_THRESHOLD) return;

  // 🔁 FASE DE CONFIRMAÇÃO
  if (isPendingConfirmation()) {
    const ok = confirmBalance(balance);
    if (ok) {
      await activateGlobalBlock(getConfirmationReason());
      updateState({ blocked: true, lastDecisionAt: now });
    }
    return;
  }

  const deposit = await Storage.get('depositAmount');
  const stopWin = await Storage.get('stopWin');
  const stopLoss = await Storage.get('stopLoss');

  const decision = shouldBlock({ balance, deposit, stopWin, stopLoss });
  if (!decision) return;

  // Inicia confirmação ao invés de bloquear direto
  startConfirmation(decision, balance);
}
