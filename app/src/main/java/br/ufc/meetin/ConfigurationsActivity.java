package br.ufc.meetin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationsActivity extends AppCompatActivity {

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

        generalSettings = (ListView) findViewById(R.id.general_settings);

        opcoesGeneralSettings = new ArrayList<String>();

        opcoesGeneralSettings.add("Change Photo");
        opcoesGeneralSettings.add("Change Phone Number");
        opcoesGeneralSettings.add("Change Email");
        opcoesGeneralSettings.add("Change Nickname");

        adaptadorGeneralSettings = new ArrayAdapter(ConfigurationsActivity.this, android.R.layout.simple_list_item_1, opcoesGeneralSettings);
        generalSettings.setAdapter(adaptadorGeneralSettings);

        privacySettings = (ListView) findViewById(R.id.privacy_settings);

        opcoesPrivacySettings = new ArrayList<String>();

        opcoesPrivacySettings.add("Blocked Users");
        opcoesPrivacySettings.add("Show User Number");

        adaptadorPrivacySettings= new ArrayAdapter(ConfigurationsActivity.this, android.R.layout.simple_list_item_1, opcoesPrivacySettings);
        privacySettings.setAdapter(adaptadorPrivacySettings);
    }
}
