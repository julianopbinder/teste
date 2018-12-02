package com.binder.helpdesk.api.security.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.binder.helpdesk.api.security.jwt.JwtTokenUtil;
import com.binder.helpdesk.api.security.model.CurrentUser;
import com.binder.helpdesk.api.service.UserService;
import com.binder.helpdesk.api.entity.User;
import com.binder.helpdesk.api.security.jwt.JwtAuthenticationResquest;

@RestController
@CrossOrigin(origins = "*") // permite qualquer ip acessar
public class AuthenticationRestController {

	//interface spring
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	//interface spring
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private UserService userService;

	@PostMapping(value = "/api/auth")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationResquest authenticationRequest)
			throws AuthenticationException {
		
							 //spring         //spring	
		final Authentication authentication = authenticationManager
				//spring          //spring
				.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
						authenticationRequest.getPassword()));
		
		//objeto "authentication" passado por parametro
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		//spring     
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
		
		//gera token 
		final String token = jwtTokenUtil.generateToken(userDetails);
		
		//busca email
		final User user = userService.findByEmail(authenticationRequest.getEmail());
		
		user.setPassword(null);
		
		//retorna token - user(id+email+password(seta para nulo) + profile - tudo são atributos da classe User
		return ResponseEntity.ok(new CurrentUser(token, user));
	}

	@PostMapping(value = "/api/refresh")
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		String username = jwtTokenUtil.getUsernameFromToken(token);
		final User user = userService.findByEmail(username);

		if (jwtTokenUtil.canTokenBeRefreshed(token)) {
			String refreshedToken = jwtTokenUtil.refreshToken(token);
			return ResponseEntity.ok(new CurrentUser(refreshedToken, user));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

}
