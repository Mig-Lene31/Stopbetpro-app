import React, { useState } from 'react';
import {
  View,
  Text,
  TextInput,
  TouchableOpacity,
  Alert,
  StyleSheet,
  NativeModules,
} from 'react-native';

const { StopBetBlocker } = NativeModules;

export default function StopBetConfigScreen() {
  const [deposit, setDeposit] = useState('');
  const [stopWin, setStopWin] = useState('');
  const [stopLoss, setStopLoss] = useState('');
  const [enabled, setEnabled] = useState(false);

  const saveRules = () => {
    if (!deposit || !stopWin || !stopLoss) {
      Alert.alert('Erro', 'Preencha todos os campos');
      return;
    }

    StopBetBlocker.setDeposit(Number(deposit));
    StopBetBlocker.setStopWin(Number(stopWin));
    StopBetBlocker.setStopLoss(Number(stopLoss));
    StopBetBlocker.enableBlock(true);

    setEnabled(true);
    Alert.alert('Ativado', 'Bloqueio configurado com sucesso');
  };

  const disableRules = () => {
    StopBetBlocker.enableBlock(false);
    setEnabled(false);
    Alert.alert('Desativado', 'Bloqueio desativado');
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>StopBet Pro</Text>

      <TextInput
        placeholder="Valor do Depósito (R$)"
        keyboardType="numeric"
        value={deposit}
        onChangeText={setDeposit}
        style={styles.input}
      />

      <TextInput
        placeholder="Stop Win (R$)"
        keyboardType="numeric"
        value={stopWin}
        onChangeText={setStopWin}
        style={styles.input}
      />

      <TextInput
        placeholder="Stop Loss (R$)"
        keyboardType="numeric"
        value={stopLoss}
        onChangeText={setStopLoss}
        style={styles.input}
      />

      {!enabled ? (
        <TouchableOpacity style={styles.btnOn} onPress={saveRules}>
          <Text style={styles.btnText}>ATIVAR BLOQUEIO</Text>
        </TouchableOpacity>
      ) : (
        <TouchableOpacity style={styles.btnOff} onPress={disableRules}>
          <Text style={styles.btnText}>DESATIVAR BLOQUEIO</Text>
        </TouchableOpacity>
      )}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#0f172a',
    padding: 24,
    justifyContent: 'center',
  },
  title: {
    color: '#22c55e',
    fontSize: 28,
    fontWeight: 'bold',
    textAlign: 'center',
    marginBottom: 32,
  },
  input: {
    backgroundColor: '#1e293b',
    color: '#fff',
    padding: 14,
    borderRadius: 8,
    marginBottom: 16,
    fontSize: 16,
  },
  btnOn: {
    backgroundColor: '#22c55e',
    padding: 16,
    borderRadius: 8,
    marginTop: 16,
  },
  btnOff: {
    backgroundColor: '#ef4444',
    padding: 16,
    borderRadius: 8,
    marginTop: 16,
  },
  btnText: {
    color: '#000',
    fontWeight: 'bold',
    textAlign: 'center',
    fontSize: 16,
  },
});
