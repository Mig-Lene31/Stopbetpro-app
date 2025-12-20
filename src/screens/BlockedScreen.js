import React from 'react';
import { View, Text, Button, StyleSheet, Alert } from 'react-native';
import * as Clipboard from '@react-native-clipboard/clipboard';

const PIX_KEY = '11970200771';

export default function BlockedScreen() {
  const copiarPix = () => {
    Clipboard.setString(PIX_KEY);
    Alert.alert(
      'Pix copiado',
      'Pague R$50,00 e envie o comprovante no WhatsApp (11 97020-0771).'
    );
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>ACESSO BLOQUEADO</Text>

      <Text style={styles.text}>
        O limite configurado foi atingido.
        O acesso será liberado automaticamente após 12 horas.
      </Text>

      <Text style={styles.text}>
        Para desbloqueio antecipado, o valor é R$50,00 via Pix.
      </Text>

      <View style={styles.box}>
        <Text selectable style={styles.pix}>{PIX_KEY}</Text>
      </View>

      <Button title="Copiar chave Pix" onPress={copiarPix} />
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
    fontSize: 24,
    fontWeight: 'bold',
    marginBottom: 20,
    textAlign: 'center'
  },
  text: {
    fontSize: 16,
    marginBottom: 15,
    textAlign: 'center'
  },
  box: {
    borderWidth: 1,
    borderColor: '#ccc',
    padding: 10,
    borderRadius: 6,
    marginBottom: 20
  },
  pix: {
    fontSize: 16,
    textAlign: 'center'
  }
});
