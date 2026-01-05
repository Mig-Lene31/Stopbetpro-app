import React, { useEffect, useState } from 'react';
import { View, Text, StyleSheet } from 'react-native';
import EngineState from '../core/EngineState';
import Storage from '../services/storage';
import { STORAGE_KEYS } from '../config/constants';

export default function BlockedScreen() {
  const [time, setTime] = useState(0);
  const [id, setId] = useState('');

  useEffect(() => {
    Storage.get(STORAGE_KEYS.USER_ID).then(setId);

    const t = setInterval(() => {
      setTime(Math.floor(EngineState.getRemainingTime() / 60000));
    }, 1000);

    return () => clearInterval(t);
  }, []);

  return (
    <View style={styles.container}>
      <Text style={styles.title}>App bloqueado</Text>
      <Text>Tempo restante: {time} min</Text>

      <Text style={styles.pix}>PIX: 000.000.000-00</Text>
      <Text>Envie comprovante + print do ID</Text>

      <Text style={styles.id}>{id}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, justifyContent: 'center', alignItems: 'center' },
  title: { fontSize: 22, marginBottom: 10 },
  pix: { marginTop: 20, fontWeight: 'bold' },
  id: { marginTop: 10 }
});
