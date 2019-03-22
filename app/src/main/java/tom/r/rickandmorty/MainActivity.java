package tom.r.rickandmorty;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import tom.r.rickandmorty.Adapter.MyAdapter;
import tom.r.rickandmorty.Model.Character;



public class MainActivity extends AppCompatActivity implements MyAdapter.OnItemClickListener{
    //modif
    public static final String EXTRA_NAME = "characterName";
    public static final String EXTRA_STATUS = "characterStatus";
    public static final String EXTRA_SPECIES = "characterSpecies";
    public static final String EXTRA_GENDER = "characterGenre";
    public static final String EXTRA_URL = "characterImage";

    MediaPlayer mySound;

    @Override
    protected void onPause() {
        super.onPause();
        mySound.release();
    }

    private static final String URL_DATA = "https://rickandmortyapi.com/api/character/";
    private RecyclerView recyclerView;
    //private RecyclerView.Adapter adapter;
    //modif
    private MyAdapter myAdapter;

    private List<Character> characterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = /*(RecyclerView)*/ findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        characterList = new ArrayList<>();

        loadRecyclerViewData();

        mySound = MediaPlayer.create(this, R.raw.song);
    }

    public void playMusic(View view) {
        mySound.start();
    }

    //modif
    /*
    public static String remover(String oldString){
        String newString = oldString.replace(',','-');
        newString = newString.replace('"',' ');
        newString = newString.replace("[","");
        newString = newString.replace("]","");
        newString = newString.replace("(Male)","");
        newString = newString.replace("(Female)","");

        return newString;
    }*/

    private void loadRecyclerViewData(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data ...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("results");

                            for(int i=0; i<array.length();i++){
                                JSONObject object = array.getJSONObject(i);
                                Character character = new Character(
                                        object.getString("name"),
                                        object.getString("status"),
                                        object.getString("species"),
                                        object.getString("gender"),
                                        object.getString("image")
                                );
                                characterList.add(character);
                            }
                            //avant
                            //adapter = new MyAdapter(characterList,getApplicationContext());
                            //recyclerView.setAdapter(adapter);

                            //modif
                            myAdapter = new MyAdapter(characterList,MainActivity.this);
                            recyclerView.setAdapter(myAdapter);
                            myAdapter.setOnItemClickListener(MainActivity.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                     }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    //modif onitemclick
    @Override
    public void onItemClick(int i) {
        Intent detailIntent = new Intent(this, CharacterDetail.class);
        Character clickedItem = characterList.get(i);

        detailIntent.putExtra(EXTRA_NAME, clickedItem.getName());
        detailIntent.putExtra(EXTRA_STATUS, clickedItem.getStatus());
        detailIntent.putExtra(EXTRA_SPECIES, clickedItem.getSpecies());
        detailIntent.putExtra(EXTRA_GENDER, clickedItem.getGender());
        detailIntent.putExtra(EXTRA_URL, clickedItem.getImage());

        startActivity(detailIntent);
    }
}
