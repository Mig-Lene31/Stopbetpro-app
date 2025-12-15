import React, { useEffect, useState } from 'react';
import {
  View,
  Text,
  TouchableOpacity,
  StyleSheet
} from 'react-native';

import Storage from '../services/storage';
import { activateBlock } from '../services/block';

export default function ConfirmBlockScreen({ navigation }) {
  const [balance, setBalance] = useState(0);
  const [reason, setReason] = useState('');

  useEffect(() => {
    async function loadData() {
      const b = await Storage.get('lastDetectedBalance');
      const r = await Storage.get('blockReason');

      setBalance(b);
      setReason(r);
    }

    loadData();
  }, []);

  async function confirmBlock() {
    await activateBlock();
    navigation.replace('Blocked');
  }

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Confirmação de Bloqueio</Text>

      <Text style={styles.text}>
        Saldo detectado: R$ {balance}
      </Text>

      <Text style={styles.text}>
        Motivo: {reason === 'STOP_WIN' ? 'Stop Win atingido' : 'Stop Loss atingido'}
      </Text>

      <TouchableOpacity style={styles.button} onPress={confirmBlock}>
        <Text style={styles.buttonText}>CONFIRMAR BLOQUEIO</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#0e0e0e',
    justifyContent: 'center',
    alignItems: 'center',
    padding: 20
  },
  title: {
    color: '#fff',
    fontSize: 24,
    marginBottom: 20
  },
  text: {
    color: '#fff',
    fontSize: 18,
    marginBottom: 10
  },
  button: {
    backgroundColor: '#e74c3c',
    padding: 15,
    borderRadius: 8,
    marginTop: 30
  },
  buttonText: {
    color: '#fff',
    fontWeight: 'bold'
  }
});
