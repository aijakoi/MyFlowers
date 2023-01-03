package com.example.myflowers;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kukka_information);
        TextView nimiTxt = findViewById(R.id.nimiTextView);
        ImageView kuvaImg = findViewById(R.id.kuvaImageView);
        TextView kuvausTxt = findViewById(R.id.kuvausTextView);

        String kukanNimi = "Nimeä ei saatavilla";
        int kukanKuva = -1;
        int kukanKuvaus = -1;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            kukanNimi = extras.getString("kukanNimi");
            kukanKuva = extras.getInt("kukanKuva");
            kukanKuvaus = extras.getInt("kukanKuvaus");
        }

        nimiTxt.setText(kukanNimi);
        kuvaImg.setImageResource(kukanKuva);
        kuvausTxt.setText(kukanKuvaus);
    }
}
