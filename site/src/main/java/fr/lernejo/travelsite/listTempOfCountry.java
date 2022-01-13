package fr.lernejo.travelsite;

import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Objects;

public final class listTempOfCountry {
    @NonNull
    private final String country;
    @NonNull
    private final List<TempDate> temperatures;

    public listTempOfCountry(@NonNull String country, @NonNull List<TempDate> temperatures) {
        this.country = country;
        this.temperatures = temperatures;
    }

    @NonNull
    public String country() {
        return country;
    }

    @NonNull
    public List<TempDate> temperatures() {
        return temperatures;
    }
}
