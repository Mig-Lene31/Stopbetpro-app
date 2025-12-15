import { NativeModules } from 'react-native';

const { VpnPermission } = NativeModules;

export function startBetBlockVpn() {
  if (!VpnPermission) {
    console.log('❌ VPN module não encontrado');
    return;
  }

  VpnPermission.requestPermission();
}
