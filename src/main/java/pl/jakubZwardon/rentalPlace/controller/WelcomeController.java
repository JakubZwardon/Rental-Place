package pl.jakubZwardon.rentalPlace.controller;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import pl.jakubZwardon.rentalPlace.model.Client;
import pl.jakubZwardon.rentalPlace.model.Equipment;
import pl.jakubZwardon.rentalPlace.model.RentalDate;
import pl.jakubZwardon.rentalPlace.model.Transaction;
import pl.jakubZwardon.rentalPlace.repositories.ClientRepository;
import pl.jakubZwardon.rentalPlace.repositories.EquipmentRepository;
import pl.jakubZwardon.rentalPlace.repositories.RentalDateRepository;
import pl.jakubZwardon.rentalPlace.repositories.TransactionRepository;

@Controller
public class WelcomeController {
	private EquipmentRepository  equipmentRepository;
	private ClientRepository clientRepository;
	private TransactionRepository transactionRepository;

	public WelcomeController(EquipmentRepository equipmentRepository, ClientRepository clientRepository,
			TransactionRepository transactionRepository) {
		super();
		this.equipmentRepository = equipmentRepository;
		this.clientRepository = clientRepository;
		this.transactionRepository = transactionRepository;
	}

	@GetMapping("/home")
	public String welcome() {
		return "index";
	}
	
	@GetMapping("/rentEquipment")
	public String choseUserInit(Model model) {
		//Pobieram tylko te które są na magazynie
		Collection<Equipment> equipmentsInDb = this.equipmentRepository.findByisRentedFalse();
		//Nie potrzebuje tranzakcji w tym widoku
		for(Equipment cli : equipmentsInDb) {
			cli.setTransactionsInternal(null);
		}
		model.addAttribute("equipmentsInDb", equipmentsInDb);
		
		Collection<Client> clientsInDb = this.clientRepository.findAll();
		//nie potrzebuje tranzakcji w tym widoku
		for(Client cli : clientsInDb) {
			cli.setTransactionsInternal(null);
		}
		model.addAttribute("clientsInDb", clientsInDb);
		
		model.addAttribute("client", new Client());
		model.addAttribute("equipment", new Equipment());
		model.addAttribute("rentalDate", new RentalDate());
		
		return "/rentEquipment";
	}
	
	@PostMapping("/rentEquipment")
	public String choseUserProcess(Client client, Equipment equipment, RentalDate rentalDate) {
		client = this.clientRepository.findByeMail(client.geteMail());
		equipment = this.equipmentRepository.findById(equipment.getId());
		
		rentalDate.setRentalDate(LocalDate.now());
		//nie może mieć wcześniej id
		rentalDate.setId(null);
			
		Transaction transaction = new Transaction();
			
		transaction.setClient(client);
		equipment.setRented(true);
		transaction.setEquipment(equipment);
			
		rentalDate.setTransaction(transaction);
		transaction.setRentalDate(rentalDate);
			
		this.transactionRepository.save(transaction);
		
		return "redirect:/home";
	}
	
	@PostMapping("/saveTransaction")
	public String saveTransaction(Client client, RentalDate rentalDate, Model model) {
		//client = (Client) model.getAttribute("client");
		System.out.println("wewnątrz saveTransaction");
		System.out.println(client.geteMail());
		//System.out.println(client.getLastName());
		System.out.println(rentalDate.getTargetReturnDate());
		return "redirect:/home";
	}	
}