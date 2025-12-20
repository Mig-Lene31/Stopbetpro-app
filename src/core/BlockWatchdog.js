import { checkBlockStatus } from './BlockController';

let running = false;

export function startBlockWatchdog() {
  if (running) return;
  running = true;

  setInterval(() => {
    checkBlockStatus();
  }, 3000);
}
