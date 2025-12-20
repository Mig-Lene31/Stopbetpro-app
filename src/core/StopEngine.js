export default class StopEngine {
  constructor(config) {
    this.deposit = config.deposit ?? null;

    this.stopWin = config.stopWin ?? null;
    this.stopLoss = config.stopLoss ?? null;

    this.maxTime = config.maxTime ?? null; // segundos
    this.blockDuration = config.blockDuration ?? 12 * 60 * 60; // 12 horas

    this.startTime = Date.now();
    this.initialBalance = null;
    this.blockedAt = null;
  }

  // Define o saldo inicial detectado (OCR / Accessibility)
  setInitialBalance(balance) {
    if (this.initialBalance === null) {
      this.initialBalance = balance;
    }
  }

  // Verifica saldo atual
  checkBalance(currentBalance) {
    if (this.initialBalance === null) {
      this.setInitialBalance(currentBalance);
      return { block: false };
    }

    const profit = currentBalance - this.initialBalance;

    // STOP WIN (lucro)
    if (this.stopWin !== null && profit >= this.stopWin) {
      return this.block("STOP_WIN");
    }

    // STOP LOSS (prejuízo)
    if (this.stopLoss !== null && profit <= -Math.abs(this.stopLoss)) {
      return this.block("STOP_LOSS");
    }

    return { block: false };
  }

  // Verifica tempo máximo de sessão
  checkTime() {
    if (this.maxTime !== null) {
      const elapsed = (Date.now() - this.startTime) / 1000;
      if (elapsed >= this.maxTime) {
        return this.block("TIME_LIMIT");
      }
    }
    return { block: false };
  }

  // Executa o bloqueio
  block(reason) {
    if (!this.blockedAt) {
      this.blockedAt = Date.now();
    }

    return {
      block: true,
      reason,
      blockedUntil: this.blockedAt + this.blockDuration * 1000,
    };
  }

  // Verifica se ainda está bloqueado
  isBlocked() {
    if (!this.blockedAt) return false;
    return Date.now() < this.blockedAt + this.blockDuration * 1000;
  }

  // Liberação antecipada (pagamento)
  unlock() {
    this.blockedAt = null;
    this.startTime = Date.now();
    this.initialBalance = null;
  }
}
