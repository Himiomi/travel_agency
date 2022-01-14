package fr.lernejo.travelsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SpringBootApplication
public class Launcher {

    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
    }

    @Bean
    PredictionEngineClient predictionEngineClient() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:7080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        return retrofit.create(PredictionEngineClient.class);
    }

}
