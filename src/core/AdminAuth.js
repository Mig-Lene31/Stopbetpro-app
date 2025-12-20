export function getTodayPassword() {
  const SECRET = 'Mi$';
  const d = new Date();
  const day = String(d.getDate()).padStart(2, '0');
  const month = String(d.getMonth() + 1).padStart(2, '0');
  return `${day}${month}${SECRET}`;
}

export function validateAdminPassword(input) {
  return input === getTodayPassword();
}
