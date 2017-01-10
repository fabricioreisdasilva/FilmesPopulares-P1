package com.frdasilva.udacity.popularmoviesp1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.frdasilva.udacity.popularmoviesp1.model.Movie;
import com.squareup.picasso.Picasso;


/**
 * Created by Fabricio Reis on 09/01/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {
    static final String TAG=MovieAdapter.class.getSimpleName();
    private Movie[] movies;
    private MovieAdapterOnClickHandler mClickHandler;

    interface MovieAdapterOnClickHandler {
        void onClick(Movie movie);
    }

    public MovieAdapter(MovieAdapterOnClickHandler clickHandler) {
        this.mClickHandler = clickHandler;
    }

    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(layoutId, parent, false);
        MovieAdapterViewHolder adapterViewHolder = new MovieAdapterViewHolder(view);
        return adapterViewHolder;
    }

    @Override
    public void onBindViewHolder(MovieAdapterViewHolder holder, int position) {
        String imagem = movies[position].getCartazFilme();
        Picasso.with(holder.itemView.getContext()).load(imagem).into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        if (movies == null) {
            return 0;
        } else {
            return movies.length;
        }
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final ImageView mImageView;

        public MovieAdapterViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_movie_poster);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Movie movie = movies[position];
            mClickHandler.onClick(movie);

        }
    }
}
