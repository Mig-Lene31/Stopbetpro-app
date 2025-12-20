import React, { useState } from 'react';
import { View, Text, Switch, Button, TextInput, StyleSheet } from 'react-native';
import StopBetEngine from '../native/StopBetEngine';

export default function StopBetConfigScreen() {
  const [enabled, setEnabled] = useState(false);
  const [stopWin, setStopWin] = useState('');
  const [stopLoss, setStopLoss] = useState('');
  const [depositLimit, setDepositLimit] = useState('');

  const toggleEngine = async () => {
    alert('Configure depósito, Stop Win e Stop Loss antes de ativar');
    return;
  }
    if (enabled) {
      StopBetEngine.disable();
import('../core/AppActivationState').then(m => m.deactivateApp());
      import("../native/stopAccessibilityListener").then(m => m.stopAccessibilityListener());
    } else {
      StopBetEngine.enable({ stopWin, stopLoss, depositLimit });
import('../core/AppActivationState').then(m => m.activateApp());
      import("../native/stopAccessibilityListener").then(m => m.startAccessibilityListener());
    }
    setEnabled(!enabled);
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Configurações StopBet</Text>

      <Text>Ativar Motor:</Text>
      <Switch value={enabled} onValueChange={toggleEngine} />

      <Text>Stop Win:</Text>
      <TextInput
        value={stopWin}
        onChangeText={setStopWin}
        placeholder="Valor Stop Win"
        keyboardType="numeric"
        style={styles.input}
      />

      <Text>Stop Loss:</Text>
      <TextInput
        value={stopLoss}
        onChangeText={setStopLoss}
        placeholder="Valor Stop Loss"
        keyboardType="numeric"
        style={styles.input}
      />

      <Text>Valor de Depósito:</Text>
      <TextInput
        value={depositLimit}
        onChangeText={setDepositLimit}
        placeholder="Valor"
        keyboardType="numeric"
        style={styles.input}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, padding: 20 },
  title: { fontSize: 24, marginBottom: 20 },
  input: { borderWidth: 1, borderColor: '#ccc', padding: 10, marginBottom: 15 }
});
