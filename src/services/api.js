import { API_URL } from "../config";

export async function getStatus(uid) {
  return fetch(`${API_URL}/user/status/${uid}`).then(r => r.json());
}

export async function updateControls(uid, stopWin, stopLoss, blockMinutes) {
  return fetch(`${API_URL}/user/update-controls`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ uid, stopWin, stopLoss, blockMinutes })
  }).then(r => r.json());
}

export async function blockUser(uid, minutes) {
  return fetch(`${API_URL}/user/block`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ uid, minutes })
  }).then(r => r.json());
}

export async function unlockPaid(uid) {
  return fetch(`${API_URL}/user/unlock-paid`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ uid })
  }).then(r => r.json());
}
