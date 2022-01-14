package fr.lernejo.prediction;

import fr.lernejo.prediction.differentsClass.TempDate;
import fr.lernejo.prediction.initalsFiles.TemperatureService;
import fr.lernejo.prediction.initalsFiles.UnknownCountryException;
import fr.lernejo.prediction.differentsClass.listTempOfCountry;
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
        final TemperatureService temp = new TemperatureService();
        Calendar date = Calendar.getInstance();
        final String pattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        ArrayList<TempDate> listAverageTemp = new ArrayList<>();
        try {
            for(int i=0;i<2;i++){
                date.add(Calendar.DATE, -1);
                listAverageTemp.add(new TempDate(sdf.format(date.getTime()),temp.getTemperature(country)));
            }
            return new listTempOfCountry(country, listAverageTemp);
        }catch (UnknownCountryException error){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("ERREUR 417, le pays n'existe pas");
        }
    }

}
