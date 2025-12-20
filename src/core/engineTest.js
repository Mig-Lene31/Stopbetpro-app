import StopEngine from './StopEngine.js';

console.log('=== TESTE STOP ENGINE ===');

// Configuração
const engine = new StopEngine({
  deposit: 100,
  stopWin: 50,      // +50 de lucro
  stopLoss: 40,     // -40 de prejuízo
  maxTime: 5,       // 5 segundos (teste rápido)
  blockDuration: 10 // 10 segundos de bloqueio (teste)
});

// 1️⃣ Saldo inicial
console.log('Saldo inicial:', engine.checkBalance(100));

// 2️⃣ Pequeno lucro (não bloqueia)
console.log('Lucro leve:', engine.checkBalance(120));

// 3️⃣ STOP WIN
console.log('STOP WIN:', engine.checkBalance(160));

// 4️⃣ Verifica bloqueio ativo
console.log('Está bloqueado?', engine.isBlocked());

// 5️⃣ Desbloqueio antecipado (pagamento)
engine.unlock();
console.log('Desbloqueado manualmente:', engine.isBlocked());

// 6️⃣ Teste STOP LOSS
engine.checkBalance(100); // redefinir base
console.log('STOP LOSS:', engine.checkBalance(50));

// 7️⃣ Teste tempo
setTimeout(() => {
  console.log('TIME LIMIT:', engine.checkTime());
  console.log('Bloqueado por tempo?', engine.isBlocked());
}, 6000);
