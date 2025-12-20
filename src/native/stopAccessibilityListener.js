import { NativeEventEmitter, NativeModules } from 'react-native';

const { StopAccessibility } = NativeModules;
const emitter = new NativeEventEmitter(StopAccessibility);

let subscription = null;

export function startAccessibilityListener() {
  if (subscription) return;

  subscription = emitter.addListener(
    'StopAccessibilityText',
    (text) => {
      import('../core').then(m => {
        if (m.handleAccessibilityEvent) {
          m.handleAccessibilityEvent(text);
        }
      });
    }
  );
}

export function stopAccessibilityListener() {
  if (subscription) {
    subscription.remove();
    subscription = null;
  }
}
