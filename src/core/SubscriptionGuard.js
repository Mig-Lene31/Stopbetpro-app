import Storage from '../services/storage';

const SUB_KEY = 'subscription';

export async function hasValidSubscription() {
  const sub = await Storage.get(SUB_KEY);
  if (!sub) return false;

  const now = Date.now();
  return sub.expiresAt && sub.expiresAt > now;
}

export async function activateSubscription(days = 30) {
  const expiresAt = Date.now() + days * 24 * 60 * 60 * 1000;
  await Storage.set(SUB_KEY, { expiresAt });
}

export async function clearSubscription() {
  await Storage.remove(SUB_KEY);
}
