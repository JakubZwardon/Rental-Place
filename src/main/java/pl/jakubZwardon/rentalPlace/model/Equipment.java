package pl.jakubZwardon.rentalPlace.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.lang.NonNull;

/**
 * Simple domain object represent equipment which will be rent to the clients
 * @author jakub
 *
 */

@Entity
@Table(name="equipments")
public class Equipment extends NamedEntity {
	@Column(name = "producer")
	@NotEmpty
	private String producer;
	
	@Column(name = "model")
	@NotEmpty
	private String model;
	
	@Column(name = "prod_date")
	@NotEmpty
	private String prodDate;
	
	@Column(name = "is_rented")
	@NotNull
	private boolean isRented;
	
	@Column(name = "description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "equipment_type_id")
	private EquipmentType equipmentType;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "equipment")
	private Set<Transaction> transactions;
	
	public Equipment() {
		super();
		this.isRented = false;
	}

	public String getProdDate() {
		return prodDate;
	}

	public void setProdDate(String prodDate) {
		this.prodDate = prodDate;
	}

	public boolean isRented() {
		return isRented;
	}
	
	//dubluje getter z powodu Thymeleaf, który nie raguje na powyższy
	public boolean getIsRented() {
		return isRented;
	}

	public void setRented(boolean isRented) {
		this.isRented = isRented;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public EquipmentType getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(EquipmentType equipmentType) {
		this.equipmentType = equipmentType;
	}

	public Set<Transaction> getTransactionsInternal() {
		if (this.transactions == null) {
			this.transactions = new HashSet<>();
		}
		return this.transactions;
	}

	public void setTransactionsInternal(Set<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	public List<Transaction> getTransactions() {
		List<Transaction> sortedTransactions = new ArrayList<>(getTransactionsInternal());
		PropertyComparator.sort(sortedTransactions, new MutableSortDefinition("name", true, true));
		return Collections.unmodifiableList(sortedTransactions);
	}

	public void addTransaction(Transaction transaction) {
		getTransactionsInternal().add(transaction);
		transaction.setEquipment(this);
	}
}
