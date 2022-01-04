package fr.lernejo.prediction;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitClient {
    @GET("api/temperature")
    Call<listTempOfCountry> getTemperature(@Query("country") String country);
}
