ackage com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class GateActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inicializações mínimas defensivas
        AppPrefs.ensureDefaults(this);
        MotorState.ensureDefaults(this);

        // Se estiver bloqueado, vai direto pra tela azul
        if (EngineState.isBlocked(this)) {
            startActivity(new Intent(this, BlockedActivity.class));
            finish();
            return;
        }

        // Fluxo normal
        startActivity(new Intent(this, PaymentActivity.class));
        finish();
    }
}
