package pl.jakubZwardon.rentalPlace.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.jakubZwardon.rentalPlace.model.Transaction;
import pl.jakubZwardon.rentalPlace.repositories.TransactionRepository;

@Controller
public class TransactionController {
	private final TransactionRepository transactions;
	
	public TransactionController(TransactionRepository transactions) {
		this.transactions = transactions;
	}
	
	@GetMapping("/transactions/{id}")
	public String showTransaction(@PathVariable("id") int id, Model model) {
		Transaction transaction = this.transactions.findById(id);
		model.addAttribute("transaction", transaction);
		model.addAttribute("client", transaction.getClient());
		model.addAttribute("equipment", transaction.getEquipment());
		model.addAttribute("rentalDate", transaction.getRentalDate());
		return"transactionDetails";
	}
	
	@GetMapping("/transactions/{id}/close")
	public String closeTransaction(@PathVariable("id") int id, Model model) {
		Transaction transaction = this.transactions.findById(id);
		transaction.getRentalDate().setReturnDate(LocalDate.now());
		transaction.getEquipment().setRented(false);
		this.transactions.save(transaction);
		return "redirect:/transactions/" + id;
	}	
}
