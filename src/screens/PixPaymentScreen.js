import React, { useState } from 'react';
import { View, Text, Button, StyleSheet } from 'react-native';
import { createPixPayment } from '../services/payment';

export default function PixPaymentScreen() {
  const [pix, setPix] = useState(null);

  const generatePix = async () => {
    const p = await createPixPayment(20);
    setPix(p);
    alert('Pix gerado, copie o código para pagar.');
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Pagamento Pix</Text>
      {!pix ? (
        <Button title="Gerar Pix" onPress={generatePix} />
      ) : (
        <Text>{pix.txid}</Text>
      )}
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, justifyContent: 'center', alignItems: 'center', padding: 20 },
  title: { fontSize: 24, marginBottom: 20 }
});
