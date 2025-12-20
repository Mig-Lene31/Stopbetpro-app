import React, { useEffect, useState } from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';

import LoginScreen from '../screens/LoginScreen';
import HomeScreen from '../screens/HomeScreen';
import BlockedScreen from '../screens/BlockedScreen';
import PaymentScreen from '../screens/PaymentScreen';
import LegalNoticeScreen from '../screens/LegalNoticeScreen';
import DepositConfirmScreen from '../screens/DepositConfirmScreen';
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

        {/* 🔐 LOGIN */}
        {!logged && (
          <Stack.Screen name="Login" component={LoginScreen} />
        )}

        {/* 💳 PAGAMENTO */}
        {logged && !paid && (
          <Stack.Screen name="Payment" component={PaymentScreen} />
        )}

        {/* ⚠️ AVISO LEGAL */}
        {logged && paid && !legalAccepted && (
          <Stack.Screen name="Legal" component={LegalNoticeScreen} />
        )}

        {/* 🧾 CONFIRMAÇÃO DE DEPÓSITO */}
        {logged && paid && legalAccepted && (
          <>
            <Stack.Screen name="DepositConfirm" component={DepositConfirmScreen} />
            <Stack.Screen name="Home" component={HomeScreen} />
            <Stack.Screen name="Blocked" component={BlockedScreen} />
          </>
        )}

        {/* 🛠️ ADMIN (OCULTO) */}
        <Stack.Screen name="AdminLogin" component={AdminLoginScreen} />
        <Stack.Screen name="Admin" component={AdminScreen} />

      </Stack.Navigator>
    </NavigationContainer>
  );
}
