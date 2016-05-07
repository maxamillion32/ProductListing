package net.visionezone.rajib.myapplication.model;

import android.graphics.Bitmap;

public class Categories {

    private int cat_id;
    private int parent_id;
    private String cat_name;
    private String cat_description;
    private String image_name;
    private String image_banner;
    private Bitmap bitmapBanner;
    private Bitmap bitmapCat;

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getCat_description() {
        return cat_description;
    }

    public void setCat_description(String cat_description) {
        this.cat_description = cat_description;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public String getImage_banner() {
        return image_banner;
    }

    public void setImage_banner(String image_banner) {
        this.image_banner = image_banner;
    }

    public Bitmap getBitmapBanner() {
        return bitmapBanner;
    }

    public void setBitmapBanner(Bitmap bitmapBanner) {
        this.bitmapBanner = bitmapBanner;
    }

    public Bitmap getBitmapCat() {
        return bitmapCat;
    }

    public void setBitmapCat(Bitmap bitmapCat) {
        this.bitmapCat = bitmapCat;
    }

}
