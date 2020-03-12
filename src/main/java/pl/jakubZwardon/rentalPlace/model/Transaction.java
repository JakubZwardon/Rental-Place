package pl.jakubZwardon.rentalPlace.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * represent an transaction where a client rent a equipment
 * 
 * @author jakub
 *
 */
@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "equipment_id")
	private Equipment equipment;
	
	@OneToOne(mappedBy = "transaction", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	private RentalDate rentalDate;

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public RentalDate getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(RentalDate rentalDate) {
		this.rentalDate = rentalDate;
	}
}
