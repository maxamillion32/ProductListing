package net.visionezone.rajib.myapplication;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import net.visionezone.rajib.myapplication.model.Categories;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class StorelistActivity extends ListActivity {

    TextView output;
    ProgressBar pb;
    List<Categories> categoriesList;

    public static final String CAT_POSITION = "CAT_POSITION";
    public static final String CAT_Id = "CAT_Id";
    public static final String PARENT_Id = "PARENT_Id";
    public static final String CAT_NAME = "CAT_NAME";
    public static final String CAT_DESCRIPTION = "CAT_DESCRIPTION";
    public static final String CAT_PHOTO = "CAT_PHOTO";
    public static final String CAT_BANNER = "CAT_BANNER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storelist);

        pb = (ProgressBar) findViewById(R.id.progressBar1);
        pb.setVisibility(View.INVISIBLE);

        if (isOnline()) {
            requestData("http://visionezone.net/projects/webservice/vwcrestserver/api/categories");
        } else {
            Toast.makeText(this, "Network isn't available", Toast.LENGTH_LONG).show();
        }
    }

    private void requestData(String uri) {

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(MainActivity.ENDPOINT)
                .build();

        CategoriesAPI api = adapter.create(CategoriesAPI.class);

        api.getFeed(new Callback<List<Categories>>() {
            @Override
            public void success(List<Categories> categories, Response response) {
                categoriesList = categories;
                updateDisplay();
            }

            @Override
            public void failure(RetrofitError retrofitError) {

            }
        });

    }

    protected void updateDisplay() {
        //Use FlowerAdapter to display data
        CategoriesAdapter adapter = new CategoriesAdapter(this, R.layout.item_categories, categoriesList);
        setListAdapter(adapter);

        ListView lv = (ListView) findViewById(android.R.id.list);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Categories categories = categoriesList.get(position);

                Intent intent = new Intent(StorelistActivity.this, CatdetailActivity.class);
                intent.putExtra(CAT_POSITION,position);

                intent.putExtra(CAT_Id,categories.getCat_id());
                intent.putExtra(PARENT_Id,categories.getParent_id());

                intent.putExtra(CAT_NAME,categories.getCat_name());
                intent.putExtra(CAT_DESCRIPTION,categories.getCat_description());

                intent.putExtra(CAT_PHOTO,categories.getImage_name());
                intent.putExtra(CAT_BANNER,categories.getImage_banner());

                //NumberFormat formatter = NumberFormat.getCurrencyInstance();
                //String price = formatter.format(categories.getPrice());
                //intent.putExtra(PRODUCT_PRISE,price);


                //Toast.makeText(StorelistActivity.this,"CAT_Id : " + categories.getCat_id(),Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }
}
