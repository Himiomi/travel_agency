package fr.lernejo.travelsite.differentsClass;

import org.springframework.lang.NonNull;

public class TempDate{
    @NonNull final String date;
    @NonNull final double temperature;

    public TempDate(@NonNull String date, double temperature) {
        this.date = date;
        this.temperature = temperature;
    }

    public double temperature() {
        return temperature;
    }
}
