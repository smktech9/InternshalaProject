package com.example.android.internshalaproject;


import android.content.Context;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {


    public LayoutInflater inflater1;
    ViewGroup container1;
    Bundle savedInstanceState1;
    String[] movieId,movieTitle,movieOverview,movieReleaseDate,moviePosterPath,movieVoteAverage;
    public MoviesFragment() {
    }

    MovieAdapter mMovieAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        inflater1=inflater;
        container1=container;
        savedInstanceState1=savedInstanceState;
        View rootView = inflater.inflate(R.layout.fragment_movies, container, false);

        mMovieAdapter = new MovieAdapter(getActivity());
        final GridView listView =  rootView.findViewById(R.id.gridView);
        listView.setAdapter(mMovieAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MovieDetailFragment fragment = new MovieDetailFragment();
                Bundle args = new Bundle();
                args.putString("title",movieTitle[position]);
                args.putString("date",movieReleaseDate[position]);
                args.putString("rating",movieVoteAverage[position]);
                args.putString("desc",movieOverview[position]);
                args.putString("poster",moviePosterPath[position]);

                fragment.setArguments(args);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.moviesFragment,fragment).commit();
                listView.setVisibility(View.INVISIBLE);

            }
        });
        FetchMovieTask movieTask = new FetchMovieTask();
        movieTask.execute();

        return rootView;
    }

    class FetchMovieTask extends AsyncTask<Void, Void, List<String>> {
        private final String LOG_TAG = FetchMovieTask.class.getSimpleName();

        @Override
        protected List<String> doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String movieJsonStr = null;

            try {
                URL url = new URL("http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=078dc21d983e5e92136e7ccf440442e1");

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {

                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                movieJsonStr = buffer.toString();

            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }
            try {
                return getMovieDataFromJson(movieJsonStr);
            } catch (JSONException j) {
                Log.e(LOG_TAG, "JSON Error", j);
            }
            return null;
        }

        private List<String> getMovieDataFromJson(String forecastJsonStr)
                throws JSONException {
            JSONObject movieJson = new JSONObject(forecastJsonStr);
            JSONArray movieArray = movieJson.getJSONArray("results");
            List<String> urls = new ArrayList<>();
            movieId = new String[movieArray.length()];
            movieTitle = new String[movieArray.length()];
            movieReleaseDate = new String[movieArray.length()];
            movieVoteAverage = new String[movieArray.length()];
            movieOverview = new String[movieArray.length()];
            moviePosterPath = new String[movieArray.length()];
            for (int i = 0; i < movieArray.length(); i++) {
                JSONObject movie = movieArray.getJSONObject(i);
                movieId[i] = movie.getString("id");
                movieTitle[i] = movie.getString("original_title");
                movieReleaseDate[i] = movie.getString("release_date");
                movieVoteAverage[i] = movie.getString("vote_average");
                movieOverview[i] = movie.getString("overview");
                moviePosterPath[i] = movie.getString("poster_path");
                urls.add("http://image.tmdb.org/t/p/w185" + movie.getString("poster_path"));


            }
            return urls;
        }

        @Override
        protected void onPostExecute(List<String> strings) {
            mMovieAdapter.replace(strings);
        }
    }

    class MovieAdapter extends BaseAdapter {
        private final String LOG_TAG = MovieAdapter.class.getSimpleName();
        private final Context context;
        private final List<String> urls = new ArrayList<String>();
        private final LayoutInflater inflater;
        public MovieAdapter(Context context) {
            this.context = context;
            inflater = LayoutInflater.from(context);
            try {
                Collections.addAll(urls, moviePosterPath);
            }
            catch (NullPointerException e){}
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            ImageView picture;
            TextView name;

            if (v == null) {
                v = inflater.inflate(R.layout.grid_item, parent, false);
                v.setTag(R.id.picture, v.findViewById(R.id.picture));
            }

            picture = (ImageView) v.getTag(R.id.picture);
            String url = getItem(position);

            Picasso.with(getContext()).load(url).into(picture);


            return v;
        }

        @Override
        public int getCount() {
            return urls.size();
        }

        @Override
        public String getItem(int position) {
            return urls.get(position);

        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        public void replace(List<String> urls) {
            this.urls.clear();
            this.urls.addAll(urls);
            notifyDataSetChanged();
        }
    }



}