package com.binder.helpdesk.api.security.jwt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.binder.helpdesk.api.entity.User;
import com.binder.helpdesk.api.enums.ProfileEnum;

public class JwtUserFactory {

	private JwtUserFactory() {
		
	}
	
	//Converte/gera um JWTUser com base nos dados do Usuário
	public static JwtUser create(User user) {
		return new JwtUser(user.getId(),user.getEmail(),user.getPassword(),
				mapToGrantedAuthorities(user.getProfile()));
	   	 
				
	}
				
	//converte o perfil do usuário para o formato do spring security
	private static List<GrantedAuthority> mapToGrantedAuthorities(ProfileEnum profileEnum){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(profileEnum.toString()));
		return authorities;
	}
	
	
	
}
