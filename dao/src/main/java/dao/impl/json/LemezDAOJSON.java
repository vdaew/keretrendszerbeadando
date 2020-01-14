package dao.impl.json;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dao.LemezDAO;
import lemezbolt.exceptions.NoMatchingBandException;
import lemezbolt.exceptions.NoMatchingGenreException;
import lemezbolt.exceptions.NoMatchingIDException;
import lemezbolt.models.Lemez;
import lemezbolt.models.Mufaj;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class LemezDAOJSON implements LemezDAO {
    File jsonFile;
    ObjectMapper mapper;

    public LemezDAOJSON(File jsonFile) {
        this.jsonFile = jsonFile;

        if (jsonFile.exists()) {
            try {
                jsonFile.createNewFile();
                FileWriter writer = new FileWriter(jsonFile);
                writer.write("[]");
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
    }

    public LemezDAOJSON(String jsonFilePath) {
        this.jsonFile = new File(jsonFilePath);

        if (jsonFile.exists()) {
            try {
                jsonFile.createNewFile();
                FileWriter writer = new FileWriter(jsonFilePath);
                writer.write("[]");
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
    }

    public void createLemez(Lemez lemez) {
        Collection<Lemez> lemezek = new ArrayList<Lemez>();
        lemezek.add(lemez);

        try{
            mapper.writeValue(jsonFile, lemezek);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Collection<Lemez> readAllLemez() {
        Collection<Lemez> lemezek = new ArrayList<Lemez>();
        try{
            lemezek = mapper.readValue(jsonFile, new TypeReference<Collection<Lemez>>() {
            });
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lemezek;
    }

    public Lemez readLemez(UUID id) throws NoMatchingIDException {
        Collection<Lemez> lemezek = readAllLemez();
        for (Lemez a: lemezek) {
            if (a.getId().toString().equalsIgnoreCase(id.toString())) {
                return a;
            }
        }
        throw new NoMatchingIDException();
    }

    public Lemez readLemez(String lemezEgyuttes) throws NoMatchingBandException {
        Collection<Lemez> lemezek = readAllLemez();
        for (Lemez a: lemezek)
        {
            if(a.getLemezEgyuttes().equalsIgnoreCase(lemezEgyuttes)) {
                return a;
            }
        }
        throw new NoMatchingBandException();
    }

    public Lemez readLemez(Mufaj mufaj) throws NoMatchingGenreException {
        Collection<Lemez> lemezek = readAllLemez();
        for (Lemez a: lemezek)
        {
            if(a.getMufaj().toString().equalsIgnoreCase(mufaj.toString())) {
                return a;
            }
        }
        throw new NoMatchingGenreException();
    }

    public void updateLemez(Lemez lemez) throws NoMatchingIDException {
        Lemez deleteThis = new Lemez();
        deleteThis.setId(lemez.getId());
        deleteLemez(deleteThis);
        createLemez(lemez);

    }

    public void deleteLemez(Lemez lemez) throws NoMatchingIDException {
        Collection<Lemez> autok = readAllLemez();
        Collection<Lemez> result = new ArrayList<Lemez>();
        try {
            Lemez deleteThis = readLemez(lemez.getId());

            for(Lemez a : autok){
                if(!(a.getId().equals(deleteThis.getId()))){
                    result.add(a);
                }
            }

            mapper.writeValue(jsonFile, result);
        } catch (NoMatchingIDException noMatchingIDException) {
            throw noMatchingIDException;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
