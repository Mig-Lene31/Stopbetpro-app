import Storage from './storage';

let running = false;
let lastBalance = null;
let callbackFn = null;

/**
 * BalanceListener.start(cb)
 * cb(balance:number)
 */
const BalanceListener = {
  start: async (callback) => {
    if (running) {
      console.log('[STOPBET] BalanceListener já ativo');
      return;
    }

    running = true;
    callbackFn = callback;

    console.log('[STOPBET] BalanceListener iniciado');

    const deposit = await Storage.get('depositAmount');

    if (deposit == null) {
      console.warn('[STOPBET] Depósito não definido');
      return;
    }

    lastBalance = deposit;

    // Simulação de escuta contínua
    startPolling();
  },

  stop: () => {
    running = false;
    callbackFn = null;
    console.log('[STOPBET] BalanceListener parado');
  },
};

async function startPolling() {
  while (running) {
    try {
      const detectedBalance = await detectBalance();

      if (detectedBalance != null && detectedBalance !== lastBalance) {
        lastBalance = detectedBalance;

        if (callbackFn) {
          callbackFn(detectedBalance);
        }
      }
    } catch (e) {
      console.warn('[STOPBET] Erro ao detectar saldo', e);
    }

    await sleep(3000); // a cada 3s
  }
}

/**
 * Aqui entra a inteligência real depois:
 * - Acessibilidade
 * - VPN
 * - WebView
 *
 * Por agora: ancora no depósito + variação simulada
 */
async function detectBalance() {
  const base = await Storage.get('depositAmount');
  const delta = await Storage.get('balanceDelta');

  if (base == null) return null;

  const balance = base + (delta || 0);
  return Number(balance);
}

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

export default BalanceListener;
