import React, { useEffect, useState } from 'react';
import { View, Text, Button, BackHandler } from 'react-native';
import { getBlockStatus, clearBlock } from '../services/block';
import { createUnlockPayment, confirmPayment } from '../services/payment';
import { canUnlockEarly } from '../services/security';

export default function BlockedScreen({ navigation }) {
  const [block, setBlock] = useState(null);
  const [payment, setPayment] = useState(null);

  useEffect(() => {
    loadBlock();
    const backHandler = BackHandler.addEventListener(
      'hardwareBackPress',
      () => true
    );
    return () => backHandler.remove();
  }, []);

  async function loadBlock() {
    const b = await getBlockStatus();
    setBlock(b);
  }

  async function unlockWithPayment() {
    const p = await createUnlockPayment();
    setPayment(p);
  }

  async function confirmUnlock() {
    const paid = await confirmPayment();
    if (canUnlockEarly(paid)) {
      await clearBlock();
      navigation.replace('Home');
    }
  }

  if (!block || !block.active) {
    navigation.replace('Home');
    return null;
  }

  return (
    <View style={{ padding: 20 }}>
      <Text>BLOQUEIO ATIVO</Text>
      <Text>Apostas bloqueadas por segurança</Text>

      {!payment ? (
        <Button
          title="Desbloquear por R$50"
          onPress={unlockWithPayment}
        />
      ) : (
        <Button
          title="Confirmar Pagamento"
          onPress={confirmUnlock}
        />
      )}
    </View>
  );
}
