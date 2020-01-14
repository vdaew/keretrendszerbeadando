package lemezbolt.models;

import java.time.LocalDate;
import java.util.UUID;

public class Lemez {
    private UUID id;
    private String lemezNev;
    private String lemezEgyuttes;
    private Besorolas besorolas;
    private Mufaj mufaj;
    private Gyarto gyarto;
    private LocalDate kiadas;

    public Lemez() {
        this.id = UUID.randomUUID();
    }

    public Lemez(String lemezNev, String lemezEgyuttes, Besorolas besorolas, Mufaj mufaj, Gyarto gyarto, LocalDate kiadas) {
        this();
        this.lemezNev = lemezNev;
        this.lemezEgyuttes = lemezEgyuttes;
        this.besorolas = besorolas;
        this.mufaj = mufaj;
        this.gyarto = gyarto;
        this.kiadas = kiadas;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLemezNev() {
        return lemezNev;
    }

    public void setLemezNev(String lemezNev) {
        this.lemezNev = lemezNev;
    }

    public String getLemezEgyuttes() {
        return lemezEgyuttes;
    }

    public void setLemezEgyuttes(String lemezEgyuttes) {
        this.lemezEgyuttes = lemezEgyuttes;
    }

    public Besorolas getBesorolas() {
        return besorolas;
    }

    public void setBesorolas(Besorolas besorolas) {
        this.besorolas = besorolas;
    }

    public Mufaj getMufaj() {
        return mufaj;
    }

    public void setMufaj(Mufaj mufaj) {
        this.mufaj = mufaj;
    }

    public Gyarto getGyarto() {
        return gyarto;
    }

    public void setGyarto(Gyarto gyarto) {
        this.gyarto = gyarto;
    }

    public LocalDate getKiadas() {
        return kiadas;
    }

    public void setKiadas(LocalDate kiadas) {
        this.kiadas = kiadas;
    }
}
