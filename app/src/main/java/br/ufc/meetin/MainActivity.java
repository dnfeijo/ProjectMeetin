package br.ufc.meetin;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageButton;
import android.content.Intent;
import android.os.Bundle;

import br.ufc.meetin.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private ImageButton initButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButton = findViewById(R.id.startingButton);
        initButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });
    }

    public void openLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void teste(View view){
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute("login", "Katharine", "12345");
    }
}
