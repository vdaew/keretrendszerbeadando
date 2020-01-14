package impleservices.implement;

import dao.LemezDAO;
import lemezbolt.exceptions.*;
import lemezbolt.models.Lemez;
import lemezbolt.models.Mufaj;
import services.LemezService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

public class LemezServiceImplementation implements LemezService {
    private LemezDAO dao;

    public LemezServiceImplementation(LemezDAO dao) {
        this.dao = dao;
    }

    public Lemez getLemez(UUID id) throws NoMatchingIDException {
        return dao.readLemez(id);
    }

    public Lemez getLemez(String lemezEgyuttes) throws NoMatchingBandException {
        return dao.readLemez(lemezEgyuttes);
    }

    public Lemez getLemez(Mufaj mufaj) throws NoMatchingGenreException {
        return dao.readLemez(mufaj);
    }

    public Collection<Lemez> getAllLemez() {
        return dao.readAllLemez();
    }

    public void addLemez(Lemez lemez) throws InvalidDateException, NoMatchingGenreException, NoMatchingReleaserException {
        if ((lemez.getKiadas().isAfter(LocalDate.now().plusYears(1))) || lemez.getKiadas().isAfter(LocalDate.now().plusMonths(1)))
        {
            throw new InvalidDateException();
        }
        dao.createLemez(lemez);

    }

    public void updateLemez(Lemez lemez) throws NoMatchingIDException, InvalidDateException, NoMatchingBandException, NoMatchingReleaserException {
        if ((lemez.getKiadas().isAfter(LocalDate.now().plusYears(1))) || lemez.getKiadas().isAfter(LocalDate.now().plusMonths(1)))
        {
            throw new InvalidDateException();
        }
        dao.updateLemez(lemez);
    }

    public void deleteLemez(Lemez lemez) throws NoMatchingIDException {
        dao.deleteLemez(lemez);

    }

    public void deleteLemez(UUID id) throws NoMatchingIDException {
        Lemez result = getLemez(id);
        dao.deleteLemez(result);
    }
}
