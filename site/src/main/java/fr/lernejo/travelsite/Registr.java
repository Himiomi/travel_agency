package fr.lernejo.travelsite;

import java.util.regex.Pattern;

//https://blogs.oracle.com/javamagazine/post/records-come-to-java
public record Registr(String userEmail,
                      String userName,
                      String userCountry,
                      Weather weatherExpectation,
                      Integer minimumTemperatureDistance){
    public Registr {
        //Verification if userEmail is mail
        if(!Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}").matcher(userEmail).matches()){
            System.out.println("Not a valid email address "+userEmail);
            throw new IllegalArgumentException("userEmail must be an email");
        }

        //Verification if userCountry is Country
  /*      if(!Pattern.compile("[A-Z]{2}").matcher(userCountry).matches()){
            System.out.println("Not a valid userCountry "+userCountry);
            throw new IllegalArgumentException("userCountry must be an Country");
        }
    */    //Verification if minimumTemperatureDistance is in [0;40]
        if(!(minimumTemperatureDistance>=0&&minimumTemperatureDistance<=40)){
            System.out.println("Not a valid minimumTemperatureDistance "+minimumTemperatureDistance);
            throw new IllegalArgumentException("minimumTemperatureDistance must be ain [0;40");
        }
    }
}
