import React, { useState } from 'react';
import { View, Text, TextInput, Button } from 'react-native';
import { registerUser, loginUser } from '../services/auth';

export default function LoginScreen({ navigation }) {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  async function handleLogin() {
    try {
      await loginUser(email, password);
      navigation.replace('Home');
    } catch (e) {
      setError('Usuário não encontrado');
    }
  }

  async function handleRegister() {
    await registerUser(email, password);
    navigation.replace('Home');
  }

  return (
    <View style={{ padding: 20 }}>
      <Text>Login / Registro</Text>

      <TextInput
        placeholder="Email"
        value={email}
        onChangeText={setEmail}
        style={{ borderWidth: 1, marginVertical: 6 }}
      />

      <TextInput
        placeholder="Senha"
        secureTextEntry
        value={password}
        onChangeText={setPassword}
        style={{ borderWidth: 1, marginVertical: 6 }}
      />

      {error ? <Text>{error}</Text> : null}

      <Button title="Entrar" onPress={handleLogin} />
      <Button title="Registrar" onPress={handleRegister} />
    </View>
  );
}
