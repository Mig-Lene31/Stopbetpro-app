import { saveLock, loadLock, clearLock } from './lockStorage';

let lockUntil = 0;
let strikes = 0;
let initialized = false;

const LEVELS = [
  30 * 60 * 1000,
  60 * 60 * 1000,
  6 * 60 * 60 * 1000,
  24 * 60 * 60 * 1000
];

export async function initLock() {
  if (initialized) return;
  const data = await loadLock();
  if (data?.lockUntil && data.lockUntil > Date.now()) {
    lockUntil = data.lockUntil;
    strikes = data.strikes || 0;
  }
  initialized = true;
}

export function registerStrike() {
  const now = Date.now();
  const duration = LEVELS[Math.min(strikes, LEVELS.length - 1)];
  lockUntil = now + duration;
  strikes++;
  saveLock({ lockUntil, strikes });
  return { block: true, until: lockUntil, strikes };
}

export function isLocked() {
  return Date.now() < lockUntil;
}

export function getRemainingTime() {
  return Math.max(0, lockUntil - Date.now());
}

export function resetLock() {
  lockUntil = 0;
  strikes = 0;
  clearLock();
}
