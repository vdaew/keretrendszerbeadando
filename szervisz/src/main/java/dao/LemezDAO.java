package dao;

import lemezbolt.exceptions.NoMatchingBandException;
import lemezbolt.exceptions.NoMatchingGenreException;
import lemezbolt.exceptions.NoMatchingIDException;
import lemezbolt.models.Lemez;
import lemezbolt.models.Mufaj;

import java.util.Collection;
import java.util.UUID;

public interface LemezDAO {
    void createLemez (Lemez lemez);
    Collection<Lemez> readAllLemez();
    Lemez readLemez (UUID id) throws NoMatchingIDException;
    Lemez readLemez(String lemezEgyuttes) throws NoMatchingBandException;
    Lemez readLemez(Mufaj mufaj) throws NoMatchingGenreException;
    void updateLemez(Lemez lemez) throws NoMatchingIDException;
    void deleteLemez(Lemez lemez) throws NoMatchingIDException;
}
