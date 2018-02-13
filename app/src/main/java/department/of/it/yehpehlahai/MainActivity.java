package department.of.it.yehpehlahai;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
ImageView poster;
TextView moviename;
String movietitle,moviedesc,movieposter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        poster=(ImageView)findViewById(R.id.Poster);
        moviename=(TextView)findViewById(R.id.Moviename);
        poster.setOnClickListener(this);
        String url = new Uri.Builder()
                .scheme("https")
                .appendPath("api.themoviedb.org")
                .appendPath("3")
                .appendPath("discover")
                .appendPath("movie")
                .appendQueryParameter("api_key","8adb8ed5a1aaa6bc3d27d80eaef32354")
                //.appendQueryParameter("sort_by","popularity.desc")
                .build()
                .toString();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest

                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override


                    public void onResponse(JSONObject response) {
                        TextView name;
                        try {
                            Log.d("Response recieved", response.toString(4));

                            JSONArray movies = response.getJSONArray("results");
                            for (int i = 0; i < movies.length(); i++) {
                                JSONObject popularmovie = movies.getJSONObject(i);
                                movietitle = popularmovie.getString("title");
                                moviedesc = popularmovie.getString("overview");
                                movieposter = popularmovie.getString("poster_path");
                                movieposter = movieposter.substring(1);
                                Log.e("movietitle", movietitle);
                                Log.e("moviedesc", moviedesc);
                                Log.e("movieurl", movieposter);
                                name = (TextView) findViewById(R.id.Moviename);
                                name.setText(movietitle);
                                String posterurl = new Uri.Builder()
                                        .scheme("https")
                                        .authority("image.tmdb.org")
                                        .appendPath("t")
                                        .appendPath("p")
                                        .appendPath("w342")
                                        .appendPath(movieposter).build().toString();
                                Picasso.with(getApplicationContext())
                                        .load(posterurl)
                                        //.centerInside()
                                        .into(poster);
                            }
                        }
                        catch(JSONException j)
                    {
                        j.printStackTrace();
                    }
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Request Error", error.getMessage());
                    }
                });
        AppController.getInstance().addToRequestQueue(jsObjRequest);
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(MainActivity.this,MovieDescription.class);
        i.putExtra("movietitle",movietitle);
        i.putExtra("moviedesc",moviedesc);
        startActivity(i);
    }

}
