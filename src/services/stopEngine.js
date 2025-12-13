export function shouldBlock(balance, stopWin, stopLoss) {
  if (typeof balance !== 'number') return false;

  if (stopWin != null && balance >= stopWin) {
    console.log('🟢 STOP WIN atingido');
    return true;
  }

  if (stopLoss != null && balance <= stopLoss) {
    console.log('🔴 STOP LOSS atingido');
    return true;
  }

  return false;
}
