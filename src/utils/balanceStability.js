let lastValue = null;
let sameCount = 0;
let lastTimestamp = 0;

export function isStableBalance(value) {
  const now = Date.now();

  // Reset se passou muito tempo
  if (now - lastTimestamp > 3000) {
    lastValue = null;
    sameCount = 0;
  }

  lastTimestamp = now;

  if (lastValue === null) {
    lastValue = value;
    sameCount = 1;
    return false;
  }

  // Variação absurda → lixo
  if (Math.abs(value - lastValue) > 5000) {
    lastValue = value;
    sameCount = 1;
    return false;
  }

  if (value === lastValue) {
    sameCount++;
  } else {
    lastValue = value;
    sameCount = 1;
  }

  // Só aceita após repetir 2x
  return sameCount >= 2;
}

export function resetStability() {
  lastValue = null;
  sameCount = 0;
  lastTimestamp = 0;
}
