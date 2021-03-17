package com.polipost.Utilities;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    // public static final String BASE_URL = "http://sms.aictpvtltd.com/api/";
    public static final String BASE_URL = "https://polipost.aictsolution.com/public/api/";


    public static Retrofit retrofit = null;
    public long bufferSize = 12345678910L;

    /*
    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .socketFactory(new RestrictedSocketFactory(bufferSize))
            .build();
     */
//    static OkHttpClient okHttpClient = new OkHttpClient.Builder()
//            .connectTimeout(120, TimeUnit.SECONDS)
//            .writeTimeout(120, TimeUnit.SECONDS)
//            .readTimeout(120, TimeUnit.SECONDS)
//            .socketFactory(new SSLCertificateSocketFactory(bufferSize))
//            .build();

//    static OkHttpClient okHttpClient = new OkHttpClient.Builder()
//            .connectTimeout(10, TimeUnit.SECONDS)
//            .readTimeout(10, TimeUnit.SECONDS)
//            .writeTimeout(10, TimeUnit.SECONDS)
//            .build();



    public static Retrofit getApiClient() {

        if (retrofit == null) {

            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
            Log.i("Info", "Inside Retrofit");
        }

        return retrofit;
    }
}
