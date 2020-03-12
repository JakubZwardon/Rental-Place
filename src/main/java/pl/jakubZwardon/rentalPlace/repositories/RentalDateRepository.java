package pl.jakubZwardon.rentalPlace.repositories;

import org.springframework.data.repository.Repository;

import pl.jakubZwardon.rentalPlace.model.RentalDate;

public interface RentalDateRepository extends Repository<RentalDate, Integer> {
	public void save(RentalDate rentalDate);
}