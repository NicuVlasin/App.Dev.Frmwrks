package ie.cit.soft8027.repository;

import ie.cit.soft8027.domain.Equipment;

import java.util.List;


public interface EquipmentRepository {

    public Equipment get(int id);

    public Equipment findByTypeAndLevel(String type,int level);

    public void save(Equipment equipment);

    public void remove(Equipment equipment);

    public List<Equipment> findAll();
}