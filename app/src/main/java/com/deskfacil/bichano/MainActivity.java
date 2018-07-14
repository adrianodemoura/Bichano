package com.deskfacil.bichano;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ImageView cao;
    private ImageView gato;
    private ImageView vaca;
    private ImageView ovelha;
    private ImageView leao;
    private ImageView macaco;
    private ImageView fechar;
    private TextView textView;
    private MediaPlayer mediaPlayer;
    private static final String TAG = "debugBichano";

    private View.OnClickListener fecharApp = new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            Toast.makeText(getApplicationContext(), "Fechei o bichano", Toast.LENGTH_LONG).show();

            finishAffinity();
            System.exit(0);
        }
    };

    private View.OnClickListener tocaPlay = new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            String string = view.getResources().getResourceEntryName(view.getId()).replace("img","").toLowerCase();

            int idAnimal;
            idAnimal = getResources().getIdentifier(string,"raw",getPackageName());

            textView.setText("Ouvindo "+string+" ...");
            mediaPlayer = MediaPlayer.create(MainActivity.this, idAnimal);
            mediaPlayer.start();

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
            {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer)
                {
                    textView.setText(R.string.txt_escolhe_animal);
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textId);

        fechar = findViewById(R.id.imgClose);
        fechar.setOnClickListener(fecharApp);

        cao     = findViewById(R.id.imgCao);
        gato    = findViewById(R.id.imgGato);
        vaca    = findViewById(R.id.imgVaca);
        ovelha  = findViewById(R.id.imgOvelha);
        leao    = findViewById(R.id.imgLeao);
        macaco  = findViewById(R.id.imgMacaco);

        cao.setOnClickListener(tocaPlay);
        gato.setOnClickListener(tocaPlay);
        vaca.setOnClickListener(tocaPlay);
        ovelha.setOnClickListener(tocaPlay);
        leao.setOnClickListener(tocaPlay);
        macaco.setOnClickListener(tocaPlay);

    }

    @Override
    protected void onStop()
    {
        Toast.makeText(getApplicationContext(), R.string.bichano_parada, Toast.LENGTH_SHORT).show();
        super.onStop();
    }

    @Override
    protected void onResume()
    {
        Toast.makeText(getApplicationContext(), R.string.bichano_pronto, Toast.LENGTH_SHORT).show();
        fechar.setVisibility(1);
        super.onResume();
    }

    @Override
    protected void onDestroy() {

        if (mediaPlayer.isPlaying())
        {
            mediaPlayer.release();
            mediaPlayer = null;
        }

        Toast.makeText(getApplicationContext(), R.string.bichano_destruido, Toast.LENGTH_LONG).show();
        super.onDestroy();
    }
}
