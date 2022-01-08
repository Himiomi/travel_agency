package fr.lernejo.travelsite;

import java.util.List;

public record listTempOfCountry(String country, List<TempDate> temperatures) {
}
