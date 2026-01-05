let confirmation = {
  pending: false,
  reason: null,
  firstBalance: null,
  confirmed: false
};

export function startConfirmation(reason, balance) {
  confirmation = {
    pending: true,
    reason,
    firstBalance: balance,
    confirmed: false
  };
}

export function confirmBalance(balance) {
  if (!confirmation.pending) return false;

  if (balance === confirmation.firstBalance) {
    confirmation.confirmed = true;
    return true;
  }

  resetConfirmation();
  return false;
}

export function isPendingConfirmation() {
  return confirmation.pending;
}

export function resetConfirmation() {
  confirmation = {
    pending: false,
    reason: null,
    firstBalance: null,
    confirmed: false
  };
}

export function getConfirmationReason() {
  return confirmation.reason;
}
