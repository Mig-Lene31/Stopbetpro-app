import Storage from './storage';
import { evaluateBalance, startStopEngine } from './stopEngine';
import { activateBlock } from './block';
import BalanceListener from './balanceListener';

let systemRunning = false;

export async function startBlockSystem() {
  if (systemRunning) {
    console.log('[STOPBET] BlockSystem já está ativo');
    return;
  }

  systemRunning = true;

  console.log('[STOPBET] Sistema de bloqueio iniciado');

  await startStopEngine();

  BalanceListener.start(async (currentBalance) => {
    console.log('[STOPBET] Saldo detectado:', currentBalance);

    await evaluateBalance(currentBalance, 'LIVE');

    const pending = await Storage.get('pendingBlock');

    if (pending === true) {
      console.log('[STOPBET] Bloqueio pendente detectado');
      await handleConfirmation(currentBalance);
    }
  });
}

async function handleConfirmation(currentBalance) {
  console.log('[STOPBET] Confirmando valores antes do bloqueio');

  await evaluateBalance(currentBalance, 'CONFIRMATION');

  const pending = await Storage.get('pendingBlock');

  if (pending === false) {
    console.log('[STOPBET] Bloqueio confirmado');
    await activateBlock();
  }
}
