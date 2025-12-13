import React, { useEffect, useState } from 'react';
import { View, Text, Button } from 'react-native';
import { startBlock, getBlockStatus } from '../services/block';
import { BLOCK_TIME_HOURS } from '../config/constants';

export default function HomeScreen({ navigation }) {
  const [block, setBlock] = useState(null);

  useEffect(() => {
    loadStatus();
  }, []);

  async function loadStatus() {
    const status = await getBlockStatus();
    if (status && status.active) {
      navigation.replace('Blocked');
      return;
    }
    setBlock(status);
  }

  async function activateBlock() {
    await startBlock(BLOCK_TIME_HOURS);
    navigation.replace('Blocked');
  }

  return (
    <View style={{ padding: 20 }}>
      <Text>StopBet Pro</Text>
      <Text>Controle de apostas ativo</Text>

      <Button
        title="Ativar Bloqueio"
        onPress={activateBlock}
      />
    </View>
  );
}
