package br.ufc.meetin;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    final int REQUEST_LOCATION = 1;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    List<String> opcoesVisited;
    ArrayAdapter<String> adaptadorVisited;
    ListView visitedCommunities;

    List<String> opcoesFound;
    ArrayAdapter<String> adaptadorFound;
    ListView foundCommunities;

    List<String> opcoesCreated;
    ArrayAdapter<String> adaptadorCreated;
    ListView createdCommunities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById((R.id.navigation_view));

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        visitedCommunities = (ListView) findViewById(R.id.visited_communities);

        opcoesVisited = new ArrayList<String>();

        opcoesVisited.add("SANA - Sala de Exibição");
        opcoesVisited.add("SANA - KPOP");

        adaptadorVisited = new ArrayAdapter<String>(PrincipalActivity.this, android.R.layout.simple_list_item_1, opcoesVisited);
        visitedCommunities.setAdapter(adaptadorVisited);
        setListViewHeightBasedOnChildren(visitedCommunities);

    }

    public void permissionLocate(MenuItem menuItem){
        if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            openLocate();
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
                openLocate();
            } else {
                Toast.makeText(this, "Permissão não concedida", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void openCreateEventActivity(MenuItem menuItem) {
        Intent intent = new Intent(this, CreateEventActivity.class);
        startActivity(intent);
    }

    public void openConfigurations(MenuItem menuItem) {
        Intent intent = new Intent(this, ConfigurationsActivity.class);
        startActivity(intent);
    }

    public void openLocate() {
        Intent intent = new Intent(this, LocateActivity.class);
        startActivity(intent);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            if (listItem instanceof ViewGroup) {
                listItem.setLayoutParams(new ListView.LayoutParams(ListView.LayoutParams.WRAP_CONTENT, ListView.LayoutParams.WRAP_CONTENT));
            }
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount()) - 1);
        listView.setLayoutParams(params);
    }
}