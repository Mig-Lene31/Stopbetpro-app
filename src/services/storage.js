import AsyncStorage from '@react-native-async-storage/async-storage';

const STORAGE_KEY = 'StopBetProStorage';

const getStorage = async () => {
  const data = await AsyncStorage.getItem(STORAGE_KEY);
  return data ? JSON.parse(data) : {};
};

const saveStorage = async (data) => {
  await AsyncStorage.setItem(STORAGE_KEY, JSON.stringify(data));
};

export const set = async (key, value) => {
  const storage = await getStorage();
  storage[key] = value;
  await saveStorage(storage);
};

export const get = async (key) => {
  const storage = await getStorage();
  return storage[key];
};

export const remove = async (key) => {
  const storage = await getStorage();
  delete storage[key];
  await saveStorage(storage);
};

export const setDepositAmount = async (amount) => {
  await set('depositAmount', amount);
};

export const getDepositAmount = async () => {
  return await get('depositAmount');
};
