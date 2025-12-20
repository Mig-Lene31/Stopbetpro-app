import Storage from './storage';

let running = false;
let lastBalance = null;

async function detectBalance() {
  const base = await Storage.get('depositAmount');
  const delta = await Storage.get('balanceDelta');
  if (base == null) return null;
  return Number(base + (delta || 0));
}

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

const BalanceListener = {
  start: async () => {
    if (running) return;
    running = true;

    const deposit = await Storage.get('depositAmount');
    if (deposit == null) return;

    lastBalance = deposit;

    while (running) {
      const detected = await detectBalance();
      if (detected != null && detected !== lastBalance) {
        lastBalance = detected;
      }
      await sleep(3000);
    }
  },

  stop: () => {
    running = false;
  }
};

export default BalanceListener;
