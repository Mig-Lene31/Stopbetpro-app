import React, { useState } from 'react';
import { View, Text, TextInput, Button, Alert } from 'react-native';
import Storage from '../services/storage';
import { FLOW_KEYS, ACCESS_STATUS } from '../core/FlowState';

function senhaDoDia() {
  const d = new Date();
  return String(d.getDate()).padStart(2, '0') +
         String(d.getMonth() + 1).padStart(2, '0') +
         'Mi$';
}

export default function AdminScreen() {
  const [senha, setSenha] = useState('');
  const [ok, setOk] = useState(false);

  if (!ok) {
    return (
      <View>
        <Text>Senha ADM</Text>
        <TextInput secureTextEntry value={senha} onChangeText={setSenha} />
        <Button title="Entrar" onPress={() =>
          senha === senhaDoDia() ? setOk(true) : Alert.alert('Erro')
        }/>
      </View>
    );
  }

  return (
    <View>
      <Button
        title="Liberar 30 dias"
        onPress={async () => {
          await Storage.set(FLOW_KEYS.ACCESS, ACCESS_STATUS.LIBERADO);
          Alert.alert('Liberado');
        }}
      />

      <Button
        title="Desbloquear agora"
        onPress={async () => {
          await Storage.remove(FLOW_KEYS.BLOCKED);
          Alert.alert('Bloqueio removido');
        }}
      />
    </View>
  );
}
