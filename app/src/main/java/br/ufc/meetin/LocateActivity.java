package br.ufc.meetin;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.awareness.FenceClient;
import com.google.android.gms.awareness.fence.AwarenessFence;
import com.google.android.gms.awareness.fence.DetectedActivityFence;
import com.google.android.gms.awareness.fence.FenceUpdateRequest;
import com.google.android.gms.awareness.fence.HeadphoneFence;
import com.google.android.gms.awareness.fence.LocationFence;
import com.google.android.gms.awareness.state.HeadphoneState;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import androidx.appcompat.app.AppCompatActivity;
import br.ufc.meetin.actions.ToastAction;
import br.ufc.meetin.R;


public class LocateActivity extends AppCompatActivity implements OnSuccessListener<Void>, OnFailureListener{
    final int REQUEST_LOCATION = 1;
    EditText latitudeEscolha;
    EditText longitudeEscolha;
    EditText radiusEscolha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate);

    }

    public void permissionFence(View view){
        if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            setFence();
        } else{
            if(shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)){
                Toast.makeText(this, "Para funcionar, precisa de localização", Toast.LENGTH_SHORT).show();
            }

            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        if (requestCode == REQUEST_LOCATION) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setFence();
            } else {
                Toast.makeText(this, "Permissão não concedida", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void setFence(){
        latitudeEscolha = findViewById(R.id.latitude);
        longitudeEscolha = findViewById(R.id.longitude);
        radiusEscolha = findViewById(R.id.radius);

        double latitudeNumber = Double.parseDouble(latitudeEscolha.getText().toString());
        double longitudeNumber = Double.parseDouble(longitudeEscolha.getText().toString());
        double radiusNumber = Double.parseDouble(radiusEscolha.getText().toString());

        AwarenessFence inPlace = LocationFence.in(latitudeNumber, longitudeNumber, radiusNumber, 10);;

        //Filtros de Intent
        IntentFilter ip = new IntentFilter("inPlace");
        //Registrar Receivers (actions) na pilha do Android
        registerReceiver(new ToastAction(), ip);
        //Registrar PendingIntents getBroadcast com os filtros criados
        PendingIntent pi = PendingIntent.getBroadcast(this,123,new Intent("inPlace"),PendingIntent.FLAG_CANCEL_CURRENT);
        //Registro de Fences no Google Awareness API
        FenceClient fc = Awareness.getFenceClient(this);
        fc.updateFences(new FenceUpdateRequest.Builder().addFence("InPlace",inPlace,pi).build())
                .addOnSuccessListener(this)
                .addOnFailureListener(this);
        Toast.makeText(this, "É pra dar certo", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(Void aVoid) {
        Toast.makeText(this, "Fence registrada com sucesso", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(@NonNull Exception e) {
        Toast.makeText(this, "Houve um erro ao registrar fence", Toast.LENGTH_SHORT).show();
        Log.e("LocateActivity", "onFailure: Houve um erro ao registrar fence", e);
    }

}
