package controllers;

import lemezbolt.exceptions.*;
import lemezbolt.models.Lemez;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.LemezService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@RestController
public class LemezController{
    private LemezService service;

    public LemezController(@Autowired LemezService service) {
        this.service = service;
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public String showLemezCount()
    {
        return String.valueOf(service.getAllLemez().size());
    }

    @RequestMapping(value = "/lemezek", method = RequestMethod.GET)
    @ResponseBody
    public Collection<Lemez> getAllLemez(){
        return service.getAllLemez();
    }

    @RequestMapping(value = "/lemezek", method = RequestMethod.POST)
    @ResponseBody
    public Lemez addNewLemez(@RequestBody Lemez lemez) throws InvalidDateException, NoMatchingGenreException, NoMatchingReleaserException, NoMatchingIDException {
        service.addLemez(lemez);
        return service.getLemez(lemez.getId());
    }

    @RequestMapping(value = "/lemez/{name}", method = RequestMethod.GET)
    @ResponseBody
    public Collection<Lemez> getLemezByName(@PathVariable String name) {
        Collection<Lemez> lemezek = service.getAllLemez();
        Collection<Lemez> result = new ArrayList<Lemez>();

        for (Lemez a: lemezek)
        {
            if (a.getLemezNev().equalsIgnoreCase(name)) {
                result.add(a);
            }
        }
        return result;
    }

    @RequestMapping(value = "/lemezid/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Lemez getLemezByID(@PathVariable UUID id) throws NoMatchingIDException {
        return service.getLemez(id);
    }

    @RequestMapping(value = "/lemez/{egyuttes}", method = RequestMethod.GET)
    @ResponseBody
    public Collection<Lemez> getLemezByBand(@PathVariable String egyuttes) {
        Collection<Lemez> lemezek = service.getAllLemez();
        Collection<Lemez> result = new ArrayList<Lemez>();

        for (Lemez a: lemezek)
        {
            if (a.getLemezEgyuttes().equalsIgnoreCase(egyuttes)) {
                result.add(a);
            }
        }
        return result;
    }

    @RequestMapping(value = "/lemez/{kiado}", method = RequestMethod.GET)
    @ResponseBody
    public Collection<Lemez> getLemezByReleaser(@PathVariable String kiado) {
        Collection<Lemez> lemezek = service.getAllLemez();
        Collection<Lemez> result = new ArrayList<Lemez>();

        for (Lemez a: lemezek)
        {
            if (a.getGyarto().toString().equalsIgnoreCase(kiado)) {
                result.add(a);
            }
        }
        return result;
    }

    @RequestMapping(value = "/lemez/{mufaj}", method = RequestMethod.GET)
    @ResponseBody
    public Collection<Lemez> getLemezByGenre(@PathVariable String mufaj) {
        Collection<Lemez> lemezek = service.getAllLemez();
        Collection<Lemez> result = new ArrayList<Lemez>();

        for (Lemez a: lemezek)
        {
            if (a.getMufaj().toString().equalsIgnoreCase(mufaj)) {
                result.add(a);
            }
        }
        return result;
    }

    @ExceptionHandler(NoMatchingIDException.class)
    @ResponseBody
    public String handleNoMatchingIDException(Exception e)
    {
        return "UUID nem található! " + e.getMessage();
    }

    @ExceptionHandler(InvalidDateException.class)
    @ResponseBody
    public String handleInvalidDateException(Exception e)
    {
        return "Érvénytelen dátum! " + e.getMessage();
    }

    @ExceptionHandler(NoMatchingBandException.class)
    @ResponseBody
    public String handleNoMatchingBandExceptionException(Exception e)
    {
        return "Nincs ilyen együttes! " + e.getMessage();
    }

    @ExceptionHandler(NoMatchingGenreException.class)
    @ResponseBody
    public String handleNoMatchingGenreExceptionException(Exception e)
    {
        return "Nincs ilyen műfaj! " + e.getMessage();
    }

    @ExceptionHandler(NoMatchingReleaserException.class)
    @ResponseBody
    public String handleNoMatchingReleaserExceptionException(Exception e)
    {
        return "Nincs ilyen kiadó! " + e.getMessage();
    }
}
