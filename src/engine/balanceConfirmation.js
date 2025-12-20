let waitingConfirmation = false;
let confirmedBalance = null;

export function requestBalanceConfirmation() {
  waitingConfirmation = true;
  confirmedBalance = null;
}

export function isWaitingConfirmation() {
  return waitingConfirmation;
}

export function confirmBalance(value) {
  confirmedBalance = value;
  waitingConfirmation = false;
  return confirmedBalance;
}

export function resetConfirmation() {
  waitingConfirmation = false;
  confirmedBalance = null;
}
