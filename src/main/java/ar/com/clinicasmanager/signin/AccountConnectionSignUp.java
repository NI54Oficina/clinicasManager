package ar.com.clinicasmanager.signin;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;

public class AccountConnectionSignUp implements ConnectionSignUp {

	public String execute(Connection<?> connection) {
		UserProfile profile = connection.fetchUserProfile();
		return profile.getEmail();
	}
}
