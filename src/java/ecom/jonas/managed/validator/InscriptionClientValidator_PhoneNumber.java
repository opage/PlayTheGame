package ecom.jonas.managed.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class InscriptionClientValidator_PhoneNumber implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		
		String phoneNumber = (String) value;
		
		Pattern pattern = Pattern.compile("^0[1-9][1-9][1-9][1-9][1-9][1-9][1-9][1-9][1-9]$",Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(phoneNumber);
		
		if (!matcher.matches())
		{
			FacesMessage message = new FacesMessage();
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Ce n'est pas un bon numero");
            message.setDetail("Ce n'est pas un bon numero");
            throw new ValidatorException(message);
		}
		
		
		
	}

}
