package services;

import lemezbolt.exceptions.*;
import lemezbolt.models.Lemez;
import lemezbolt.models.Mufaj;

import java.util.Collection;
import java.util.UUID;

public interface LemezService {
    Lemez getLemez(UUID id) throws NoMatchingIDException;
    Lemez getLemez(String lemezEgyuttes) throws NoMatchingBandException;
    Lemez getLemez(Mufaj mufaj) throws NoMatchingGenreException;
    Collection<Lemez> getAllLemez();
    void addLemez(Lemez lemez) throws InvalidDateException, NoMatchingGenreException, NoMatchingReleaserException;
    void updateLemez(Lemez lemez) throws NoMatchingIDException, InvalidDateException, NoMatchingBandException, NoMatchingReleaserException;
    void deleteLemez(Lemez lemez) throws NoMatchingIDException;
    void deleteLemez(UUID id) throws NoMatchingIDException;
}
