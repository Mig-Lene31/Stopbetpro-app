import { NativeModules, Platform } from 'react-native';

const { BlockOverlay } = NativeModules;

export function showBlockOverlay() {
  if (Platform.OS !== 'android') return;
  if (!BlockOverlay) return;

  BlockOverlay.show();
}

export function hideBlockOverlay() {
  if (Platform.OS !== 'android') return;
  if (!BlockOverlay) return;

  BlockOverlay.hide();
}
