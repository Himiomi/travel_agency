package fr.lernejo.prediction;

import java.util.ArrayList;
import java.util.List;

public record listTempOfCountry(String country, List<TempDate> temperatures) {
}
