import React, { useState } from 'react';
import { View, Text, TextInput, Button, StyleSheet, Alert } from 'react-native';
import Storage from '../services/storage';

export default function DepositConfirmScreen({ navigation }) {
  const [deposit, setDeposit] = useState('');

  const confirm = async () => {
    const value = Number(deposit);
    if (!value || value <= 0) {
      Alert.alert('Erro', 'Informe um valor válido de depósito');
      return;
    }

    await Storage.set('depositAmount', value);
    navigation.replace('Home');
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Confirme seu depósito</Text>

      <Text style={styles.text}>
        Informe exatamente o valor que você irá depositar no site de apostas.
      </Text>

      <TextInput
        value={deposit}
        onChangeText={setDeposit}
        keyboardType="numeric"
        placeholder="Ex: 100"
        style={styles.input}
      />

      <Button title="Confirmar" onPress={confirm} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, padding: 20, justifyContent: 'center' },
  title: { fontSize: 22, marginBottom: 20 },
  text: { fontSize: 16, marginBottom: 15 },
  input: { borderWidth: 1, padding: 10, marginBottom: 20 }
});
