import React from 'react';
import { View, Text, Button, StyleSheet, Alert } from 'react-native';
import { activateSubscription } from '../core/SubscriptionGuard';
import { clearBlock } from '../core/BlockController';

export default function AdminScreen() {

  const liberar30Dias = async () => {
    await activateSubscription(30);
    Alert.alert('OK', 'Assinatura liberada por 30 dias');
  };

  const desbloquearAgora = async () => {
    await clearBlock();
    Alert.alert('OK', 'Bloqueio removido');
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Painel Administrativo</Text>

      <Button title="Liberar 30 dias" onPress={liberar30Dias} />
      <Button title="Remover bloqueio" onPress={desbloquearAgora} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, justifyContent: 'center', padding: 20 },
  title: { fontSize: 22, marginBottom: 20, textAlign: 'center' }
});
