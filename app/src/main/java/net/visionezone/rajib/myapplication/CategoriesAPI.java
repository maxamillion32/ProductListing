package net.visionezone.rajib.myapplication;

import net.visionezone.rajib.myapplication.model.Categories;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

public interface CategoriesAPI {
    @GET("/projects/webservice/vwcrestserver/api/categories")
    public void getFeed(Callback<List<Categories>> response);
}