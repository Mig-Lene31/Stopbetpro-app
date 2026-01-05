import auth from '@react-native-firebase/auth';

const API_URL = 'http://192.168.1.127:3000';

export async function createPixPayment(value) {
  const user = auth().currentUser;
  if (!user) throw new Error('Usuário não autenticado');

  const res = await fetch(\`\${API_URL}/pix/create\`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({
      uid: user.uid,
      value,
    }),
  });

  return await res.json();
}
