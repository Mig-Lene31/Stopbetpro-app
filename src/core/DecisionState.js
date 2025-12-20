let state = {
  lastBalance: null,
  stableCount: 0,
  blocked: false,
  pendingReason: null,
  lastDecisionAt: null
};

export function getState() {
  return state;
}

export function updateState(partial) {
  state = { ...state, ...partial };
}

export function resetState() {
  state = {
    lastBalance: null,
    stableCount: 0,
    blocked: false,
    pendingReason: null,
    lastDecisionAt: null
  };
}
