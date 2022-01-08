package fr.lernejo.prediction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

@RestController
public class TemperatureController {

    @GetMapping("/api/temperature")
    public Object temperature(@RequestParam String country){
        System.out.println("Request of"+country);
        final TemperatureService temperatureService = new TemperatureService();
        final String pattern = "yyyy-MM-dd";
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        ArrayList<TempDate> listAverageTemp = new ArrayList<>();
        try {
            listAverageTemp.add(new TempDate(sdf.format(calendar.getTime()), temperatureService.getTemperature(country)));
            calendar.add(Calendar.DATE, -1); //https://stackoverflow.com/questions/212321/how-to-subtract-x-days-from-a-date-using-java-calendar
            listAverageTemp.add(new TempDate(sdf.format(calendar.getTime()), temperatureService.getTemperature(country)));
            return new listTempOfCountry(country, listAverageTemp);
        }catch (UnknownCountryException error){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("ERREUR 417, le pays n'existe pas");
        }
    }

}
