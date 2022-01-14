package fr.lernejo.prediction.initalsFiles;

public class UnknownCountryException extends RuntimeException {
    public UnknownCountryException(String country) {
        super("Unknown country: " + country);
    }
}
