package ecom.jonas.jaas;

import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

public class JAASActionListener {

	private ActionListener parent = null;

	public JAASActionListener(ActionListener parent) {
		this.parent = parent;
	}

	public void processAction(ActionEvent event)
			throws AbortProcessingException {
		FacesContext context = FacesContext.getCurrentInstance();
		UIOutput comp = null;
		String userid = null, password = null;
		JAASHelper jaasHelper = new JAASHelper();

		// Check to see if they are on the login page.
		boolean onLoginPage = (-1 != context.getViewRoot().getViewId()
				.lastIndexOf("login")) ? true : false;
		if (onLoginPage) {
			if (null != (comp = (UIOutput) context.getViewRoot().findComponent(
					"form:userid"))) {
				userid = (String) comp.getValue();
			}
			if (null != (comp = (UIOutput) context.getViewRoot().findComponent(
					"form:password"))) {
				password = (String) comp.getValue();
			}
			// If JAAS authentication failed
			if (!jaasHelper.authenticate(userid, password)) {
				context.getApplication().getNavigationHandler()
						.handleNavigation(context, null, "login");
				return;
			} else {
				// Subject must not be null, since authentication succeeded
				assert (null != jaasHelper.getSubject());
				// Put the authenticated subject in the session.
				context.getExternalContext().getSessionMap().put("JAASSubject", jaasHelper.getSubject());
			}
		}
		parent.processAction(event);
	}

}
