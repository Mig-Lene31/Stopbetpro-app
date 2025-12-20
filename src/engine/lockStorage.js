import AsyncStorage from '@react-native-async-storage/async-storage';

const KEY = '@StopBetPro:lock';

export async function saveLock(data) {
  try {
    await AsyncStorage.setItem(KEY, JSON.stringify(data));
  } catch (e) {}
}

export async function loadLock() {
  try {
    const raw = await AsyncStorage.getItem(KEY);
    return raw ? JSON.parse(raw) : null;
  } catch (e) {
    return null;
  }
}

export async function clearLock() {
  try {
    await AsyncStorage.removeItem(KEY);
  } catch (e) {}
}
