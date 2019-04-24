package com.example.android.internshalaproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends Fragment {


    public MovieDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        String title = getArguments().getString("title");
        String date = getArguments().getString("date");
        String rating = getArguments().getString("rating");
        String desc = getArguments().getString("desc");
        String url = "http://image.tmdb.org/t/p/w185"+getArguments().getString("poster");

        TextView movieTitle =  view.findViewById(R.id.title);
        TextView movieDate =  view.findViewById(R.id.date);
        TextView movieRating =  view.findViewById(R.id.rating);
        TextView movieDesc =  view.findViewById(R.id.description);
        ImageView imageView = view.findViewById(R.id.poster);
        Button button = view.findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoviesFragment fragment = new MoviesFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.moviesDetailFragment,fragment).commit();
            }
        });

        movieTitle.setText(title);
        movieDate.setText(date);
        movieRating.setText(rating);
        movieDesc.setText(desc);
        Picasso.with(getContext()).load(url).into(imageView);

        return view;
    }

}
