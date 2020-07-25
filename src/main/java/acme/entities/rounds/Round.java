
package acme.entities.rounds;

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

import org.hibernate.validator.constraints.URL;

import acme.entities.roles.Entrepreneur;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "kind"), @Index(columnList = "status")
})
public class Round extends DomainEntity {

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

	@Pattern(regexp = "^(SEED|ANGEL|SERIES-A|SERIES-B|SERIES-C|BRIDGE)$")
	@NotBlank
	private String				kind;

	@NotBlank
	private String				title;

	@NotBlank
	private String				description;

	@Valid
	@NotNull
	private Money				money;

	@NotBlank
	@URL
	private String				information;

	@NotNull
	private boolean				status;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Entrepreneur		entrepreneur;

}
