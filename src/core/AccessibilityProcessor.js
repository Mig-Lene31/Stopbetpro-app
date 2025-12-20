import { parseBalanceFromText } from './BalanceParser';
import { processBalance } from './DecisionEngine';

let lastProcessed = null;

export async function handleAccessibilityText(text) {
  const balance = parseBalanceFromText(text);
  if (balance == null) return;

  if (balance === lastProcessed) return;
  lastProcessed = balance;

  await processBalance(balance);
}
