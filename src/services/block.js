import Storage from './storage';
import { startVpn } from './vpn';

export async function activateBlock() {
  const alreadyBlocked = await Storage.get('blocked');
  if (alreadyBlocked === true) {
    console.log('[STOPBET] Bloqueio já ativo');
    return;
  }

  console.log('[STOPBET] ATIVANDO BLOQUEIO TOTAL');

  await Storage.set('blocked', true);
  await startVpn();
}
