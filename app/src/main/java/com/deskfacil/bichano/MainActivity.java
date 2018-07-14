package com.deskfacil.bichano;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
//public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
    private static HashMap<String,Integer> listAnimals = new HashMap<String, Integer>();

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
            //Toast.makeText(getApplicationContext(), "Ouvindo "+string, Toast.LENGTH_LONG).show();

            textView.setText("Ouvindo "+string+" ...");
            mediaPlayer = MediaPlayer.create(MainActivity.this, idAnimal);
            //mediaPlayer = MediaPlayer.create(MainActivity.this, MainActivity.this.listAnimals.get(string));
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

        /*listAnimals.put("cao",R.raw.cao);
        listAnimals.put("gato",R.raw.gato);
        listAnimals.put("vaca",R.raw.vaca);
        listAnimals.put("ovelha",R.raw.ovelha);
        listAnimals.put("leao",R.raw.leao);
        listAnimals.put("macaco",R.raw.macaco);*/

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

        // for loop
        /*Log.d(TAG, "For Loop:");
        for(Map.Entry linha : listAnimals.entrySet())
        {
            //System.out.println("chave: "+linha.getKey()+" valor: "+linha.getValue());
            //Log.d(TAG,"chave: "+linha.getKey()+" valor: "+linha.getValue());
        }*/

        // while loop
        /*Log.d(TAG, "While Loop:");
        Iterator iterator = listAnimals.entrySet().iterator();
        while (iterator.hasNext())
        {
            Map.Entry me2 = (Map.Entry) iterator.next();
            //Log.d(TAG, "Chave: "+me2.getKey() + " - Valor: " + me2.getValue());
        }*/


        /*fechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });*/
    }

    @Override
    protected void onStop()
    {
        Toast.makeText(getApplicationContext(), "Parei o bichano", Toast.LENGTH_SHORT).show();
        super.onStop();
    }

    @Override
    protected void onResume()
    {
        Toast.makeText(getApplicationContext(), "O bichano tá pronto", Toast.LENGTH_SHORT).show();
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

        Toast.makeText(getApplicationContext(), "Destrui o bichano", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }
}
