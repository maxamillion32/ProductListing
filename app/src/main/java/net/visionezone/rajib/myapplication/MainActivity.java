package net.visionezone.rajib.myapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = "MainActivity";
    private static final int MENU_ITEM_LOGOUT = 1001;
    private static String siteurl = "http://visionezone.net/projects/webservice/vwcrestserver";
    private static String email = "rajib.vwc1@gmail.com";
    private CoordinatorLayout coordinatorLayout;

    public static final String PHOTOS_BASE_URL =
            "http://visionezone.net/projects/webservice/vwcrestserver/assets/upload/";
    public static final String ENDPOINT =
            "http://visionezone.net";
    public static final String CAT_BANNER_PATH = "categories/banner/original/";
    public static final String CAT_PHOTO_PATH = "categories/original/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);
        Log.d(LOG_TAG,"onCreate");

        // Scroll Barr add with activity_main //////////////////
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<10; i++){
            builder.append(getString(R.string.lorem_ipsum)+"\n\n");
            TextView tv = (TextView) findViewById(R.id.longText);
            tv.setText(builder.toString());
        }
        // Scroll Barr add //////////////////

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Send an email
                String[] addresses = {email};
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, addresses);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Information request");
                intent.putExtra(Intent.EXTRA_TEXT, "Please send some information!");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        menu.add(0,MENU_ITEM_LOGOUT,104,R.string.log_out);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id){
            case R.id.store_id:
                Intent intents = new Intent(this, StorelistActivity.class);
                startActivity(intents);
                return true;
            case R.id.product_id:
                Intent intentp = new Intent(this, ProductslistActivity.class);
                startActivity(intentp);
                return true;
            case R.id.settings_id:
                Snackbar.make(coordinatorLayout,
                        "You Selected Settings", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            case R.id.our_site_id:
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(siteurl));
                if(webIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(webIntent);
                }
                return true;
            case R.id.about_us_id:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                return true;
            case R.id.contact_us_id:
                Intent intentc = new Intent(this, ContactusActivity.class);
                startActivity(intentc);
                return true;
            case R.id.share_id:
                Snackbar.make(coordinatorLayout,
                        "You Selected Share", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            case R.id.cart_id:
                Snackbar.make(coordinatorLayout,
                        "You Selected Cart", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            case MENU_ITEM_LOGOUT:
                Snackbar.make(coordinatorLayout,
                        "You Selected Logout", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(MainActivity.this,"Your Orientation is Portrait",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this,"Your Orientation is Landscape",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG,"onStop");
    }

}
