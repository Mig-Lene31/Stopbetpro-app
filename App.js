import React, { useEffect } from 'react';
import AppNavigator from './src/navigation/AppNavigator';
import { startBlockWatchdog } from './src/core/BlockWatchdog';
import { runBootGuard } from './src/core/BootGuard';

export default function App() {
  useEffect(() => {
    runBootGuard();
    startBlockWatchdog();
  }, []);

  return <AppNavigator />;
}
