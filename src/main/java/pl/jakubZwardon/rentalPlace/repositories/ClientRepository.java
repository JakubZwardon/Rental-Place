package pl.jakubZwardon.rentalPlace.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.jakubZwardon.rentalPlace.model.Client;

public interface ClientRepository extends Repository<Client, Integer> {
	public Collection<Client> findAll();
	
	public Client findByeMail(String eMail);
	
	public void save(Client client);
}
