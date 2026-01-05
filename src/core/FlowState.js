import Storage from '../services/storage';

export const FLOW_KEYS = {
  LEGAL: 'legalAccepted',
  ACCESS: 'access_status'
};

export const ACCESS_STATUS = {
  WAITING: 'WAITING',
  LIBERADO: 'LIBERADO'
};

export async function getFlowState() {
  const legal = await Storage.get(FLOW_KEYS.LEGAL);
  const access = await Storage.get(FLOW_KEYS.ACCESS);

  return {
    legalAccepted: !!legal,
    accessStatus: access || ACCESS_STATUS.WAITING
  };
}
