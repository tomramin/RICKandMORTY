package tom.r.rickandmorty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.*;
import static tom.r.rickandmorty.MainActivity.EXTRA_NAME;
import static tom.r.rickandmorty.MainActivity.EXTRA_STATUS;
import static tom.r.rickandmorty.MainActivity.EXTRA_SPECIES;
import static tom.r.rickandmorty.MainActivity.EXTRA_GENDER;
import static tom.r.rickandmorty.MainActivity.EXTRA_URL;

public class CharacterDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);

        //modif
        Intent intent = getIntent();
        String characterName = intent.getStringExtra(EXTRA_NAME);
        String characterStatus = intent.getStringExtra(EXTRA_STATUS);
        String characterSpecies = intent.getStringExtra(EXTRA_SPECIES);
        String characterGender = intent.getStringExtra(EXTRA_GENDER);
        String characterImage = intent.getStringExtra(EXTRA_URL);

        TextView textViewName = findViewById(R.id.name);
        TextView textViewStatus = findViewById(R.id.status);
        TextView textViewSpecies = findViewById(R.id.species);
        TextView textViewGender = findViewById(R.id.gender);
        ImageView imageView = findViewById(R.id.photoImageView);

        Picasso.get().load(characterImage).into(imageView);
        textViewName.setText(characterName);
        textViewStatus.setText("Status: "+characterStatus);
        textViewSpecies.setText("Species: "+characterSpecies);
        textViewGender.setText("Gender: "+characterGender);


    }
}
