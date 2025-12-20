let balance = 100;

export function getNextBalance() {
  // simula perda
  balance -= Math.floor(Math.random() * 20);
  if (balance < 0) balance = 0;
  return balance;
}
