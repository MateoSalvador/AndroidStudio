package ec.epn.detri.awm.ppq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_ULTIMO_DIGITO = "extra_last";
    private Button btnConsultar;
    private Placa placa;
    private TextView txtPlaca;
    private TextView lblMessage;

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtPlaca = this.findViewById(R.id.txtPlaca);
        lblMessage = this.findViewById(R.id.lblMessage);
    }
    /**
     * Método que retorna un intent para disparar la actividad de consulta de pico y placa
     */
    private Intent generarIntentConsulta() {
        placa = new Placa(txtPlaca.getText().toString());
        if (!placa.placaEsParticular(txtPlaca.toString())){
            lblMessage.setVisibility(View.VISIBLE);
            lblMessage.setText("Placa privada incorrecta");
        }
        else{
            lblMessage.setVisibility(View.VISIBLE);
            lblMessage.setText("Placa privada correcta");
        }

        Intent intent = new Intent(this, ActividadResultado.class);
        intent.putExtra(EXTRA_ULTIMO_DIGITO, placa.getLast().toString());
        return intent;
    }

    public void mostrarPantallaConsulta(View view) {
        startActivity(generarIntentConsulta());
        Log.d(TAG, "Realizando consulta para la placa: " + placa.getLast());
    }
}