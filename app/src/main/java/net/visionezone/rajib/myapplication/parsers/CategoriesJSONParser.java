package net.visionezone.rajib.myapplication.parsers;

import net.visionezone.rajib.myapplication.model.Categories;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CategoriesJSONParser {
    public static List<Categories> parseFeed(String content) {

        try {
            JSONArray ar = new JSONArray(content);
            List<Categories> flowerList = new ArrayList<>();

            for (int i = 0; i < ar.length(); i++) {

                JSONObject obj = ar.getJSONObject(i);
                Categories categories = new Categories();

                categories.setCat_id(obj.getInt("cat_id"));
                categories.setParent_id(obj.getInt("parent_id"));
                categories.setCat_name(obj.getString("cat_name"));
                categories.setCat_description(obj.getString("cat_description"));
                categories.setImage_name(obj.getString("image_name"));
                categories.setImage_banner(obj.getString("image_banner"));

                flowerList.add(categories);
            }

            return flowerList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
