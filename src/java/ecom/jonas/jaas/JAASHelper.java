package ecom.jonas.jaas;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class JAASHelper {
	
	LoginContext loginContext = null;

	public JAASHelper() {
	}

	public boolean authenticate(String userid, String password) {
		boolean result = false;
		try {
			loginContext = new LoginContext("FileLogin", new LoginCallback(
					userid, password));
			loginContext.login();
			result = true;
		} catch (LoginException e) {
			// A production quality implementation would log this message
			result = false;
		}
		return result;
	}

	public Subject getSubject() {
		Subject result = null;
		if (null != loginContext) {
			result = loginContext.getSubject();
		}
		return result;
	}

	public static class LoginCallback implements CallbackHandler {
		private String userName = null;
		private String password = null;

		public LoginCallback(String userName, String password) {
			this.userName = userName;
			this.password = password;
		}

		public void handle(Callback[] callbacks) {
			for (int i = 0; i < callbacks.length; i++) {
				if (callbacks[i] instanceof NameCallback) {
					NameCallback nc = (NameCallback) callbacks[i];
					nc.setName(userName);
				} else if (callbacks[i] instanceof PasswordCallback) {
					PasswordCallback pc = (PasswordCallback) callbacks[i];
					pc.setPassword(password.toCharArray());
				}
			}
		}
	}

}
