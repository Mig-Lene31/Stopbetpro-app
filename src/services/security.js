export function isValidSession(user) {
  return !!user && !!user.email;
}

export function canUnlockEarly(payment) {
  return (
    payment &&
    (payment.status === 'paid' ||
     payment.mock === true)
  );
}
