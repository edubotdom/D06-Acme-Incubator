
package acme.entities.forums;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.entities.rounds.Round;
import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Forum extends DomainEntity {

	// Serialisation identifier
	private static final long	serialVersionUID	= 1L;
	//Attributes

	//Derived attributes

	//Relationships

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Authenticated		creator;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Round				round;
}
