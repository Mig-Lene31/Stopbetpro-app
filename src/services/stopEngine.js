import Storage from './storage';

let engineRunning = false;

export async function startStopEngine() {
  if (engineRunning) return;
  engineRunning = true;
  console.log('[STOPBET] StopEngine iniciado');
}

export async function evaluateBalance(currentBalance) {
  const deposit = await Storage.get('depositAmount');
  const stopWin = await Storage.get('stopWin');
  const stopLoss = await Storage.get('stopLoss');

  if (deposit == null || stopWin == null || stopLoss == null) {
    console.warn('[STOPBET] Configuração incompleta');
    return;
  }

  const stopWinTarget = deposit + stopWin;
  const stopLossTarget = deposit - stopLoss;

  console.log('[STOPBET] Saldo:', currentBalance);
  console.log('[STOPBET] StopWin alvo:', stopWinTarget);
  console.log('[STOPBET] StopLoss alvo:', stopLossTarget);

  if (currentBalance >= stopWinTarget) {
    await markPendingBlock('STOP_WIN', currentBalance);
  }

  if (currentBalance <= stopLossTarget) {
    await markPendingBlock('STOP_LOSS', currentBalance);
  }
}

async function markPendingBlock(reason, balance) {
  await Storage.set('pendingBlock', true);
  await Storage.set('blockReason', reason);
  await Storage.set('lastDetectedBalance', balance);
  await Storage.set('navigateToConfirm', true);

  console.log('[STOPBET] Bloqueio pendente:', reason);
}
