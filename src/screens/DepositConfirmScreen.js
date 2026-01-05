import React from 'react';
import { View, Text, Button } from 'react-native';
import Storage from '../services/storage';

export default function DepositConfirmScreen() {
  const confirm = async () => {
    await Storage.set('access_status', 'LIBERADO');
  };

  return (
    <View>
      <Text>Depósito confirmado</Text>
      <Button title="Continuar" onPress={confirm} />
    </View>
  );
}
