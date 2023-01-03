package com.example.myflowers;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class KukkaInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kukka_information);
        TextView nimiTxtView = findViewById(R.id.nimiTextView);
        ImageView kuvaImgView = findViewById(R.id.kuvaImageView);
        TextView kuvausTxtView = findViewById(R.id.kuvausTextView);

        String kukkaNimi = "username not set";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            kukkaNimi = extras.getString("kukkaNimi");
        }

        String kukka = kukkaNimi;
        nimiTxtView.setText(kukkaNimi);
        kuvaImgView.setImageResource(lataaKukanKuva(kukka));
        kuvausTxtView.setText(lataaKukanTiedot(kukka));




        //lataaKukanKuva((String) nimiTxtView.getText());

        //if (kukkaNimi == "Kaktus") {
        //    kuvaImgView.setImageResource(R.drawable.kukka1);
        //    kuvausTxtView.setText(R.string.Kaktus);
        //}
        //else if (kukkaNimi == "Kielo") {
        //    kuvaImgView.setImageResource(R.drawable.kukka2);
        //    kuvausTxtView.setText(R.string.Kielo);
        //}




    }

    private int lataaKukanTiedot(String kukka) {
        if (kukka.equals("Kaktus")) return R.string.Kaktus;
        else if (kukka.equals("Kielo")) return R.string.Kielo;
        else if (kukka.equals("Sinikello")) return R.string.Sinikello;
        else if (kukka.equals("Myrkkymuratti")) return R.string.Myrkkymuratti;
        else if (kukka.equals("Auringonkukka")) return R.string.Auringonkukka;
        else if (kukka.equals("Narsissi")) return R.string.Narsissi;
        else if (kukka.equals("Rentunruusu")) return R.string.Rentunruusu;
        else if (kukka.equals("Jukka palmu")) return R.string.Jukkapalmu;
        else if (kukka.equals("Yukka palmu")) return R.string.Yukkapalmu;
        else if (kukka.equals("Hortenssia")) return R.string.Hortenssia;
        else if (kukka.equals("Peikko")) return R.string.Peikko;
        else if (kukka.equals("Nukkumatti")) return R.string.Nukkumatti;
        else return R.string.eiOle;
    };

    private int lataaKukanKuva(String kukka) {
        if (kukka.equals("Kaktus")) return R.drawable.kaktus;
        else if (kukka.equals("Kielo")) return R.drawable.kielo;
        else if (kukka.equals("Sinikello")) return R.drawable.sinikello;
        else if (kukka.equals("Myrkkymuratti")) return R.drawable.myrkkymuratti;
        else if (kukka.equals("Auringonkukka")) return R.drawable.auringonkukka;
        else if (kukka.equals("Narsissi")) return R.drawable.narsissi;
        else if (kukka.equals("Rentunruusu")) return R.drawable.rentunruusu;
        else if (kukka.equals("Jukka palmu")) return R.drawable.jukkapalmu;
        else if (kukka.equals("Yukka palmu")) return R.drawable.yukkapalmu;
        else if (kukka.equals("Hortenssia")) return R.drawable.hortenssia;
        else if (kukka.equals("Peikko")) return R.drawable.peikko;
        else if (kukka.equals("Nukkumatti")) return R.drawable.nukkumatti;
        else return R.drawable.kaktus;
    };
}
