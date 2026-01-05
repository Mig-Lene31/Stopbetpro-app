import React, { useEffect, useState } from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';

import LegalNoticeScreen from '../screens/LegalNoticeScreen';
import AccessScreen from '../screens/AccessScreen';
import StopBetConfigScreen from '../screens/StopBetConfigScreen';
import BlockedScreen from '../screens/BlockedScreen';
import AdminLoginScreen from '../screens/AdminLoginScreen';
import AdminScreen from '../screens/AdminScreen';

import Storage from '../services/storage';
import EngineState from '../core/EngineState';

const Stack = createNativeStackNavigator();

export default function AppNavigator() {
  const [loading, setLoading] = useState(true);
  const [legalAccepted, setLegalAccepted] = useState(false);
  const [accessLiberado, setAccessLiberado] = useState(false);
  const [blocked, setBlocked] = useState(false);

  useEffect(() => {
    async function boot() {
      const legal = await Storage.get('legalAccepted');
      const acesso = await Storage.get('access_liberado');
      const isBlocked = await EngineState.isBlocked?.() || false;

      setLegalAccepted(!!legal);
      setAccessLiberado(!!acesso);
      setBlocked(!!isBlocked);
      setLoading(false);
    }
    boot();
  }, []);

  if (loading) return null;

  return (
    <NavigationContainer>
      <Stack.Navigator screenOptions={{ headerShown: false }}>

        {!legalAccepted && (
          <Stack.Screen name="Legal" component={LegalNoticeScreen} />
        )}

        {legalAccepted && !accessLiberado && (
          <Stack.Screen name="Access" component={AccessScreen} />
        )}

        {legalAccepted && accessLiberado && !blocked && (
          <Stack.Screen name="StopBetConfig" component={StopBetConfigScreen} />
        )}

        {blocked && (
          <Stack.Screen name="Blocked" component={BlockedScreen} />
        )}

        <Stack.Screen name="AdminLogin" component={AdminLoginScreen} />
        <Stack.Screen name="Admin" component={AdminScreen} />

      </Stack.Navigator>
    </NavigationContainer>
  );
}
