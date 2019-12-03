package br.ufc.meetin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationsActivity extends AppCompatActivity {

    Toolbar toolbar;

    List<String> opcoesGeneralSettings;
    ArrayAdapter<String> adaptadorGeneralSettings;
    ListView generalSettings;

    List<String> opcoesPrivacySettings;
    ArrayAdapter<String> adaptadorPrivacySettings;
    ListView privacySettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurations);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        generalSettings = (ListView) findViewById(R.id.general_settings);

        opcoesGeneralSettings = new ArrayList<String>();

        opcoesGeneralSettings.add("Mudar Foto");
        opcoesGeneralSettings.add("Mudar número de telefone");
        opcoesGeneralSettings.add("Mudar email");
        opcoesGeneralSettings.add("Mudar apelido");

        adaptadorGeneralSettings = new ArrayAdapter(ConfigurationsActivity.this, android.R.layout.simple_list_item_1, opcoesGeneralSettings);
        generalSettings.setAdapter(adaptadorGeneralSettings);

        privacySettings = (ListView) findViewById(R.id.privacy_settings);

        opcoesPrivacySettings = new ArrayList<String>();

        opcoesPrivacySettings.add("Usuários Bloqueados");
        opcoesPrivacySettings.add("Mostrar seu número?");

        adaptadorPrivacySettings= new ArrayAdapter(ConfigurationsActivity.this, android.R.layout.simple_list_item_1, opcoesPrivacySettings);
        privacySettings.setAdapter(adaptadorPrivacySettings);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
