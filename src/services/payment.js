import { apiRequest } from './api';
import { BLOCK_UNLOCK_PRICE } from '../config/constants';

export async function createUnlockPayment() {
  return await apiRequest(
    '/payments/create',
    'POST',
    {
      amount: BLOCK_UNLOCK_PRICE,
      currency: 'BRL',
      type: 'unlock'
    }
  );
}

export async function confirmPayment() {
  return await apiRequest(
    '/payments/confirm',
    'POST'
  );
}
