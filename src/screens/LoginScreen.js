import React, { useState } from 'react';
import { View, Text, TextInput, Button, StyleSheet, Alert } from 'react-native';
import { saveItem } from '../services/storage';
import { STORAGE_KEYS } from '../config/constants';

export default function LoginScreen({ navigation }) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const login = async () => {
    if (!username || !password) {
      Alert.alert('Erro', 'Preencha todos os campos');
      return;
    }

    // Aqui você pode integrar com backend real
    await saveItem(STORAGE_KEYS.USER, { username });
    navigation.reset({ index: 0, routes: [{ name: 'Home' }] });
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Login / Cadastro</Text>

      <Text>Usuário:</Text>
      <TextInput
        value={username}
        onChangeText={setUsername}
        placeholder="Digite seu usuário"
        style={styles.input}
      />

      <Text>Senha:</Text>
      <TextInput
        value={password}
        onChangeText={setPassword}
        placeholder="Digite sua senha"
        secureTextEntry
        style={styles.input}
      />

      <Button title="Entrar" onPress={login} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, padding: 20, justifyContent: 'center' },
  title: { fontSize: 24, marginBottom: 20, textAlign: 'center' },
  input: { borderWidth: 1, borderColor: '#ccc', padding: 10, marginBottom: 15 }
});
