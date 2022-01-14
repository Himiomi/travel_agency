package fr.lernejo.travelsite.ApiFunction;

import fr.lernejo.travelsite.differentsClass.Registr;
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
    public void inscription(@RequestBody Registr newRegistr) {
        listRegistr.add(newRegistr);
    }

    @ResponseBody
    @GetMapping("/api/travels")
    public Object travels(@RequestParam String userName) throws IOException {
        Registr currentRegistr = null;
        for(Registr registr1:listRegistr){
            if (registr1.userName().equals(userName))return siteService.potentialCountries(registr1);;
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Veuillez vous enregistrer");

    }
}
//https://itqna.net/questions/69229/retrofit-could-not-locate-responsebody-converter
