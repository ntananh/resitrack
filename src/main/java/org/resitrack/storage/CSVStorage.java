package org.resitrack.storage;

import org.resitrack.entity.Person;
import org.resitrack.interfaces.IStorage;

import java.io.FileNotFoundException;
import java.util.List;

public class CSVStorage implements IStorage {
    private String personToString(Person person){
        return person.getName() + "," + person.getSex() + "," + person.getDob();
    }
    public void save(List<Person> people) {
        try {
            writeToFile(convertObject(people), "D:/test.txt");

        }catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }

    }
    private String convertObject(List<Person> people){
        StringBuilder storeData = new StringBuilder();
        for(Person person : people){
           storeData.append(personToString(person));
           storeData.append("\n");

        }
        return storeData.toString();

    }
    private void writeToFile(String data, String filePath) throws FileNotFoundException {

    }

    @Override
    public void save() {

    }

    @Override
    public boolean delete() {
        return false;
    }
}
