
package acme.entities.technologies;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "source"), @Index(columnList = "sector")
})
public class Technology extends DomainEntity {

	// Serialisation identifier -----------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------

	@NotBlank
	private String				title;

	@NotBlank
	private String				sector;

	@NotBlank
	private String				inventor;

	@NotBlank
	private String				description;

	@NotBlank
	@URL
	private String				website;

	@NotBlank
	@Email
	private String				contact;

	@NotBlank
	@Pattern(regexp = "^(open-source)?(closed-source)?$")
	private String				source;

	@Range(min = -5, max = 5)
	private Double				stars;

}
