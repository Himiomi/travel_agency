package fr.lernejo.prediction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class Launcher {

    public static void main(String[] args) {
        try {
            PrintWriter writer = new PrintWriter("Inscription.txt", StandardCharsets.UTF_8);
            writer.println("Démarrage application ");
            writer.close();
        }catch (IOException e){
            System.out.println(e);
        }
        SpringApplication.run(Launcher.class, args);
    }


}
