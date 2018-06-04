package ecom.jonas.managed.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.icesoft.faces.component.ext.HtmlInputSecret;
import com.icesoft.faces.component.ext.HtmlPanelGrid;

public class InscriptionClientValidator_Password implements Validator {
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value)	throws ValidatorException {
		
		String confirmPassword = (String) value;
		
		HtmlPanelGrid panelGrid = (HtmlPanelGrid) component.getParent().getParent();
		HtmlInputSecret passwordInput = (HtmlInputSecret) panelGrid.findComponent("passwordNewCus");
		
		String password = (String) passwordInput.getValue();
		
		if (!password.equals(confirmPassword))
		{
			FacesMessage message = new FacesMessage();
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Mot de passe n'est pas identique");
            message.setDetail("Mot de passe n'est pas identique");
            //context.addMessage("userForm:Email", message);
            throw new ValidatorException(message);
		}

	}
}
