import { NativeModules, Platform } from 'react-native';

const { StopBetEngine } = NativeModules;

export default {
  enable(config) {
    if (StopBetEngine && Platform.OS === 'android') {
      StopBetEngine.enable(
        config.maxTime,
        config.initialValue,
        config.stopWin,
        config.stopLoss
      );
    }
  },

  updateValue(value) {
    if (StopBetEngine && Platform.OS === 'android') {
      StopBetEngine.updateValue(value);
    }
  },

  async isBlocked() {
    if (StopBetEngine && Platform.OS === 'android') {
      return await StopBetEngine.isBlocked();
    }
    return false;
  },

  async remainingSeconds() {
    if (StopBetEngine && Platform.OS === 'android') {
      return await StopBetEngine.remainingSeconds();
    }
    return 0;
  },

  // 💰 DESBLOQUEIO APÓS PAGAMENTO
  async unlockPaid() {
    if (StopBetEngine && Platform.OS === 'android') {
      return await StopBetEngine.unlockPaid();
    }
    return false;
  },

  disable() {
    if (StopBetEngine && Platform.OS === 'android') {
      StopBetEngine.disable();
    }
  },
};
