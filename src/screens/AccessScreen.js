import React, { useEffect, useState } from 'react';
import { View, Text, Button, StyleSheet } from 'react-native';
import Storage from '../services/storage';

export default function AccessScreen({ navigation }) {
  const [userId, setUserId] = useState('');
  const [status, setStatus] = useState('AGUARDANDO');
  const [liberado, setLiberado] = useState(false);

  useEffect(() => {
    async function load() {
      let id = await Storage.get('userId');
      if (!id) {
        id = Math.random().toString(36).substring(2, 10).toUpperCase();
        await Storage.set('userId', id);
      }
      setUserId(id);
    }
    load();
  }, []);

  const avancar = () => {
    navigation.replace('StopBetConfig');
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>STOPBET PRO</Text>

      <Text style={styles.label}>SEU ID:</Text>
      <Text style={styles.id}>{userId}</Text>

      <Text style={styles.text}>Para liberar o acesso:</Text>
      <Text style={styles.text}>1. Envie o pagamento via PIX</Text>
      <Text style={styles.text}>2. Tire um PRINT desta tela</Text>
      <Text style={styles.text}>3. Envie no WhatsApp</Text>

      <Text style={styles.text}>WhatsApp / PIX:</Text>
      <Text style={styles.text}>(11) 97020-0771</Text>

      <Text style={styles.status}>
        STATUS: {status}
      </Text>

      <Button title="Avançar" disabled={!liberado} onPress={avancar} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, padding: 20, justifyContent: 'center' },
  title: { fontSize: 24, textAlign: 'center', marginBottom: 20 },
  label: { fontSize: 16, textAlign: 'center' },
  id: { fontSize: 18, fontWeight: 'bold', textAlign: 'center', marginBottom: 20 },
  text: { fontSize: 14, textAlign: 'center', marginBottom: 5 },
  status: { marginTop: 20, fontSize: 16, textAlign: 'center' }
});
