import React, { useEffect, useState } from 'react';
import { View, Text, StyleSheet, Button } from 'react-native';
import Storage from '../services/storage';
import { STORAGE_KEYS } from '../config/constants';

export default function AccessScreen({ navigation }) {
  const [userId, setUserId] = useState(null);
  const [liberado, setLiberado] = useState(false);

  useEffect(() => {
    async function load() {
      let id = await Storage.get(STORAGE_KEYS.USER_ID);
      if (!id) {
        id = 'ID-' + Math.random().toString(36).substring(2, 10).toUpperCase();
        await Storage.set(STORAGE_KEYS.USER_ID, id);
      }
      setUserId(id);

      const acesso = await Storage.get('access_liberado');
      setLiberado(!!acesso);
    }
    load();
  }, []);

  if (!liberado) {
    return (
      <View style={styles.container}>
        <Text style={styles.title}>Acesso bloqueado</Text>
        <Text style={styles.text}>Envie o PIX e o comprovante no WhatsApp</Text>

        <Text style={styles.pix}>PIX: 000.000.000-00</Text>
        <Text style={styles.whats}>WhatsApp: (11) 90000-0000</Text>

        <Text style={styles.id}>Seu ID:</Text>
        <Text style={styles.idValue}>{userId}</Text>
      </View>
    );
  }

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Acesso liberado</Text>
      <Button title="Configurar App" onPress={() => navigation.replace('StopBetConfig')} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, justifyContent: 'center', alignItems: 'center', padding: 20 },
  title: { fontSize: 22, marginBottom: 15 },
  text: { textAlign: 'center', marginBottom: 10 },
  pix: { fontWeight: 'bold', marginTop: 10 },
  whats: { marginBottom: 20 },
  id: { marginTop: 20 },
  idValue: { fontWeight: 'bold', fontSize: 16 }
});
