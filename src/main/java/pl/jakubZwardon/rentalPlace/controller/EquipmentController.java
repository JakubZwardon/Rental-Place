package pl.jakubZwardon.rentalPlace.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import pl.jakubZwardon.rentalPlace.model.Client;
import pl.jakubZwardon.rentalPlace.model.Equipment;
import pl.jakubZwardon.rentalPlace.repositories.EquipmentRepository;

@Controller
public class EquipmentController {
	private EquipmentRepository equipmentRepository;
	
	public EquipmentController(EquipmentRepository equipmentRepository) {
		this.equipmentRepository = equipmentRepository;
	}

	@GetMapping("/equipment/new")
	public String newEquipmentInit(Model model) {
		Equipment equipment = new Equipment();
		model.addAttribute("equipment", equipment);
		return "newEquipment";
	}
	
	@PostMapping("/equipment/new")
	public String newEquipmentProcess(@Valid Equipment equipment, BindingResult bindingResult) {
		System.out.println(equipment.getName());
		System.out.println(equipment.getDescription());
		if(bindingResult.hasErrors()) {
			return "newEquipment";
		}
		else {
			this.equipmentRepository.save(equipment);
			return "redirect:/home";
		}
	}
	
	@GetMapping("/chooseEquipment")
	public String chooseEquipmentInit(Model model) {
		Collection<Equipment> equipmentsInDb = this.equipmentRepository.findAll();
		model.addAttribute("equipmentsInDb", equipmentsInDb);
		model.addAttribute("equipment", new Equipment());
		return "chooseEquipment";
	}
	
	@GetMapping("/equipments/{id}/equipmentDetails")
	public String showEquipmentDetails(@PathVariable("id") int id, Model model) {
		Equipment equipment = this.equipmentRepository.findById(id);
		model.addAttribute("equipment", equipment);
		return "equipmentDetails";
	}
}