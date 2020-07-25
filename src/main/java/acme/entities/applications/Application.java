
package acme.entities.applications;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import acme.entities.roles.Investor;
import acme.entities.rounds.Round;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "status"), @Index(columnList = "creation"), @Index(columnList = "ticker")
})
public class Application extends DomainEntity {

	// Serialisation identifier
	private static final long	serialVersionUID	= 1L;

	// Atributos
	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{3}X{0,2}-[0-9]{2}-[0-9]{6}$")
	@NotBlank
	private String				ticker;

	@NotNull
	@Past
	private Date				creation;

	@NotBlank
	private String				statement;

	@Valid
	@NotNull
	private Money				offer;

	@Pattern(regexp = "^(accepted|pending|rejected)$")
	@NotBlank
	private String				status;

	private String				justification;

	//Relaciones
	@NotNull
	@Valid
	@ManyToOne
	private Round				round;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Investor			investor;
}
