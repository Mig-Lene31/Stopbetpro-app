import React, { useState } from 'react';
import {
  View,
  Text,
  TextInput,
  TouchableOpacity,
  StyleSheet
} from 'react-native';

import { set } from '../services/storage';
import { startBlockSystem } from '../services/blockEngine';
import useBlockNavigator from '../hooks/useBlockNavigator';

export default function HomeScreen({ navigation }) {
  useBlockNavigator(navigation);

  const [deposit, setDeposit] = useState('');
  const [stopWin, setStopWin] = useState('');
  const [stopLoss, setStopLoss] = useState('');

  async function saveConfig() {
    await set('depositAmount', Number(deposit));
    await set('stopWin', Number(stopWin));
    await set('stopLoss', Number(stopLoss));

    console.log('[STOPBET] Configuração salva');
    await startBlockSystem();
  }

  return (
    <View style={styles.container}>
      <Text style={styles.title}>StopBet Pro</Text>

      <TextInput
        style={styles.input}
        placeholder="Valor do depósito"
        keyboardType="numeric"
        value={deposit}
        onChangeText={setDeposit}
      />

      <TextInput
        style={styles.input}
        placeholder="Stop Win (lucro)"
        keyboardType="numeric"
        value={stopWin}
        onChangeText={setStopWin}
      />

      <TextInput
        style={styles.input}
        placeholder="Stop Loss (perda)"
        keyboardType="numeric"
        value={stopLoss}
        onChangeText={setStopLoss}
      />

      <TouchableOpacity style={styles.button} onPress={saveConfig}>
        <Text style={styles.buttonText}>ATIVAR PROTEÇÃO</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#0e0e0e',
    justifyContent: 'center',
    padding: 20,
  },
  title: {
    color: '#fff',
    fontSize: 26,
    textAlign: 'center',
    marginBottom: 30,
  },
  input: {
    backgroundColor: '#fff',
    borderRadius: 6,
    padding: 12,
    marginBottom: 15,
  },
  button: {
    backgroundColor: '#2ecc71',
    padding: 15,
    borderRadius: 8,
    alignItems: 'center',
  },
  buttonText: {
    color: '#000',
    fontWeight: 'bold',
  },
});
