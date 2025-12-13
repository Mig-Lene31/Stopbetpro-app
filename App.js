import React, { useEffect } from 'react';
import { View, Text, NativeEventEmitter, NativeModules } from 'react-native';

const { StopBetBridge } = NativeModules;
const stopBetEmitter = new NativeEventEmitter(StopBetBridge);

export default function App() {

  useEffect(() => {
    const sub = stopBetEmitter.addListener(
      'STOPBET_BALANCE',
      (balance) => {
        console.log('🔥 SALDO RECEBIDO DO ANDROID:', balance);
      }
    );

    return () => sub.remove();
  }, []);

  return (
    <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
      <Text>StopBet Pro ativo</Text>
    </View>
  );
}
