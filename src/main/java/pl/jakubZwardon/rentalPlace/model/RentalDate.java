package pl.jakubZwardon.rentalPlace.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Some dates which tells us about transaction
 * 
 * @author jakub
 *
 */

@Entity
@Table(name = "rental_dates")
public class RentalDate extends BaseEntity {
	@Column(name = "rental_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate rentalDate;
	
	@Column(name = "target_return_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate targetReturnDate;
	
	@Column(name = "return_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate returnDate;
	
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "transaction_id", nullable = true)
	private Transaction transaction;

	public LocalDate getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(LocalDate rentalDate) {
		this.rentalDate = rentalDate;
	}

	public LocalDate getTargetReturnDate() {
		return targetReturnDate;
	}

	public void setTargetReturnDate(LocalDate targetReturnDate) {
		this.targetReturnDate = targetReturnDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
}
