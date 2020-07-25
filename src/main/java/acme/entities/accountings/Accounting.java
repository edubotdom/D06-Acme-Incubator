
package acme.entities.accountings;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import acme.entities.roles.Bookkeeper;
import acme.entities.rounds.Round;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "status")
})
public class Accounting extends DomainEntity {

	// Serialisation identifier
	private static final long	serialVersionUID	= 1L;

	// Atributos
	@NotBlank
	private String				title;

	@Past
	@NotNull
	private Date				creation;

	@NotNull
	private boolean				status;

	@NotBlank
	private String				body;

	@NotNull
	@Valid
	@ManyToOne
	private Bookkeeper			bookkeeper;

	@NotNull
	@Valid
	@ManyToOne
	private Round				round;

}
