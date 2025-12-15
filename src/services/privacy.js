import Storage from './storage';
import { APP_VERSION } from '../config/constants';

const PRIVACY_VERSION = '1.0';

export async function acceptPrivacyTerms() {
  const payload = {
    accepted: true,
    privacyVersion: PRIVACY_VERSION,
    appVersion: APP_VERSION,
    acceptedAt: new Date().toISOString()
  };

  await Storage.set('privacyAccepted', true);
  await Storage.set('privacyMeta', payload);

  return payload;
}

export async function getPrivacyStatus() {
  const accepted = await Storage.get('privacyAccepted');
  const meta = await Storage.get('privacyMeta');

  return {
    accepted: accepted === true,
    meta
  };
}
