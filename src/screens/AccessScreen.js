import React, { useEffect, useState } from 'react';
import { View, Text, StyleSheet } from 'react-native';
import Storage from '../services/storage';
import { STORAGE_KEYS } from '../config/constants';

export default function AccessScreen() {
  const [id, setId] = useState('');

  useEffect(() => {
    async function load() {
      let uid = await Storage.get(STORAGE_KEYS.USER_ID);
      if (!uid) {
        uid = 'ID-' + Math.random().toString(36).substring(2, 10).toUpperCase();
        await Storage.set(STORAGE_KEYS.USER_ID, uid);
      }
      setId(uid);
    }
    load();
  }, []);

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Acesso bloqueado</Text>
      <Text>Envie o PIX + comprovante no WhatsApp</Text>

      <Text style={styles.pix}>PIX: 000.000.000-00</Text>
      <Text>WhatsApp: (11) 90000-0000</Text>

      <Text style={styles.id}>{id}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, justifyContent: 'center', alignItems: 'center', padding: 20 },
  title: { fontSize: 22, marginBottom: 10 },
  pix: { marginTop: 15, fontWeight: 'bold' },
  id: { marginTop: 20, fontSize: 16, fontWeight: 'bold' }
});
