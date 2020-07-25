/*
 * UserIdentity.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.datatypes;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.datatypes.DomainDatatype;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Email extends DomainDatatype {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotNull
	private String				user;

	private String				domain;

	@NotBlank
	private String				name;

	// Derived attributes -----------------------------------------------------


	// Object interface -------------------------------------------------------
	@Override
	public String toString() {
		StringBuilder result;

		result = new StringBuilder();
		if (this.name != null) {
			result.append(this.name);
			result.append(" <");
		}
		result.append(this.user);
		result.append("@");
		result.append(this.domain);
		if (this.name != null) {
			result.append(">");
		}
		return result.toString();
	}
}
