package net.visionezone.rajib.myapplication;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.EditText;
import android.widget.TextView;

public class ContactusActivity extends AppCompatActivity {

    private RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);

        relativeLayout = (RelativeLayout) findViewById(R.id.relativelayout);

        // Scroll Barr add with activity_main //////////////////
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<10; i++){
            builder.append(getString(R.string.lorem_ipsum)+"\n\n");
            TextView tv = (TextView) findViewById(R.id.longText);
            tv.setText(builder.toString());
        }
        // Scroll Barr add //////////////////
    }

    public void buttonClickHandler(View view) {
        EditText fn = (EditText) findViewById(R.id.firstName);
        String firstname = fn.getText().toString();
        Snackbar.make(relativeLayout,
                "You name is "+firstname, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
