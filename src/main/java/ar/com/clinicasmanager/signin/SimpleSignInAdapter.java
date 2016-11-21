package ar.com.clinicasmanager.signin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

public class SimpleSignInAdapter implements SignInAdapter {

	@Autowired
	private TokenBasedRememberMeServices tokenBasedRememberMeServices;

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	public String signIn(String localUserId, Connection<?> connection, NativeWebRequest request) {
		try {
			UserDetails usuario = userDetailsService.loadUserByUsername(localUserId);

			Authentication authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(authentication);

			// Usar .loginSuccess para filtrar si existe el parámetro que indique que se quiere el remember-me
			tokenBasedRememberMeServices.onLoginSuccess((HttpServletRequest) request.getNativeRequest(),
					(HttpServletResponse) request.getNativeResponse(), authentication);

			return "/home";
		} catch (UsernameNotFoundException e) {
			return "/login?login_error=t";
		}
	}

}