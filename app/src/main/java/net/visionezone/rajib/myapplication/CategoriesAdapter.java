package net.visionezone.rajib.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.visionezone.rajib.myapplication.model.Categories;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class CategoriesAdapter extends ArrayAdapter<Categories> {

    private Context context;
    private List<Categories> categoriesList;

    private LruCache<Integer, Bitmap> imageCache;

    public CategoriesAdapter(Context context, int resource, List<Categories> objects) {
        super(context, resource, objects);
        this.context = context;
        this.categoriesList = objects;

        final int maxMemory = (int)(Runtime.getRuntime().maxMemory() /1024);
        final int cacheSize = maxMemory / 8;
        imageCache = new LruCache<>(cacheSize);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_categories, parent, false);

        //Display flower name in the TextView widget
        Categories categories = categoriesList.get(position);
        TextView tv = (TextView) view.findViewById(R.id.textView1);
        tv.setText(categories.getCat_name());

        //Display flower category in the TextView widget
        TextView tv2 = (TextView) view.findViewById(R.id.textView2);
        tv2.setText(Html.fromHtml(categories.getCat_description()));

        //Display flower category in the TextView widget
        //NumberFormat formatter = NumberFormat.getCurrencyInstance();
        //String price = formatter.format(categories.getPrice());
        //TextView tv3 = (TextView) view.findViewById(R.id.textView3);
        //tv3.setText(price);

        //Display flower photo in ImageView widget
        Bitmap bitmap = imageCache.get(categories.getCat_id());
        if (bitmap != null) {
            ImageView image = (ImageView) view.findViewById(R.id.imageView1);
            image.setImageBitmap(categories.getBitmapCat());
        }
        else {
            CategoriesAndView container = new CategoriesAndView();
            container.categories = categories;
            container.view = view;

            ImageLoader loader = new ImageLoader();
            loader.execute(container);
        }

        return view;
    }

    class CategoriesAndView {
        public Categories categories;
        public View view;
        public Bitmap bitmap;
    }

    private class ImageLoader extends AsyncTask<CategoriesAndView, Void, CategoriesAndView> {

        @Override
        protected CategoriesAndView doInBackground(CategoriesAndView... params) {

            CategoriesAndView container = params[0];
            Categories categories = container.categories;

            try {
                String imageUrl = MainActivity.PHOTOS_BASE_URL + MainActivity.CAT_PHOTO_PATH + categories.getImage_name();
                Log.v("CatPhoto", "MyValue=" + imageUrl);
                InputStream in = (InputStream) new URL(imageUrl).getContent();
                Bitmap bitmap = BitmapFactory.decodeStream(in);
                categories.setBitmapCat(bitmap);
                in.close();
                container.bitmap = bitmap;
                return container;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(CategoriesAndView result) {
            ImageView image = (ImageView) result.view.findViewById(R.id.imageView1);
            image.setImageBitmap(result.bitmap);
            imageCache.put(result.categories.getCat_id(), result.bitmap);
        }

    }
}
