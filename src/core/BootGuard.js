import Storage from '../services/storage';
import { showBlockOverlay } from '../native/blockOverlay';

export async function runBootGuard() {
  const blocked = await Storage.get('blocked');
  const until = await Storage.get('blockUntil');

  if (!blocked || !until) return;

  if (Date.now() < until) {
    showBlockOverlay();
  } else {
    await Storage.remove('blocked');
    await Storage.remove('blockUntil');
    await Storage.remove('blockReason');
  }
}
