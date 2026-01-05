import React, { useState, useCallback } from 'react';
import { NavigationContainer, useFocusEffect } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';

import LegalNoticeScreen from '../screens/LegalNoticeScreen';
import AccessScreen from '../screens/AccessScreen';
import StopBetConfigScreen from '../screens/StopBetConfigScreen';
import AdminLoginScreen from '../screens/AdminLoginScreen';
import AdminScreen from '../screens/AdminScreen';

import { getFlowState, ACCESS_STATUS } from '../core/FlowState';

const Stack = createNativeStackNavigator();

function FlowController() {
  const [state, setState] = useState(null);

  useFocusEffect(
    useCallback(() => {
      getFlowState().then(setState);
    }, [])
  );

  if (!state) return null;

  return (
    <Stack.Navigator screenOptions={{ headerShown: false }}>
      {!state.legalAccepted && (
        <Stack.Screen name="Legal" component={LegalNoticeScreen} />
      )}

      {state.legalAccepted &&
        state.accessStatus !== ACCESS_STATUS.LIBERADO && (
          <Stack.Screen name="Access" component={AccessScreen} />
        )}

      {state.legalAccepted &&
        state.accessStatus === ACCESS_STATUS.LIBERADO && (
          <Stack.Screen
            name="StopBetConfig"
            component={StopBetConfigScreen}
          />
        )}

      <Stack.Screen name="AdminLogin" component={AdminLoginScreen} />
      <Stack.Screen name="Admin" component={AdminScreen} />
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
