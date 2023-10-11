package com.example.android_buoi8;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CartServices {
    @GET("carts")
    Call<CartsResponse> getCart();
}
