package pl.jakubZwardon.rentalPlace.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Represent types of equipment: digger, hammer, lawnmower
 * @author jakub
 *
 */
@Entity
@Table(name = "equipmentTypes")
public class EquipmentType extends NamedEntity {

}
