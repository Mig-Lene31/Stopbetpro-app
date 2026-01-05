import React, { useState, useCallback } from 'react';
import { NavigationContainer, useFocusEffect } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';

import LegalNoticeScreen from '../screens/LegalNoticeScreen';
import AccessScreen from '../screens/AccessScreen';
import StopBetConfigScreen from '../screens/StopBetConfigScreen';
import BlockedScreen from '../screens/BlockedScreen';
import AdminLoginScreen from '../screens/AdminLoginScreen';
import AdminScreen from '../screens/AdminScreen';

import { getFlowState, ACCESS_STATUS } from '../core/FlowState';

const Stack = createNativeStackNavigator();

function FlowController() {
  const [state, setState] = useState(null);

  useFocusEffect(
    useCallback(() => {
      let alive = true;
      getFlowState().then(s => {
        if (alive) setState(s);
      });
      return () => {
        alive = false;
      };
    }, [])
  );

  if (!state) return null;

  // 1️⃣ Aviso legal
  if (!state.legalAccepted) {
    return (
      <Stack.Navigator screenOptions={{ headerShown: false }}>
        <Stack.Screen name="Legal" component={LegalNoticeScreen} />
      </Stack.Navigator>
    );
  }

  // 2️⃣ Não liberado (pagamento / espera)
  if (state.accessStatus !== ACCESS_STATUS.LIBERADO) {
    return (
      <Stack.Navigator screenOptions={{ headerShown: false }}>
        <Stack.Screen name="Access" component={AccessScreen} />
        <Stack.Screen name="AdminLogin" component={AdminLoginScreen} />
        <Stack.Screen name="Admin" component={AdminScreen} />
      </Stack.Navigator>
    );
  }

  // 3️⃣ Bloqueado por tempo
  if (state.blocked) {
    return (
      <Stack.Navigator screenOptions={{ headerShown: false }}>
        <Stack.Screen name="Blocked" component={BlockedScreen} />
        <Stack.Screen name="AdminLogin" component={AdminLoginScreen} />
        <Stack.Screen name="Admin" component={AdminScreen} />
      </Stack.Navigator>
    );
  }

  // 4️⃣ Liberado → Configuração
  return (
    <Stack.Navigator screenOptions={{ headerShown: false }}>
      <Stack.Screen name="StopBetConfig" component={StopBetConfigScreen} />
    </Stack.Navigator>
  );
}

export default function AppNavigator() {
  return (
    <NavigationContainer>
      <FlowController />
    </NavigationContainer>
  );
}
