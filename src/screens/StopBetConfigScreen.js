import React, { useState, useEffect } from 'react';
import { View, Text, TextInput, Button, ScrollView, StyleSheet, Switch } from 'react-native';
import { CONFIG_KEYS, saveConfig, getConfig } from '../core/ConfigState';

export default function StopBetConfigScreen() {
  const [deposit, setDeposit] = useState('');
  const [win, setWin] = useState('');
  const [loss, setLoss] = useState('');
  const [time, setTime] = useState('');
  const [engine, setEngine] = useState(false);

  useEffect(() => {
    async function load() {
      setDeposit((await getConfig(CONFIG_KEYS.DEPOSIT)) || '');
      setWin((await getConfig(CONFIG_KEYS.STOP_WIN)) || '');
      setLoss((await getConfig(CONFIG_KEYS.STOP_LOSS)) || '');
      setTime((await getConfig(CONFIG_KEYS.STOP_TIME)) || '');
      setEngine((await getConfig(CONFIG_KEYS.ENGINE)) || false);
    }
    load();
  }, []);

  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Text style={styles.title}>Configuração do StopBet</Text>

      <Text>Valor de Depósito</Text>
      <TextInput style={styles.input} value={deposit} onChangeText={setDeposit} onBlur={() => saveConfig(CONFIG_KEYS.DEPOSIT, deposit)} />

      <Text>Stop WIN</Text>
      <TextInput style={styles.input} value={win} onChangeText={setWin} onBlur={() => saveConfig(CONFIG_KEYS.STOP_WIN, win)} />

      <Text>Stop LOSS</Text>
      <TextInput style={styles.input} value={loss} onChangeText={setLoss} onBlur={() => saveConfig(CONFIG_KEYS.STOP_LOSS, loss)} />

      <Text>Stop TEMPO (min)</Text>
      <TextInput style={styles.input} value={time} onChangeText={setTime} onBlur={() => saveConfig(CONFIG_KEYS.STOP_TIME, time)} />

      <View style={styles.switchRow}>
        <Text>Ativar motor</Text>
        <Switch value={engine} onValueChange={() => {}} />
      </View>

      <Text style={styles.info}>
        ⚠️ O motor ainda não está ativo.
        Esta área é apenas para configuração.
      </Text>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: { padding: 20 },
  title: { fontSize: 22, marginBottom: 20 },
  input: { borderWidth: 1, marginBottom: 15, padding: 10 },
  switchRow: { flexDirection: 'row', justifyContent: 'space-between', marginTop: 20 },
  info: { marginTop: 30, fontSize: 12, color: '#666' }
});
