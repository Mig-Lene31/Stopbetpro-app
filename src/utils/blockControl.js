import AsyncStorage from '@react-native-async-storage/async-storage';

const BLOCK_STARTED_AT = 'BLOCK_STARTED_AT';
const BLOCK_DURATION = 'BLOCK_DURATION';

// Inicia o bloqueio por um tempo definido (em ms)
export async function startBlock(durationMs) {
  const now = Date.now();
  await AsyncStorage.setItem(BLOCK_STARTED_AT, now.toString());
  await AsyncStorage.setItem(BLOCK_DURATION, durationMs.toString());
}

// Verifica se o bloqueio ainda está ativo
export async function isBlockActive() {
  const startedAt = await AsyncStorage.getItem(BLOCK_STARTED_AT);
  const duration = await AsyncStorage.getItem(BLOCK_DURATION);

  if (!startedAt || !duration) return false;

  const endTime =
    parseInt(startedAt, 10) + parseInt(duration, 10);

  return Date.now() < endTime;
}

// Limpa o bloqueio (usado após o tempo acabar ou desbloqueio pago)
export async function clearBlock() {
  await AsyncStorage.removeItem(BLOCK_STARTED_AT);
  await AsyncStorage.removeItem(BLOCK_DURATION);
}
