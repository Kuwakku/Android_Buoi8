package com.example.android_buoi8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://dummyjson.com/";
    private DummyServices dummyServices;
    private Retrofit mRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        dummyServices = mRetrofit.create(DummyServices.class);

        dummyServices.getProducts().enqueue(new Callback<ProductsRespondse>() {
            @Override
            public void onResponse(Call<ProductsRespondse> call, Response<ProductsRespondse> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        ProductsRespondse productsRespondse = response.body();
                        Log.d("TAG", "onResponse: " + productsRespondse.getProducts(    ).get(0).getTitle());
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductsRespondse> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getMessage());
            }
        });

        dummyServices.getProductById("2").enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        Product product = response.body();
                        Log.d("TAG", "onResponse: getProductById : " + product.getTitle());
                    }
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getMessage());
            }
        });

        DummyServices dummyServices2 = RetrofitClient.create(DummyServices.class);
        CartServices cartServices = RetrofitClient.create(CartServices.class);
        cartServices.getCart().enqueue(new Callback<CartsResponse>() {
            @Override
            public void onResponse(Call<CartsResponse> call, Response<CartsResponse> response) {

                if(response.isSuccessful()) {
                    if (response.code() == 200) {
                        int i = 0;
                        CartsResponse cartsResponse = response.body();
                        Log.d("TAG", "onResponse: CartsResponse: " + cartsResponse.getCarts().get(i).toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<CartsResponse> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getMessage());
            }
        });

        dummyServices.addProducts(new AddProductsRequest("Cai coc")).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("TAG", "onResponse: " + response.body().get("id").getAsString());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getMessage());
            }
        });
    }
}