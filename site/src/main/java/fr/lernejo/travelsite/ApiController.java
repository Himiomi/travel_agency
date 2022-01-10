package fr.lernejo.travelsite;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@RestController
public class ApiController {

    private  final SiteService siteService;
    private final ArrayList<Registr> listRegistr ;    //Liste des personnes enregistrées

    ApiController(SiteService siteService){
        this.siteService=siteService;
        listRegistr = new ArrayList<Registr>();
    }

    @PostMapping("/api/inscription")
    public void inscription(@RequestBody Registr newRegistr) throws IOException {

        PrintWriter writer = new PrintWriter("Inscription.txt", StandardCharsets.UTF_8);
        writer.println("inscription de "+newRegistr.userName());
        writer.close();
        listRegistr.add(newRegistr);
    }

    @ResponseBody
    @GetMapping("/api/travels")
    public Object travels(@RequestParam String userName) throws IOException {

        PrintWriter writer = new PrintWriter("Travels.txt", StandardCharsets.UTF_8);
        writer.println("Demande pour "+userName);
        writer.close();
        String content = new String(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("countries.txt")).readAllBytes(), StandardCharsets.UTF_8);
        ArrayList<PotentialDestinations> listDest = new ArrayList<PotentialDestinations>();
        Iterator<String> iterator=content.lines().iterator();
        Registr currentRegistr=null;
        for(Registr registr1:listRegistr){if (registr1.userName().equals(userName))currentRegistr=registr1;}
        if(currentRegistr==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Veuillez vous enregistrer");
        else {
            while (iterator.hasNext()) {
                String current=iterator.next();
                double moyTemp=(siteService.getTemperature(current).get(0).temperature()+siteService.getTemperature(current).get(1).temperature())/2;
                if (moyTemp>=currentRegistr.minimumTemperatureDistance()&&currentRegistr.weatherExpectation().equals(Weather.WARMER))listDest.add(new PotentialDestinations(current,moyTemp));
                else if(moyTemp<=currentRegistr.minimumTemperatureDistance()&&currentRegistr.weatherExpectation().equals(Weather.COLDER))listDest.add(new PotentialDestinations(current,moyTemp));
            }
            return listDest;
        }
    }
}
//https://itqna.net/questions/69229/retrofit-could-not-locate-responsebody-converter
