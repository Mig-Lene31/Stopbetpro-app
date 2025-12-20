import { useEffect } from 'react';
import { registerStrike, isLocked, initLock, getRemainingTime } from '../engine/stopTimeLock';
import { requestBalanceConfirmation, isWaitingConfirmation, confirmBalance } from '../engine/balanceConfirmation';

export function useStopEngine(config = {}) {
  useEffect(() => { initLock(); }, []);

  function checkBalance(balance) {
    if (isLocked()) {
      return { block: true, remaining: getRemainingTime() };
    }

    if (balance <= (config.minBalance ?? 10) && !isWaitingConfirmation()) {
      requestBalanceConfirmation();
      return { confirm: true };
    }

    if (isWaitingConfirmation()) {
      const confirmed = confirmBalance(balance);
      if (confirmed <= (config.minBalance ?? 10)) {
        return registerStrike();
      }
    }

    return { block: false };
  }

  return { checkBalance, isWaitingConfirmation, getRemainingTime };
}
