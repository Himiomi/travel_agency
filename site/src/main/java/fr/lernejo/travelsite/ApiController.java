package fr.lernejo.travelsite;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@RestController
public class ApiController {

    private SiteService siteService;
    private final ArrayList<Registr> listRegistr ;    //Liste des personnes enregistrées

    ApiController(SiteService siteService){
        this.siteService=siteService;
        listRegistr = new ArrayList<Registr>();
    }

    @PostMapping("/api/inscription")
    public void inscription(@RequestBody Registr newRegistr){
        listRegistr.add(newRegistr);
    }

    @ResponseBody
    @GetMapping("/api/travels")
    public Object travels(@RequestParam String userName) throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("countries.txt");
        assert inputStream != null;
        String content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        ArrayList<PotentialDestinations> listDest = new ArrayList<PotentialDestinations>();
        Iterator<String> iterator=content.lines().iterator();
        List<TempDate> tempOfCountry;
        Registr currentRegistr=null;
        for(Registr registr1:listRegistr){
            if (registr1.userName().equals(userName))currentRegistr=registr1;
        }
        if(currentRegistr==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Veuillez vous enregistrer");
        else {
            while (iterator.hasNext()) {
                String current=iterator.next();
                System.out.println("On va requester "+ current);
                tempOfCountry = siteService.getTemperature(current);
                double moyTemp=(tempOfCountry.get(0).temperature()+tempOfCountry.get(1).temperature())/2;
                if (moyTemp>=currentRegistr.minimumTemperatureDistance()&&currentRegistr.weatherExpectation().equals(Weather.WARMER))listDest.add(new PotentialDestinations(String.valueOf(iterator),moyTemp));
                else if(moyTemp<=currentRegistr.minimumTemperatureDistance()&&currentRegistr.weatherExpectation().equals(Weather.COLDER))listDest.add(new PotentialDestinations(String.valueOf(iterator),moyTemp));
            }
            return listDest;
        }
    }

}
//https://itqna.net/questions/69229/retrofit-could-not-locate-responsebody-converter
