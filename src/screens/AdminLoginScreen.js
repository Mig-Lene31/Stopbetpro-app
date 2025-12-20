import React, { useState } from 'react';
import { View, Text, TextInput, Button, StyleSheet, Alert } from 'react-native';
import { validateAdminPassword } from '../core/AdminAuth';
import { enableAdmin } from '../core/AdminState';

export default function AdminLoginScreen({ navigation }) {
  const [password, setPassword] = useState('');

  const entrar = () => {
    if (validateAdminPassword(password)) {
      enableAdmin();
      navigation.replace('Admin');
    } else {
      Alert.alert('Erro', 'Senha inválida');
    }
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Acesso Administrativo</Text>

      <TextInput
        placeholder="Senha do dia"
        secureTextEntry
        value={password}
        onChangeText={setPassword}
        style={styles.input}
      />

      <Button title="Entrar" onPress={entrar} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, justifyContent: 'center', padding: 20 },
  title: { fontSize: 22, marginBottom: 20, textAlign: 'center' },
  input: { borderWidth: 1, padding: 10, marginBottom: 20 }
});
