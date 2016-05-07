package net.visionezone.rajib.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class ProductslistActivity extends AppCompatActivity {

    private List<Product> products = DataProvider.productList;
    private CoordinatorLayout coordinatorLayout;
    public static final String PRODUCT_ID = "PRODUCT_ID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productslist);

        // Get list value from strings array.//
        /*String[] items = getResources().getStringArray(R.array.clothing);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                        android.R.id.text1, items);
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(adapter);*/
        // Get list value from strings array.//

        ProductListAdapter adapter = new ProductListAdapter(
                this, R.layout.list_item, products);
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ProductslistActivity.this, ProductdetailActivity.class);

                Product product = products.get(position);
                intent.putExtra(PRODUCT_ID, product.getProductId());

                startActivity(intent);
            }
        });
    }
}
