package ecom.jonas.jaas;

import javax.security.auth.Subject;

public class JAASSubject {
	 private Subject subject;
	   
	   public JAASSubject(Subject subject) {
	      this.subject = subject;
	   }
	   
	   public Subject getSubject() {
	      return subject;
	   }
}
