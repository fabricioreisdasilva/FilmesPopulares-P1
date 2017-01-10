package com.frdasilva.udacity.popularmoviesp1;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * Created by Fabricio Reis on 09/01/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {

    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MovieAdapterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final ImageView imageView;

        public MovieAdapterViewHolder(View itemView, ImageView imageView) {
            super(itemView);
            this.imageView = imageView;
        }

        @Override
        public void onClick(View view) {

        }
    }
}
