import React from 'react';
import { View, Text, Button, StyleSheet } from 'react-native';

export default function HomeScreen({ navigation }) {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Bem-vindo ao StopBet Pro</Text>

      <Button
        title="Configurações StopBet"
        onPress={() => navigation.navigate('StopBetConfig')}
      />

      <Button
        title="Pagamento Mensal"
        onPress={() => navigation.navigate('Payment')}
      />

      <Button
        title="Pagamento Pix"
        onPress={() => navigation.navigate('PixPayment')}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, justifyContent: 'center', alignItems: 'center', padding: 20 },
  title: { fontSize: 24, marginBottom: 20, textAlign: 'center' }
});
