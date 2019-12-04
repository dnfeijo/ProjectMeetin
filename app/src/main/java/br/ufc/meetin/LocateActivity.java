package br.ufc.meetin;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
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
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import br.ufc.meetin.actions.InsertListAction;
import br.ufc.meetin.actions.ToastAction;
import br.ufc.meetin.R;
import br.ufc.meetin.data.Event;


public class LocateActivity extends AppCompatActivity implements OnSuccessListener<Void>, OnFailureListener{
    private static LocateActivity ins;
    Toolbar toolbar;
    //final int REQUEST_LOCATION = 1;
    EditText latitudeEscolha;
    EditText longitudeEscolha;
    EditText radiusEscolha;

    List<String> optionsNearEvents;
    ArrayAdapter<String> adapterNear;
    ListView nearEvents;

    Event event1 = new Event("SANA", -3.7274, -38.5093);
    Event event2 = new Event("KPOP", -30.7274, -3.5093);
    Event event3 = new Event("BIENAL", -3.7274, -38.5093);
    Event[] events = {event1, event2, event3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate);

        ins = this;

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setFence();
    }

    public void setFence(){
        latitudeEscolha = findViewById(R.id.latitude);
        longitudeEscolha = findViewById(R.id.longitude);
        radiusEscolha = findViewById(R.id.radius);

        ArrayList<AwarenessFence> inPlace = new ArrayList<>();
        ArrayList<PendingIntent> piList = new ArrayList<>();
        for(int i = 0; i<events.length; i++){
            inPlace.add(LocationFence.in(events[i].getLatitude(), events[i].getLongitude(), 50, 10));
            //Filtros de Intent
            IntentFilter ip = new IntentFilter("inPlace");
            //Registrar Receivers (actions) na pilha do Android
            registerReceiver(new InsertListAction(), ip);
            //Registrar PendingIntents getBroadcast com os filtros criados
            piList.add(PendingIntent.getBroadcast(this,100+i, new Intent("inPlace").putExtra("EventName",events[i].getName()),PendingIntent.FLAG_ONE_SHOT));
        }
        //Registro de Fences no Google Awareness API
        FenceClient fc = Awareness.getFenceClient(this);
        FenceUpdateRequest.Builder builder = new FenceUpdateRequest.Builder();
        for(int i = 0; i<inPlace.size(); i++){
            builder.addFence("InPlace" + (char) i, inPlace.get(i), piList.get(i));
        }
        fc.updateFences(builder
                .build())
                .addOnSuccessListener(this)
                .addOnFailureListener(this);
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

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public ListView getEventsListView() {
       return (ListView) findViewById(R.id.near_events_list);
    }

    public static LocateActivity getInstance() {
        return ins;
    }
}
