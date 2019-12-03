package br.ufc.meetin;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.Menu;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

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

    }

    public void openCreateEventActivity(MenuItem menuItem) {
        Intent intent = new Intent(this, CreateEventActivity.class);
        startActivity(intent);
    }

    public void openConfigurations(MenuItem menuItem) {
        Intent intent = new Intent(this, ConfigurationsActivity.class);
        startActivity(intent);
    }

    public void openLocate(MenuItem menuItem) {
        Intent intent = new Intent(this, LocateActivity.class);
        startActivity(intent);
    }
}