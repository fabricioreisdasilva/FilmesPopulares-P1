package com.frdasilva.udacity.popularmoviesp1;

import android.content.Context;
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
    private Context context;

    public MovieAdapter(MovieAdapterOnClickHandler mClickHandler) {
        this.mClickHandler = mClickHandler;
    }

    public void refreshList(Movie[] listMovies) {
        this.movies = listMovies;
        notifyDataSetChanged();
    }

    public interface MovieAdapterOnClickHandler {
        void onClick(Movie movie);
    }


    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        int layoutId = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutId, parent, false);
        MovieAdapterViewHolder adapterViewHolder = new MovieAdapterViewHolder(view);
        return adapterViewHolder;
    }

    @Override
    public void onBindViewHolder(MovieAdapterViewHolder holder, int position) {
        String imagem = movies[position].getCartazFilme();
        Picasso.with(context).load(imagem).into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        if (movies == null) {
            return 0;
        } else {
            return movies.length;
        }
    }

    class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final ImageView mImageView;

        MovieAdapterViewHolder(View itemView) {
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
