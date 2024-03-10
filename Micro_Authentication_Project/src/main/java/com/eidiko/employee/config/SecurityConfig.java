package com.eidiko.employee.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.eidiko.employee.filter.JwtAuthFilter;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtAuthenticationEntryPoint point;
	@Autowired
	JwtAuthFilter authFilter;

	@Bean
	public RoleHierarchy roleHierarchy() {
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		roleHierarchy.setHierarchy("1008 >1007 >1005 >1001");
		return roleHierarchy;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	//	SecurityExpressionHandler<FilterInvocation> expressionHandler = new DefaultWebSecurityExpressionHandler();
		
		return http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth
				.requestMatchers("/emp/saveEmp", "/auth/token", "/auth/validate/{token}","/emp/applyLeave/{empId}")
				.permitAll()

				.requestMatchers("/emp/applyLeave/{empId}", "/getEmpLeaveByEmpId/{empId}",
						"/getAllEmpLeaveByStatusByEmpId/{empId}/{status}",
						"/auth/getFor444", "/auth/getFor555","/emp/getEmp/{empId}",
						"/manager/getReportingManagerByempId/{empId}").hasAuthority("1001")
				
				.requestMatchers("/emp/**","/empConfig/**","/auth/getFor333","/managr/getManagerByEmpId/{empId}",
						"/manager/getManagerByManagerId/{managerId}","/manager/getAllEmployeeByReportingManager/{managerId}")
				.hasAuthority("1005")
				
				.requestMatchers("/emp/**","/manager/**","/empConfig/**", "/empLeave/**", "/auth/getFor111").hasAuthority("1007")
				//for Restrictions
				//.requestMatchers(HttpMethod.POST,"/empLeave/saveAccesslevel","").hasRole("1007")
				
				.requestMatchers("/emp/**","/empConfig/**", "/empLeave/**","/leaveConfig/**","/manager/**").hasAuthority("1008")
				.anyRequest().authenticated())
				.exceptionHandling(ex->ex.authenticationEntryPoint(point))
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		log.info("Before Authentication*******");
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		log.info("Before Authentication*******: " + userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		log.info("Before Authentication*******: " + passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	/*
	 * private SecurityExpressionHandler<FilterInvocation> webExpressionHandler() {
	 * DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = new
	 * DefaultWebSecurityExpressionHandler();
	 * defaultWebSecurityExpressionHandler.setRoleHierarchy(roleHierarchy()); return
	 * defaultWebSecurityExpressionHandler; }
	 */
}
