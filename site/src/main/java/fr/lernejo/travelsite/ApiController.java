package fr.lernejo.travelsite;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

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
        listRegistr.add(newRegistr);
    }

    @ResponseBody
    @GetMapping("/api/travels")
    public Object travels(@RequestParam String userName) throws IOException {
        Registr currentRegistr = null;
        for(Registr registr1:listRegistr){if (registr1.userName().equals(userName))currentRegistr=registr1;}
        if(currentRegistr==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Veuillez vous enregistrer");
        else {
            PrintWriter writer = new PrintWriter("debugAPICONTROLLER.txt", StandardCharsets.UTF_8);
            writer.println("User is "+currentRegistr);
            writer.close();
            return siteService.potentialCountries(currentRegistr.weatherExpectation(),currentRegistr.minimumTemperatureDistance());
        }
    }
}
//https://itqna.net/questions/69229/retrofit-could-not-locate-responsebody-converter
