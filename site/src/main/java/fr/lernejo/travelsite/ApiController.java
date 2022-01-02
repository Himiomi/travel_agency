package fr.lernejo.travelsite;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ApiController {

    private final ArrayList<Registr> listRegistr = new ArrayList<Registr>();    //Liste des personnes enregistrées

    @PostMapping("/api/inscription")
    public void inscription(@RequestBody Registr newRegistr){
        listRegistr.add(newRegistr);
    }

    @GetMapping("/api/travels")
    public ArrayList<PotentialDestinations> travels(@RequestParam String userName){
        ArrayList<PotentialDestinations> listDest = new ArrayList<PotentialDestinations>();
        listDest.add(new PotentialDestinations("Caribbean",32.4));
        listDest.add(new PotentialDestinations("Australia",35.1));
        return listDest;
    }

}
