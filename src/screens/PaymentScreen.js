import React from 'react';
import { View, Text, Button, StyleSheet, Alert } from 'react-native';
import * as Clipboard from '@react-native-clipboard/clipboard';

const PIX_KEY = '11970200771';
const WHATSAPP_NUMBER = '5511970200771';

export default function PaymentScreen() {
  const copiarPix = () => {
    Clipboard.setString(PIX_KEY);
    Alert.alert(
      'Pix copiado',
      'Envie o comprovante junto com o ID do app no WhatsApp.'
    );
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Ativação da Assinatura</Text>

      <Text style={styles.text}>
        Valor mensal: R$100,00
      </Text>

      <Text style={styles.text}>
        Copie a chave Pix abaixo, realize o pagamento e envie o comprovante
        junto com o ID exibido no app para o WhatsApp.
      </Text>

      <View style={styles.box}>
        <Text selectable style={styles.pix}>{PIX_KEY}</Text>
      </View>

      <Button title="Copiar chave Pix" onPress={copiarPix} />

      <View style={{ height: 20 }} />

      <Button
        title="Enviar comprovante no WhatsApp"
        onPress={() =>
          Alert.alert(
            'WhatsApp',
            'Envie o comprovante para o número 11 97020-0771'
          )
        }
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, padding: 20, justifyContent: 'center' },
  title: { fontSize: 22, fontWeight: 'bold', marginBottom: 20, textAlign: 'center' },
  text: { fontSize: 16, marginBottom: 15, textAlign: 'center' },
  box: {
    borderWidth: 1,
    borderColor: '#ccc',
    padding: 10,
    marginBottom: 20,
    borderRadius: 6
  },
  pix: { fontSize: 16, textAlign: 'center' }
});
