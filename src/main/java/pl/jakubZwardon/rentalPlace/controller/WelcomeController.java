package pl.jakubZwardon.rentalPlace.controller;

import java.time.LocalDate;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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
	private RentalDateRepository rentalDateRepository;

	public WelcomeController(EquipmentRepository equipmentRepository, ClientRepository clientRepository,
			TransactionRepository transactionRepository, RentalDateRepository rentalDateRepository) {
		super();
		this.equipmentRepository = equipmentRepository;
		this.clientRepository = clientRepository;
		this.transactionRepository = transactionRepository;
		this.rentalDateRepository = rentalDateRepository;
	}
	
//	@InitBinder
//	public void setAllowedFields(WebDataBinder dataBinder) {
//		dataBinder.setDisallowedFields("id");
//	}

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
		
//		RentalDate rentalDate2 = new RentalDate();
//		rentalDate2.setTargetReturnDate(rentalDate.getTargetReturnDate());
//		rentalDate2.setRentalDate(LocalDate.now());
			
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
	
	@GetMapping("/createDatabase")
	public String createDatabase() {
		Client client = new Client();
		client.setFirstName("Jakub");
		client.setLastName("Zwardoń");
		client.setCity("Jasienica");
		client.setAddress("Tulipanów 1373/2");
		client.setTelephone("505951940");
		client.seteMail("jakubzwardon@gmail.com");
		this.clientRepository.save(client);
		
		Client client2 = new Client();
		//client.setId(2);
		client2.setFirstName("Ania");
		client2.setLastName("Chmielewska");
		client2.setCity("Katowice");
		client2.setAddress("polna 373/2");
		client2.setTelephone("698987756");
		client2.seteMail("aniach@gmail.com");
		this.clientRepository.save(client2);
		
		Equipment equipment = new Equipment();
		equipment.setName("Makita");
		equipment.setProdDate("2019-02-02");
		equipment.setDescription("kosiarka");
		this.equipmentRepository.save(equipment);
		
		Equipment equipment2 = new Equipment();
		//equipment.setId(2);
		equipment2.setName("Bosh");
		equipment2.setProdDate("2012-03-02");
		equipment2.setDescription("wkrętarka");
		this.equipmentRepository.save(equipment2);
		
		return "redirect:home";
	}
	
	@GetMapping("/testOnetoOne")
	public String testOneToOne() {
		RentalDate rentalDate = new RentalDate();
		rentalDate.setRentalDate(LocalDate.now());
		
		Transaction transaction = new Transaction();
		
		rentalDate.setTransaction(transaction);
		transaction.setRentalDate(rentalDate);
		
		transactionRepository.save(transaction);
		return "redirect:home";
	}
}