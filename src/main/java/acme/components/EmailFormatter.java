
package acme.components;

import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.format.Formatter;

import acme.datatypes.Email;
import acme.framework.helpers.MessageHelper;
import acme.framework.helpers.StringHelper;

public class EmailFormatter implements Formatter<Email> {

	//Formatter<Email> interface ------------------

	@Override
	public String print(final Email object, final Locale locale) {
		assert object != null;
		assert locale != null;

		String result = null;
		String userText, domainText, nameText, nameEndText = null;

		nameText = object.getName() == null ? "" : String.format("%s <", object.getName());
		userText = String.format("%s", object.getUser());
		domainText = String.format("%s", object.getDomain());
		nameEndText = object.getName() == null ? "" : String.format(">");
		result = String.format("%s%s@%s%s", nameText, userText, domainText, nameEndText);

		return result;

	}

	@Override
	public Email parse(final String text, final Locale locale) throws ParseException {
		assert !StringHelper.isBlank(text);
		assert locale != null;

		Email result;
		String nameRegex, userRegex, domainRegex;
		String emailRegex;
		Pattern pattern;
		Matcher matcher;
		String errorMessage;
		String nameText, userText, domainText;

		nameRegex = "\\w+(?:\\s*\\w*\\.*\\-*)*";
		userRegex = "\\w+(?:\\.*\\w*)*";
		domainRegex = "\\w+\\.\\w(?:\\.*\\w)*";

		emailRegex = String.format(//
			"^\\s*(?:(?<DN>%1$s)\\s*\\<)?(?<US>%2$s)\\@(?<DO>%3$s)\\>?\\s*$", nameRegex, //
			userRegex, //
			domainRegex //
		);

		pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
		matcher = pattern.matcher(text);

		if (!matcher.find()) {
			errorMessage = MessageHelper.getMessage("default.error.conversion", null, "Invalid value", locale);
			throw new ParseException(errorMessage, 0);
		} else {
			nameText = matcher.group("DN");
			userText = matcher.group("US");
			domainText = matcher.group("DO");

			result = new Email();
			result.setName(nameText);
			result.setUser(userText);
			result.setDomain(domainText);
		}

		return result;
	}
}
