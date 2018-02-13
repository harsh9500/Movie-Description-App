package department.of.it.yehpehlahai;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Harsh Jain on 13-02-2018.
 * Assisted by Ajay Lad aka Ladazoo
 */

public class MovieDescription extends AppCompatActivity {
    TextView movietitle,moviedesc;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description);
        movietitle=(TextView)findViewById(R.id.MovieDescriptionName);
        moviedesc =(TextView)findViewById(R.id.MovieDescriptionDetails);
        Intent intent=getIntent();
        String title = intent.getStringExtra("movietitle");
        String description = intent.getStringExtra("moviedesc");
        movietitle.setText(title);
        moviedesc.setText(description);
    }
}
