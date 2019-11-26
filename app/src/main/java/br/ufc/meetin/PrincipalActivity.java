package br.ufc.meetin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_principal);

        visitedCommunities = (ListView) findViewById(R.id.visited_communities);

        opcoesVisited = new ArrayList<String>();

        opcoesVisited.add("SANA - Sala de Exibição");
        opcoesVisited.add("SANA - KPOP");

        adaptadorVisited = new ArrayAdapter(PrincipalActivity.this, android.R.layout.simple_list_item_1, opcoesVisited);
        visitedCommunities.setAdapter(adaptadorVisited);

        foundCommunities = (ListView) findViewById(R.id.found_communities);

        opcoesFound = new ArrayList<String>();

        opcoesFound.add("SANA - Cosplay");
        opcoesFound.add("SANA - RPG/TCG");
        opcoesFound.add("SANA - Geral");

        adaptadorFound = new ArrayAdapter(PrincipalActivity.this, android.R.layout.simple_list_item_1, opcoesFound);
        foundCommunities.setAdapter(adaptadorFound);

        createdCommunities = (ListView) findViewById(R.id.created_communities);

        opcoesCreated = new ArrayList<String>();

        opcoesCreated.add("Comunidade criada");

        adaptadorCreated = new ArrayAdapter(PrincipalActivity.this, android.R.layout.simple_list_item_1, opcoesCreated);
        createdCommunities.setAdapter(adaptadorCreated);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public void openCreateEventActivity(View view) {
        Intent intent = new Intent(this, CreateEventActivity.class);
        startActivity(intent);
    }

    public void openConfigurations(View view) {
        Intent intent = new Intent(this, ConfigurationsActivity.class);
        startActivity(intent);
    }

    public void openLocate(View view) {
        Intent intent = new Intent(this, LocateActivity.class);
        startActivity(intent);
    }
}