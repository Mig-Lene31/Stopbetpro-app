export function parsePossibleBalance(text) {
  if (!text) return null;

  const clean = text
    .replace(/\s+/g, '')
    .replace('R$', '')
    .replace(',', '.');

  // só números
  if (!/^\d+(\.\d{1,2})?$/.test(clean)) return null;

  const value = parseFloat(clean);

  // filtros de sanidade
  if (value <= 0) return null;
  if (value > 100000) return null; // evita bankroll falso

  return value;
}
