import { useEffect } from 'react';
import { useStopEngine } from './useStopEngine';
import { startAccessibilityListener, stopAccessibilityListener } from '../native/stopAccessibilityListener';
import { parsePossibleBalance } from '../utils/balanceParser';
import { isStableBalance, resetStability } from '../utils/balanceStability';
import { showBlockOverlay, hideBlockOverlay } from '../native/blockOverlay';
import { formatRemaining } from '../utils/timeFormatter';

export function useStopBetController(config) {
  const engine = useStopEngine(config);

  useEffect(() => {
    startAccessibilityListener((text) => {
      const balance = parsePossibleBalance(text);
      if (balance === null) return;
      if (!isStableBalance(balance)) return;

      const result = engine.checkBalance(balance);

      if (result?.block) {
        showBlockOverlay(`🔒 BLOQUEADO\n\nTempo restante:\n${formatRemaining(result.remaining)}`);
        return;
      }

      if (result?.confirm) {
        showBlockOverlay(`⚠️ CONFIRME SEU SALDO REAL\n\nVá para HOME ou DEPÓSITO`);
        return;
      }

      hideBlockOverlay();
    });

    return () => {
      stopAccessibilityListener();
      resetStability();
      hideBlockOverlay();
    };
  }, []);

  return engine;
}
