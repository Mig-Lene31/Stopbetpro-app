import Storage from '../services/storage';

export const CONFIG_KEYS = {
  DEPOSIT: 'config_deposit',
  STOP_WIN: 'config_stop_win',
  STOP_LOSS: 'config_stop_loss',
  STOP_TIME: 'config_stop_time',
  ENGINE: 'engine_enabled'
};

export async function saveConfig(key, value) {
  await Storage.set(key, value);
}

export async function getConfig(key) {
  return await Storage.get(key);
}
