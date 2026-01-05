import React, { useState } from 'react';
import { View, Text, TextInput, Button, Alert, StyleSheet } from 'react-native';
import Storage from '../services/storage';
import { activateSubscription } from '../core/SubscriptionGuard';
import { clearBlock } from '../core/BlockController';

function gerarSenhaDoDia() {
  const d = new Date();
  const dia = String(d.getDate()).padStart(2, '0');
  const mes = String(d.getMonth() + 1).padStart(2, '0');
  return dia + mes + 'Mi$';
}

export default function AdminScreen() {
  const [senha, setSenha] = useState('');
  const [ok, setOk] = useState(false);

  if (!ok) {
    return (
      <View style={styles.container}>
        <Text>Senha ADM</Text>
        <TextInput
          secureTextEntry
          value={senha}
          onChangeText={setSenha}
          style={styles.input}
        />
        <Button
          title="Entrar"
          onPress={() =>
            senha === gerarSenhaDoDia()
              ? setOk(true)
              : Alert.alert('Erro', 'Senha incorreta')
          }
        />
      </View>
    );
  }

  return (
    <View style={styles.container}>
      <Text>Painel Administrativo</Text>

      <Button
        title="Liberar 30 dias"
        onPress={async () => {
          await activateSubscription(30);
          await Storage.set('access_liberado', true);
          Alert.alert('OK', 'Acesso liberado por 30 dias');
        }}
      />

      <Button
        title="Desbloquear agora"
        onPress={() => {
          clearBlock();
          Alert.alert('OK', 'Bloqueio removido');
        }}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, justifyContent: 'center', padding: 20 },
  input: { borderWidth: 1, padding: 10, marginBottom: 10 }
});
