package pl.jakubZwardon.rentalPlace.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.jakubZwardon.rentalPlace.model.Client;

public interface ClientRepository extends Repository<Client, Integer> {
	//@Transactional(readOnly = true)
	public Collection<Client> findAll();
	
	//22:26:37	SELECT * FROM rentalplace.clients LIMIT 0, 1000	2 row(s) returned	0,00032 sec / 0,000022 sec
	@Transactional(readOnly = true)
	@Query("SELECT client FROM Client client")
	public Collection<Client> find();
	
	//@Query("SELECT client FROM Client client left join fetch client.transactions WHERE client.eMail =:eMail")
	//@Transactional(readOnly = true)
	public Client findByeMail(String eMail);
	
	public void save(Client client);
}
