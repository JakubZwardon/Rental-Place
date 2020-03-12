package pl.jakubZwardon.rentalPlace.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;

/**
 * simple domain object extended <code>BaseEntity</code> by the name property
 * 
 * @author jakub
 */
@MappedSuperclass
public class NamedEntity extends BaseEntity {
	@Column(name = "name")
	//@NotEmpty
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "To string klasy NamedEntity; NamedEntity [name=" + name + "]";
	}
}
