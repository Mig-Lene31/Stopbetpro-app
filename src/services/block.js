import { NativeModules } from 'react-native';

const { BlockerModule } = NativeModules;

export function startBlock(hours) {
  if (!BlockerModule) {
    console.log('❌ BlockerModule não encontrado');
    return;
  }

  BlockerModule.startBlock(hours);
}
