import { BackHandler } from 'react-native';
import { shouldBlock } from './stopEngine';
import { startBlock } from './block';

let blocked = false;

export function processBalance(balance, config) {
  if (!config || blocked) return;

  if (shouldBlock(balance, config.stopWin, config.stopLoss)) {
    blocked = true;
    startBlock(12);
    BackHandler.exitApp();
  }
}
