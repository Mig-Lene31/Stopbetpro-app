import Storage from '../services/storage';
import { showBlockOverlay, hideBlockOverlay } from '../native/blockOverlay';

const BLOCK_DURATION_HOURS = 12;

export async function activateGlobalBlock(reason) {
  const until = Date.now() + BLOCK_DURATION_HOURS * 60 * 60 * 1000;

  await Storage.set('blocked', true);
  await Storage.set('blockUntil', until);
  await Storage.set('blockReason', reason);

  showBlockOverlay();
}

export async function checkBlockStatus() {
  const blocked = await Storage.get('blocked');
  if (!blocked) return false;

  const until = await Storage.get('blockUntil');
  if (!until) return false;

  if (Date.now() >= until) {
    await clearBlock();
    return false;
  }

  showBlockOverlay();
  return true;
}

export async function clearBlock() {
  await Storage.remove('blocked');
  await Storage.remove('blockUntil');
  await Storage.remove('blockReason');

  hideBlockOverlay();
}
