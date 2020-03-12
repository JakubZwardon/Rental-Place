package pl.jakubZwardon.rentalPlace.controller;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pl.jakubZwardon.rentalPlace.IdWrapper;
import pl.jakubZwardon.rentalPlace.model.Client;
import pl.jakubZwardon.rentalPlace.model.Transaction;
import pl.jakubZwardon.rentalPlace.repositories.ClientRepository;

@Controller
public class ClientController {
	private ClientRepository clientRepository;
	
	public ClientController(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@GetMapping("/client/new")
	public String newClientInit(Model model, Client client) {
		//Client client = new Client();
		model.addAttribute("client", client);
		//System.out.println("Wartość id w client controller:" + transaction.getId());
		return "newClient2";
	}
	
	@PostMapping("/client/new")
	public String newClientProcess(@Valid Client client, BindingResult bindingResult) {
		System.out.println(client.getFirstName());
		System.out.println(client.getLastName());
		System.out.println(client.getAddress());
		System.out.println(client.getCity());
		System.out.println(client.getTelephone());
		System.out.println(client.geteMail());
		if(bindingResult.hasErrors()) {
			return "newClient2";
		}
		else {
			this.clientRepository.save(client);
			return "redirect:/home";
		}
	}
	
	@GetMapping("/chooseClient")
	public String chooseClientInit(Model model) {
		Collection<Client> clientsInDb = this.clientRepository.findAll();
		//nie potrzebuje tranzakcji w tym widoku
		for(Client cli : clientsInDb) {
			cli.setTransactionsInternal(null);
		}
		model.addAttribute("clientsInDb", clientsInDb);
		model.addAttribute("client", new Client());
		return "chooseClient";
	}
	
	@PostMapping("/clientDetails")
	public String showClientDetails(Client client, Model model) {
		client = this.clientRepository.findByeMail(client.geteMail());
		//wczytanie tranzakcji
		//System.out.println("Tranzakcja: "+ client.getTransactions().get(0).getEquipment());
		//System.out.println("Tranzakcja: "+ client.getTransactions().get(1).getEquipment());
		model.addAttribute("client", client);
		
		//Dziele tranzakcje na aktywne i te już zamknięte
		java.util.List<Transaction> actualTransaction = new ArrayList<>();
		java.util.List<Transaction> unactualTransaction = new ArrayList<>();
		for(Transaction tr: client.getTransactions()) {
			if(tr.getRentalDate().getReturnDate() == null) {
				actualTransaction.add(tr);
			} else {
				unactualTransaction.add(tr);
			}
		}
		model.addAttribute("actualTransaction", actualTransaction);
		model.addAttribute("unactualTransaction", unactualTransaction);
		return "clientDetails";
	}
}