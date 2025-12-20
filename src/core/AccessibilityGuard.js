import { isBlockedDomain } from './DomainBlockEngine';
import { processBalance } from './DecisionEngine';
import { isAppActive } from './AppActivationState';

export async function handleAccessibilityEvent(text) {
  if (!isAppActive()) return;
  if (!text) return;

  if (isBlockedDomain(text)) {
    console.log('[STOPBET] Site de aposta detectado');
  }

  await processBalance(null);
}
