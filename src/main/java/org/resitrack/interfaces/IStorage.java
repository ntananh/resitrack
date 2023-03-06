package org.resitrack.interfaces;

import org.resitrack.entity.Person;

import java.util.List;

public interface IStorage {
    void save();
    boolean delete();
}
