import Storage from '../services/storage';
import { clearBlock } from './BlockController';

export async function unlockWithPayment() {
  const blocked = await Storage.get('blocked');
  if (!blocked) return false;

  await clearBlock();

  await Storage.set('lastPaidUnlock', Date.now());
  return true;
}
