package com.fyp.yes2live.apiConfig;



import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    // in this class we define the base url of all our api's and then we create object of api clint class
    private static Retrofit retrofit = null;
    private static final String BASE_URL = "http://localhost:8080/api/";//THE BASE URL OF ALL API

    public static Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)//CODE TO USE BASE URI
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
// these all are built in functions


        return retrofit;
    }

}
