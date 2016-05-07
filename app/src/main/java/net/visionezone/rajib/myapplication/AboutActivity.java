package net.visionezone.rajib.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        // Scroll Barr add with activity_main //////////////////
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<10; i++){
            builder.append(getString(R.string.lorem_ipsum)+"\n\n");
            TextView tv = (TextView) findViewById(R.id.longText);
            tv.setText(builder.toString());
        }
        // Scroll Barr add //////////////////

    }
}
