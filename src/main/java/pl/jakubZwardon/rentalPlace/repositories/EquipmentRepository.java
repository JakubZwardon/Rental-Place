package pl.jakubZwardon.rentalPlace.repositories;

import java.util.Collection;

import org.springframework.data.repository.Repository;

import pl.jakubZwardon.rentalPlace.model.Equipment;

public interface EquipmentRepository extends Repository<Equipment, Integer> {
	public void save(Equipment equipment);
	
	public Collection<Equipment> findAll();
	public Equipment findById(Integer id);
	public Collection<Equipment> findByisRentedFalse();
}
