export function shouldBlock({
  balance,
  deposit,
  stopWin,
  stopLoss
}) {
  if (
    balance == null ||
    deposit == null ||
    stopWin == null ||
    stopLoss == null
  ) {
    return null;
  }

  const winTarget = deposit + stopWin;
  const lossTarget = deposit - stopLoss;

  if (balance >= winTarget) return 'STOP_WIN';
  if (balance <= lossTarget) return 'STOP_LOSS';

  return null;
}
