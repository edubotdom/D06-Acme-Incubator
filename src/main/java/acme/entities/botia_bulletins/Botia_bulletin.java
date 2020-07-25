
package acme.entities.botia_bulletins;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "expiring_date")
})
public class Botia_bulletin extends DomainEntity {

	// Serialisation identifier -----------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				moment;

	@Temporal(TemporalType.TIMESTAMP)
	private Date				expiring_date;

	@NotBlank
	private String				author;

	@NotBlank
	private String				title;

	@NotBlank
	private String				text_body;

}
