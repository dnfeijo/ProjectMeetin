package br.ufc.meetin.actions;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.google.android.gms.awareness.fence.FenceState;

import java.util.ArrayList;
import java.util.List;

import br.ufc.meetin.LocateActivity;
import br.ufc.meetin.R;
import br.ufc.meetin.data.Event;

public class InsertListAction extends BroadcastReceiver {

    List<String> optionsNearEvents = new ArrayList<String>();
    ArrayAdapter<String> adapterNear;
    ListView nearEvents;

    @Override
    public void onReceive(Context context, Intent intent) {

        int state = FenceState.extract(intent).getCurrentState();
        switch (state) {
            case FenceState.TRUE:
                Bundle extras = intent.getExtras();
                if(extras != null) {
                    optionsNearEvents.add(extras.getString("EventName"));
                }
                break;

            case FenceState.FALSE:
                break;

            case FenceState.UNKNOWN:
                break;
        }

        nearEvents = LocateActivity.getInstance().getEventsListView();

        adapterNear = new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1,
                optionsNearEvents);
        nearEvents.setAdapter(adapterNear);
    }
}
