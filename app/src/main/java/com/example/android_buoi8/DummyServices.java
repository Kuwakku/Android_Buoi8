package com.example.android_buoi8;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DummyServices {

    @GET("products")
    Call<ProductsRespondse> getProducts();

    @GET("products/{idProduct}")
    Call<Product> getProductById(@Path("idProduct") String idProduct);

    @GET("products/search")
    Call<ProductsRespondse> searchProductName(@Query("q") String productName);

    @Headers("Content-Type: application/json")
    @POST("products/add")
    Call<JsonObject> addProducts(@Body AddProductsRequest addProductsRequest);
}
