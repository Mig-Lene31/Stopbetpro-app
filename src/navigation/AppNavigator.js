import React, { useEffect, useState } from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';

import LegalNoticeScreen from '../screens/LegalNoticeScreen';
import AccessScreen from '../screens/AccessScreen';
import StopBetConfigScreen from '../screens/StopBetConfigScreen';
import BlockedScreen from '../screens/BlockedScreen';
import AdminLoginScreen from '../screens/AdminLoginScreen';
import AdminScreen from '../screens/AdminScreen';

import { getFlowState, ACCESS_STATUS } from '../core/FlowState';

const Stack = createNativeStackNavigator();

export default function AppNavigator() {
  const [state, setState] = useState(null);

  useEffect(() => {
    getFlowState().then(setState);
  }, []);

  if (!state) return null;

  return (
    <NavigationContainer>
      <Stack.Navigator screenOptions={{ headerShown: false }}>
        {!state.legalAccepted && (
          <Stack.Screen name="Legal" component={LegalNoticeScreen} />
        )}

        {state.legalAccepted && state.accessStatus !== ACCESS_STATUS.LIBERADO && (
          <Stack.Screen name="Access" component={AccessScreen} />
        )}

        {state.legalAccepted &&
          state.accessStatus === ACCESS_STATUS.LIBERADO &&
          !state.blocked && (
            <Stack.Screen name="StopBetConfig" component={StopBetConfigScreen} />
          )}

        {state.blocked && (
          <Stack.Screen name="Blocked" component={BlockedScreen} />
        )}

        <Stack.Screen name="AdminLogin" component={AdminLoginScreen} />
        <Stack.Screen name="Admin" component={AdminScreen} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
