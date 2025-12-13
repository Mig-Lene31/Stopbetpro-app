import React, { useEffect, useState } from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import LoginScreen from '../screens/LoginScreen';
import HomeScreen from '../screens/HomeScreen';
import BlockedScreen from '../screens/BlockedScreen';
import { getItem } from '../services/storage';
import { STORAGE_KEYS } from '../config/constants';

const Stack = createNativeStackNavigator();

export default function AppNavigator() {
  const [loading, setLoading] = useState(true);
  const [logged, setLogged] = useState(false);

  useEffect(() => {
    checkSession();
  }, []);

  async function checkSession() {
    const user = await getItem(STORAGE_KEYS.USER);
    setLogged(!!user);
    setLoading(false);
  }

  if (loading) return null;

  return (
    <NavigationContainer>
      <Stack.Navigator
        screenOptions={{
          headerShown: false,
          gestureEnabled: false
        }}
      >
        {!logged ? (
          <Stack.Screen name="Login" component={LoginScreen} />
        ) : (
          <>
            <Stack.Screen name="Home" component={HomeScreen} />
            <Stack.Screen name="Blocked" component={BlockedScreen} />
          </>
        )}
      </Stack.Navigator>
    </NavigationContainer>
  );
}
