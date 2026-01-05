import React from 'react';
import { View, Text, Button } from 'react-native';
import Storage from '../services/storage';

export default function ConfirmBlockScreen() {
  const confirm = async () => {
    await Storage.set('blocked', true);
  };

  return (
    <View>
      <Text>Bloqueio confirmado</Text>
      <Button title="OK" onPress={confirm} />
    </View>
  );
}
