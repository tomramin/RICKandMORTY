package tom.r.rickandmorty.View;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
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
import tom.r.rickandmorty.R;


/**
 * @author Tom
 */
public class MainActivity extends AppCompatActivity implements MyAdapter.OnItemClickListener, SearchView.OnQueryTextListener{

    public static final String EXTRA_NAME = "characterName";
    public static final String EXTRA_STATUS = "characterStatus";
    public static final String EXTRA_SPECIES = "characterSpecies";
    public static final String EXTRA_GENDER = "characterGenre";
    public static final String EXTRA_URL = "characterImage";
    MediaPlayer mySound;
    private SearchView searchView;

    // Stop music when quit the application
    @Override
    protected void onPause() {
        super.onPause();
        mySound.release();
    }

    // les 20 pages de l'api
    private static final String URL_DATA = "https://rickandmortyapi.com/api/character/?page=1";
    private static final String URL_DATA_2 = "https://rickandmortyapi.com/api/character/?page=2";
    private static final String URL_DATA_3 = "https://rickandmortyapi.com/api/character/?page=3";
    private static final String URL_DATA_4 = "https://rickandmortyapi.com/api/character/?page=4";
    private static final String URL_DATA_5 = "https://rickandmortyapi.com/api/character/?page=5";
    private static final String URL_DATA_6 = "https://rickandmortyapi.com/api/character/?page=6";
    private static final String URL_DATA_7 = "https://rickandmortyapi.com/api/character/?page=7";
    private static final String URL_DATA_8 = "https://rickandmortyapi.com/api/character/?page=8";
    private static final String URL_DATA_9 = "https://rickandmortyapi.com/api/character/?page=9";
    private static final String URL_DATA_10 = "https://rickandmortyapi.com/api/character/?page=10";
    private static final String URL_DATA_11 = "https://rickandmortyapi.com/api/character/?page=11";
    private static final String URL_DATA_12 = "https://rickandmortyapi.com/api/character/?page=12";
    private static final String URL_DATA_13 = "https://rickandmortyapi.com/api/character/?page=13";
    private static final String URL_DATA_14 = "https://rickandmortyapi.com/api/character/?page=14";
    private static final String URL_DATA_15 = "https://rickandmortyapi.com/api/character/?page=15";
    private static final String URL_DATA_16 = "https://rickandmortyapi.com/api/character/?page=16";
    private static final String URL_DATA_17 = "https://rickandmortyapi.com/api/character/?page=17";
    private static final String URL_DATA_18 = "https://rickandmortyapi.com/api/character/?page=18";
    private static final String URL_DATA_19 = "https://rickandmortyapi.com/api/character/?page=19";
    private static final String URL_DATA_20 = "https://rickandmortyapi.com/api/character/?page=20";


    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private List<Character> characterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
       // recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        characterList = new ArrayList<>();

        loadRecyclerViewData();
        mySound = MediaPlayer.create(this, R.raw.song);
        mySound.start();

        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(this);
    }

    public void loadRecyclerViewData(){
        // message "loading data ..." during loading data
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data ...");
        progressDialog.show();

        //chargement des 20 pages de l'url
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

        StringRequest stringRequest2 = new StringRequest(Request.Method.GET,
                URL_DATA_2,
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

        StringRequest stringRequest3 = new StringRequest(Request.Method.GET,
                URL_DATA_3,
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

        StringRequest stringRequest4 = new StringRequest(Request.Method.GET,
                URL_DATA_4,
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

        StringRequest stringRequest5 = new StringRequest(Request.Method.GET,
                URL_DATA_5,
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

        StringRequest stringRequest6 = new StringRequest(Request.Method.GET,
                URL_DATA_6,
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

        StringRequest stringRequest7 = new StringRequest(Request.Method.GET,
                URL_DATA_7,
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

        StringRequest stringRequest8 = new StringRequest(Request.Method.GET,
                URL_DATA_8,
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

        StringRequest stringRequest9 = new StringRequest(Request.Method.GET,
                URL_DATA_9,
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

        StringRequest stringRequest10 = new StringRequest(Request.Method.GET,
                URL_DATA_10,
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

        StringRequest stringRequest11 = new StringRequest(Request.Method.GET,
                URL_DATA_11,
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

        StringRequest stringRequest12 = new StringRequest(Request.Method.GET,
                URL_DATA_12,
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

        StringRequest stringRequest13 = new StringRequest(Request.Method.GET,
                URL_DATA_13,
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

        StringRequest stringRequest14 = new StringRequest(Request.Method.GET,
                URL_DATA_14,
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

        StringRequest stringRequest15 = new StringRequest(Request.Method.GET,
                URL_DATA_15,
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

        StringRequest stringRequest16 = new StringRequest(Request.Method.GET,
                URL_DATA_16,
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

        StringRequest stringRequest17 = new StringRequest(Request.Method.GET,
                URL_DATA_17,
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

        StringRequest stringRequest18 = new StringRequest(Request.Method.GET,
                URL_DATA_18,
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

        StringRequest stringRequest19 = new StringRequest(Request.Method.GET,
                URL_DATA_19,
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

        StringRequest stringRequest20 = new StringRequest(Request.Method.GET,
                URL_DATA_20,
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
        RequestQueue requestQueue2 = Volley.newRequestQueue(this);
        requestQueue2.add(stringRequest2);
        RequestQueue requestQueue3 = Volley.newRequestQueue(this);
        requestQueue3.add(stringRequest3);
        RequestQueue requestQueue4 = Volley.newRequestQueue(this);
        requestQueue4.add(stringRequest4);
        RequestQueue requestQueue5 = Volley.newRequestQueue(this);
        requestQueue5.add(stringRequest5);
        RequestQueue requestQueue6 = Volley.newRequestQueue(this);
        requestQueue6.add(stringRequest6);
        RequestQueue requestQueue7 = Volley.newRequestQueue(this);
        requestQueue7.add(stringRequest7);
        RequestQueue requestQueue8 = Volley.newRequestQueue(this);
        requestQueue8.add(stringRequest8);
        RequestQueue requestQueue9 = Volley.newRequestQueue(this);
        requestQueue9.add(stringRequest9);
        RequestQueue requestQueue10 = Volley.newRequestQueue(this);
        requestQueue10.add(stringRequest10);
        RequestQueue requestQueue11 = Volley.newRequestQueue(this);
        requestQueue11.add(stringRequest11);
        RequestQueue requestQueue12 = Volley.newRequestQueue(this);
        requestQueue12.add(stringRequest12);
        RequestQueue requestQueue13 = Volley.newRequestQueue(this);
        requestQueue13.add(stringRequest13);
        RequestQueue requestQueue14 = Volley.newRequestQueue(this);
        requestQueue14.add(stringRequest14);
        RequestQueue requestQueue15 = Volley.newRequestQueue(this);
        requestQueue15.add(stringRequest15);
        RequestQueue requestQueue16 = Volley.newRequestQueue(this);
        requestQueue16.add(stringRequest16);
        RequestQueue requestQueue17 = Volley.newRequestQueue(this);
        requestQueue17.add(stringRequest17);
        RequestQueue requestQueue18 = Volley.newRequestQueue(this);
        requestQueue18.add(stringRequest18);
        RequestQueue requestQueue19 = Volley.newRequestQueue(this);
        requestQueue19.add(stringRequest19);
        RequestQueue requestQueue20 = Volley.newRequestQueue(this);
        requestQueue20.add(stringRequest20);
    }

    // on item click
    @Override
    public void onItemClick(int i) {
        Intent detailIntent = new Intent(this, CharacterDetail.class);
        Character clickedItem = characterList.get(i);

        // put extra data for second activity
        detailIntent.putExtra(EXTRA_NAME, clickedItem.getName());
        detailIntent.putExtra(EXTRA_STATUS, clickedItem.getStatus());
        detailIntent.putExtra(EXTRA_SPECIES, clickedItem.getSpecies());
        detailIntent.putExtra(EXTRA_GENDER, clickedItem.getGender());
        detailIntent.putExtra(EXTRA_URL, clickedItem.getImage());

        startActivity(detailIntent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final List<Character> filteredModelList = filter(characterList,newText);
        myAdapter.setFilter(filteredModelList);
        return false;
    }

    private List<Character> filter(List<Character> characters, String query){
        query = query.toLowerCase();
        final List<Character> filteredModelList = new ArrayList<>();
        for(Character character: characters){
            String text = character.getName().toLowerCase();
            if (text.contains(query)){
                filteredModelList.add(character);
            }
        }
        return filteredModelList;
    }
}
