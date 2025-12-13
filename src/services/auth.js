import { saveItem, getItem } from './storage';
import { STORAGE_KEYS } from '../config/constants';

export async function registerUser(email, password) {
  const user = {
    id: Date.now().toString(),
    email,
    createdAt: new Date().toISOString()
  };

  await saveItem(STORAGE_KEYS.USER, user);
  return user;
}

export async function loginUser(email, password) {
  const user = await getItem(STORAGE_KEYS.USER);

  if (!user || user.email !== email) {
    throw new Error('Usuário não encontrado');
  }

  return user;
}

export async function logoutUser() {
  await saveItem(STORAGE_KEYS.USER, null);
}
