import auth from '@react-native-firebase/auth';

export async function register(email, password) {
  const res = await auth().createUserWithEmailAndPassword(email, password);
  return res.user;
}

export async function login(email, password) {
  const res = await auth().signInWithEmailAndPassword(email, password);
  return res.user;
}

export function logout() {
  return auth().signOut();
}

export function onAuthChange(callback) {
  return auth().onAuthStateChanged(callback);
}

export function currentUser() {
  return auth().currentUser;
}
