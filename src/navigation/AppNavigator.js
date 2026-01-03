import React, { useEffect, useState } from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';

import LoginScreen from '../screens/LoginScreen';
import LegalNoticeScreen from '../screens/LegalNoticeScreen';
import PaymentScreen from '../screens/PaymentScreen';
import PixPaymentScreen from '../screens/PixPaymentScreen';
import AccessScreen from '../screens/AccessScreen';
import StopBetConfigScreen from '../screens/StopBetConfigScreen';
import BlockedScreen from '../screens/BlockedScreen';
import AdminLoginScreen from '../screens/AdminLoginScreen';
import AdminScreen from '../screens/AdminScreen';

import Storage from '../services/storage';
import { STORAGE_KEYS } from '../config/constants';
import { hasValidSubscription } from '../core/SubscriptionGuard';

const Stack = createNativeStackNavigator();

export default function AppNavigator() {
  const [loading, setLoading] = useState(true);
  const [logged, setLogged] = useState(false);
  const [paid, setPaid] = useState(false);
  const [legalAccepted, setLegalAccepted] = useState(false);

  useEffect(() => {
    async function boot() {
      const user = await Storage.get(STORAGE_KEYS.USER);
      const subscription = await hasValidSubscription();
      const legal = await Storage.get('legalAccepted');

      setLogged(!!user);
      setPaid(subscription);
      setLegalAccepted(!!legal);
      setLoading(false);
    }
    boot();
  }, []);

  if (loading) return null;

  return (
    <NavigationContainer>
      <Stack.Navigator screenOptions={{ headerShown: false }}>

        {!logged && (
          <Stack.Screen name="Login" component={LoginScreen} />
        )}

        {logged && !paid && (
          <>
            <Stack.Screen name="Payment" component={PaymentScreen} />
            <Stack.Screen name="PixPayment" component={PixPaymentScreen} />
          </>
        )}

        {logged && paid && !legalAccepted && (
          <Stack.Screen name="Legal" component={LegalNoticeScreen} />
        )}

        {logged && paid && legalAccepted && (
          <>
            <Stack.Screen name="Access" component={AccessScreen} />
            <Stack.Screen name="StopBetConfig" component={StopBetConfigScreen} />
            <Stack.Screen name="Blocked" component={BlockedScreen} />
          </>
        )}

        <Stack.Screen name="AdminLogin" component={AdminLoginScreen} />
        <Stack.Screen name="Admin" component={AdminScreen} />

      </Stack.Navigator>
    </NavigationContainer>
  );
}
