package pl.jakubZwardon.rentalPlace.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

@Entity
@Table(name = "clients")
public class Client extends Person {
	@Column(name = "address")
	@NotEmpty
	private String address;
	@Column(name = "city")
	@NotEmpty
	private String city;
	@Column(name = "e_mail")
	@NotEmpty
	@javax.validation.constraints.Email
	private String eMail;
	@Column(name = "telephone")
	@NotEmpty
	@Digits(fraction = 0, integer = 10)
	private String telephone;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "client", fetch = FetchType.LAZY)
	private Set<Transaction> transactions;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	protected Set<Transaction> getTransactionsInternal() {
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
		transaction.setClient(this);
	}
	
	@Override
	public String toString() {
		return "Client [id=" + this.getId() + ", first_neme=" + this.getFirstName() + ", last_name=" + this.getLastName() + 
				", address=" + address + ", city=" + city + ", eMail=" + eMail + ", telephone=" + telephone + "]";
	}
}
