package ec.epn.detri.awm.ppq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ActividadResultado extends AppCompatActivity {

    private TextView lblDia;
    private TextView lblResultado;
    private Button btnParking;
    private Boolean puedoCircular;
    private Integer ultimoDigito;

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_resultado);
        lblDia = this.findViewById(R.id.lblDia);
        lblResultado = this.findViewById(R.id.lblResultado);
        btnParking = this.findViewById(R.id.btnParking);
        Log.d(TAG, getIntent().getStringExtra(MainActivity.EXTRA_ULTIMO_DIGITO));
        ultimoDigito = Integer.parseInt(getIntent().getStringExtra(MainActivity.EXTRA_ULTIMO_DIGITO));


    }
    protected void onResume() {
        super.onResume();
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        String diaName = sdf.format(d);
        lblDia.setText(diaName);
        puedoCircular = Placa.puedoCircular( Calendar.getInstance().get(Calendar.DAY_OF_WEEK),ultimoDigito);
        Log.d(TAG, "Puedo circular? "+ puedoCircular);
        if (puedoCircular) {
            lblResultado.setText("Sí puedes circular :)");
            btnParking.setVisibility(View.INVISIBLE);
        }else{
            lblResultado.setText("No puedes circular :(");
            btnParking.setVisibility(View.VISIBLE);
        }
    }


    /**
     * Método que retorna un intent para disparar una app de mapas.
     */
    private Intent generarIntentMapas(String address) {
        return new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + Uri.encode(address)));
    }

    /**
     * Método que retorna un intent para disparar una app de navegación (Browser)
     */
    private Intent generarIntentBrowser(String address) {
        // ¿Intent implícito o explícito?

        // Crear el intent
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.google.com/?q="
                + Uri.encode(address)));

        // Abrir la actividad como una nueva tarea ¿Qué campo de un intent se emplea para definir esta restricción?
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        return intent;
    }

    public void mostrarPantallaMaps(View view) {
        String address = "Parqueadero de borde, Quito, Ecuador";
        try{
            final Intent geoIntent = generarIntentMapas(address);

            // Verificar si existe una aplicación para manejar el intent "geo".
            if (geoIntent.resolveActivity(getPackageManager()) != null)
                // Iniciar la app de mapas
                startActivity(geoIntent);
            else
                // Iniciar la app de navegación (browser)
                startActivity(generarIntentBrowser(address));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void nuevaConsulta(View view){
        startActivity(new Intent(this, MainActivity.class));
    }

    public String getName() {
        return null;
    }
    public int getNumber(int n) {
        return 1;
    }
}