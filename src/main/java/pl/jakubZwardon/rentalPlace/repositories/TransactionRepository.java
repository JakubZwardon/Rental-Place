package pl.jakubZwardon.rentalPlace.repositories;

import org.springframework.data.repository.Repository;

import pl.jakubZwardon.rentalPlace.model.Transaction;

public interface TransactionRepository extends Repository<Transaction, Integer> {
	void save(Transaction transaction);
	
	public Transaction findById(Integer id);
}
