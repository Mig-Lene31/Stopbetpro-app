import { useEffect } from 'react';
import { get, set } from '../services/storage';

export default function useBlockNavigator(navigation) {
  useEffect(() => {
    let active = true;

    async function checkNavigation() {
      while (active) {
        const shouldNavigate = await get('navigateToConfirm');

        if (shouldNavigate === true) {
          await set('navigateToConfirm', false);
          navigation.navigate('ConfirmBlock');
        }

        await sleep(1000);
      }
    }

    checkNavigation();

    return () => {
      active = false;
    };
  }, [navigation]);
}

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}
