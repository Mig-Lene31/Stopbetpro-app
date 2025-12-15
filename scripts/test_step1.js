import Storage from '../src/services/storage.js';

async function setup() {
  await Storage.set('depositAmount', 100);
  await Storage.set('stopWin', 50);
  await Storage.set('stopLoss', 40);

  console.log('[TEST] Configuração inicial salva');
}

setup();
