import React from 'react';
import { View, Text, Button, StyleSheet, ScrollView } from 'react-native';
import Storage from '../services/storage';

export default function LegalNoticeScreen({ navigation }) {
  const accept = async () => {
    await Storage.set('legalAccepted', true);
    navigation.replace('DepositConfirm');
  };

  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Text style={styles.title}>Aviso Importante</Text>

      <Text style={styles.text}>
        O StopBet Pro funciona com base nas informações fornecidas por você
        e na leitura automática de valores exibidos em sites de apostas.
      </Text>

      <Text style={styles.text}>
        É sua responsabilidade informar corretamente o valor de depósito inicial.
        Valores incorretos podem causar bloqueios antecipados ou tardios.
      </Text>

      <Text style={styles.text}>
        O aplicativo NÃO acessa contas, senhas ou dados pessoais.
        Ele atua apenas para bloquear o acesso após critérios definidos.
      </Text>

      <Text style={styles.text}>
        Ao continuar, você declara estar ciente e de acordo com o funcionamento.
      </Text>

      <Button title="Li e estou ciente" onPress={accept} />
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: { padding: 20 },
  title: { fontSize: 22, marginBottom: 20, fontWeight: 'bold' },
  text: { fontSize: 16, marginBottom: 15 }
});
