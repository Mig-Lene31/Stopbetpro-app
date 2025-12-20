export function parseBalanceFromText(text) {
  if (!text || typeof text !== 'string') return null;

  const normalized = text
    .replace(/\./g, '')
    .replace(',', '.')
    .replace(/[^0-9.]/g, ' ');

  const matches = normalized.match(/\d+(\.\d{1,2})?/g);
  if (!matches || matches.length === 0) return null;

  const values = matches
    .map(v => Number(v))
    .filter(v => !isNaN(v) && v > 0 && v < 1000000);

  if (values.length === 0) return null;

  return Math.max(...values);
}
