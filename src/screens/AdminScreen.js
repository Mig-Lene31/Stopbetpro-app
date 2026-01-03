import React, { useState } from 'react';
import { View, Text, Button, StyleSheet, Alert, TextInput } from 'react-native';

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
        <Text style={styles.title}>Administração</Text>

        <TextInput
          placeholder="Senha ADM"
          secureTextEntry
          value={senha}
          onChangeText={setSenha}
          style={styles.input}
        />

        <Button
          title="Entrar"
          onPress={() => {
            if (senha === gerarSenhaDoDia()) {
              setOk(true);
            } else {
              Alert.alert('Erro', 'Senha incorreta');
            }
          }}
        />
      </View>
    );
  }

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Painel Administrativo</Text>

      <Button
        title="Liberar 30 dias"
        onPress={() => activateSubscription(30)}
      />

      <Button
        title="Remover bloqueio"
        onPress={clearBlock}
      />

      <Button
        title="Liberar acesso (Gate)"
        onPress={() => {
          Storage.set('access_liberado', true);
          Alert.alert('OK', 'Acesso liberado');
        }}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    padding: 20
  },
  title: {
    fontSize: 22,
    marginBottom: 20,
    textAlign: 'center'
  },
  input: {
    borderWidth: 1,
    padding: 10,
    marginBottom: 15
  }
});
