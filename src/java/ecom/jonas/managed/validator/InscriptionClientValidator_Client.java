package ecom.jonas.managed.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import ecom.jonas.session.SearchEngineRemote;

public class InscriptionClientValidator_Client implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value)	throws ValidatorException {
		
		String email = (String) value;
		
		Pattern pattern = Pattern.compile("^[\\n &lt;&quot;\']*([a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+)",Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		
		if (!matcher.matches())
		{
			FacesMessage message = new FacesMessage();
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Email is not valid");
            message.setDetail("Email is not valid");
            context.addMessage("userForm:Email", message);
            throw new ValidatorException(message);
		}
		

	}
}
