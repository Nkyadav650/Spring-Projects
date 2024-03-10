package com.eidiko.employee.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private String accessId;
	
	// private Collection<? extends GrantedAuthority> authorities;

	 public CustomUserDetails(CustomUserDetails user) {
		    this.username = user.getUsername();
		    this.password = user.getPassword();
		    this.accessId=user.getAccessId();
		   
		    //this.authorities = authorities;
		}



	/*
	 * public CustomUserDetails(CustomUserDetails user ,
	 * List<SimpleGrantedAuthority> autherities ) { this.username =
	 * user.getUsername(); this.password = user.getPassword(); //
	 * System.out.println("inside the Constructor of CustomUserDetailsService: "
	 * +autherities); //this.accessLevelId=autherities; }
	 * 
	 * @Override public Collection<? extends GrantedAuthority> getAuthorities() {
	 * 
	 * 
	 * List<GrantedAuthority> list=new ArrayList<>(); list.add(new
	 * SimpleGrantedAuthority(accessLevelId));
	 * 
	 * 
	 * 
	 * return null; }
	 */

	 @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
		 List<GrantedAuthority> list=new ArrayList<>(); 
		 list.add(new SimpleGrantedAuthority(accessId));
		 return list;
	    }
	 
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}






}