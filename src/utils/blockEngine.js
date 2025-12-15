/**
 * Motor central de bloqueio do StopBet Pro
 * Responsável por decidir quando o bloqueio deve acontecer
 */

export function shouldBlock({
  depositValue,
  stopWinValue,
  stopLossValue,
  currentBalance
}) {
  if (
    depositValue == null ||
    stopWinValue == null ||
    stopLossValue == null ||
    currentBalance == null
  ) {
    return { block: false, reason: null }
  }

  // STOP WIN
  const winLimit = depositValue + stopWinValue
  if (currentBalance >= winLimit) {
    return {
      block: true,
      reason: 'STOP_WIN'
    }
  }

  // STOP LOSS
  const lossLimit = depositValue - stopLossValue
  if (currentBalance <= lossLimit) {
    return {
      block: true,
      reason: 'STOP_LOSS'
    }
  }

  return { block: false, reason: null }
}
