package br.ufc.meetin.actions;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.awareness.fence.FenceState;

//TALVEZ DELETAR
public class ToastAction extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Localizando", Toast.LENGTH_SHORT).show();
        int state = FenceState.extract(intent).getCurrentState();
        switch (state){
            case FenceState.TRUE:
                Toast.makeText(context, "Você está no local.", Toast.LENGTH_SHORT).show();
                break;
            case FenceState.FALSE:
                Toast.makeText(context, "Você não está no local", Toast.LENGTH_SHORT).show();
                break;
            case FenceState.UNKNOWN:
                Toast.makeText(context, "?????", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
