package com.frdasilva.udacity.popularmoviesp1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.frdasilva.udacity.popularmoviesp1.model.Movie;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private String TAG = DetailActivity.class.getSimpleName();
    private static final String INTENT_EXTRA             = "MOVIE";
    private ImageView backgroundImage;
    private ImageView cartazFilme;
    private TextView tituloFilme;
    private TextView avaliacaoFilme;
    private TextView dataFilme;
    private TextView resumoFilme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        backgroundImage = (ImageView) findViewById(R.id.iv_detail_background_image);
        cartazFilme = (ImageView) findViewById(R.id.iv_detail_poster_image);
        tituloFilme = (TextView) findViewById(R.id.tv_movie_name);
        avaliacaoFilme = (TextView) findViewById(R.id.tv_movie_rate);
        dataFilme = (TextView) findViewById(R.id.tv_movie_release);
        resumoFilme = (TextView) findViewById(R.id.tv_movie_overview);

        Intent intent = getIntent();

        //Verifica se a intent foi devidamente recebida com os extras
        if (intent != null) {
            if (intent.hasExtra(INTENT_EXTRA)) {
                Movie movie = intent.getParcelableExtra(INTENT_EXTRA);

                Picasso.with(this).load(movie.getBackgroundImage()).into(backgroundImage);
                Picasso.with(this).load(movie.getCartazFilme()).into(cartazFilme);

                tituloFilme.setText(movie.getTitulo());
                avaliacaoFilme.setText(Double.toString(movie.getMediaVotos()));
                dataFilme.setText(movie.getDataLancamento());
                resumoFilme.setText(movie.getSinopse());
            }
        }

    }
}
