package ecom.jonas.managed.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class InscriptionClientValidator_Birthday implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		
		String birthday = (String) value;
		
		Pattern pattern = Pattern.compile("^([0-9]{0,2})/([0-9]{0,2})/([0-9]{0,4})$",Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(birthday);
		
		if (!matcher.matches())
		{
			FacesMessage message = new FacesMessage();
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("La date n'est pas correct");
            message.setDetail("La date n'est pas correct");
            throw new ValidatorException(message);
		}
		
		
		
	}

}
