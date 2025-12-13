import { NativeEventEmitter, NativeModules } from 'react-native';
import { processBalance } from './proBlocker';

const { StopBetBridge } = NativeModules;

const emitter = new NativeEventEmitter(StopBetBridge);

export function startBalanceListener(config) {
  emitter.addListener('STOPBET_BALANCE', balance => {
    processBalance(balance, config);
  });
}
