package com.frdasilva.udacity.popularmoviesp1;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.frdasilva.udacity.popularmoviesp1.model.Movie;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler {

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;
    private String TAG = MainActivity.class.getSimpleName();
    private boolean sortByPopularity = true;
    private ProgressBar mProgressBar;
    private Menu menu;
    private MenuItem popular;
    private MenuItem topRated;

    private static final String BASE_URL_SORT_POPULARITY = "http://api.themoviedb.org/3/movie/popular?api_key=";
    private static final String BASE_URL_SORT_TOP_RATED  = "http://api.themoviedb.org/3/movie/top_rated?api_key=";
    private static final String LANGUAGE_URL             = "&language=pt";

    //TODO: INSERIR A APIKEY NESSA VARIAVEL
    private static final String THE_MOVIE_DB_API_KEY = "4c701989b6f7ef0b71c7361b0d89673f";

    private String baseURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializando o RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_movie_list);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        //Inicializando o ProgressBar
        mProgressBar = (ProgressBar) findViewById(R.id.pb_load_movies);

        //Inicializando o adapter
        mMovieAdapter = new MovieAdapter(this);
        mRecyclerView.setAdapter(mMovieAdapter);

        //Exibe o RecyclerView
        mRecyclerView.setVisibility(View.VISIBLE);

        //Inicia o thread
        new FetchMovieTask().execute();

    }

    @Override
    public void onClick(Movie movie) {

    }

    public class FetchMovieTask extends AsyncTask<Void,Void,Movie[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Movie[] doInBackground(Void... voids) {
            HttpURLConnection urlConnection = null;
            BufferedReader bufferedReader = null;
            String jsonString = null;

            //Verifica a ordenação escolhida
            if (sortByPopularity) {
                baseURL = BASE_URL_SORT_POPULARITY + THE_MOVIE_DB_API_KEY + LANGUAGE_URL;
            } else {
                baseURL = BASE_URL_SORT_TOP_RATED + THE_MOVIE_DB_API_KEY + LANGUAGE_URL;
            }

            Log.d(TAG,baseURL);

            //Monta Uri
            Uri builtUri = Uri.parse(baseURL).buildUpon().build();

            try {
                URL url = new URL(builtUri.toString());

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null)
                    return null;
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null)
                    buffer.append(line + "\n");
                if (buffer.length() == 0)
                    return null;
                jsonString = buffer.toString();
                inputStream.close();

            }catch (Exception e){
                e.printStackTrace();
                return null;
            }

            try{
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                Movie[] container = new Movie[jsonArray.length()];
                String titulo, dataLancamento, sinopse, cartazFilme, cartazFilmeCompleto;
                Double mediaVotos;
                for(int i=0;i<jsonArray.length();i++){
                    titulo = jsonArray.getJSONObject(i).getString("title");
                    dataLancamento=jsonArray.getJSONObject(i).getString("release_date");
                    sinopse=jsonArray.getJSONObject(i).getString("overview");
                    mediaVotos=jsonArray.getJSONObject(i).getDouble("vote_average");
                    cartazFilme=jsonArray.getJSONObject(i).getString("poster_path");
                    cartazFilmeCompleto="http://image.tmdb.org/t/p/w342"+cartazFilme;
                    container[i]=new Movie(titulo,dataLancamento,sinopse,cartazFilmeCompleto,mediaVotos);
                }

                return container;

            }catch(Exception e){
                e.printStackTrace();
                return null;
            }finally {
                urlConnection.disconnect();
            }

        }

        @Override
        protected void onPostExecute(Movie[] movies) {
            super.onPostExecute(movies);
            mProgressBar.setVisibility(View.INVISIBLE);
            mRecyclerView.setVisibility(View.VISIBLE);
            mMovieAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        popular = menu.findItem(R.id.menu_sort_popularity);
        topRated = menu.findItem(R.id.menu_sort_rate);
        if (sortByPopularity) {
            popular.setVisible(false);
            topRated.setVisible(true);
        } else {
            popular.setVisible(true);
            topRated.setVisible(false);
        }
        return true;
    }
}
