package agencija.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
		@Autowired
	    @Qualifier("customUserDetailsService")
	    UserDetailsService userDetailsService;
	 
			@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
				auth.inMemoryAuthentication(). withUser("zap1").
				 password("{noop}123"). roles("zaposleni"); 
				auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
			   
		}

	
			@Override
			protected void configure(HttpSecurity http) throws Exception {
			    http.authorizeRequests()
			   
			    .antMatchers("/index.jsp").hasAnyRole( "korisnik", "zaposleni")
			    .antMatchers("/auth/zaposleni/**", "/izvestaji/**").hasRole("zaposleni")
			    .antMatchers("/auth/korisnik/**").hasRole("korisnik")
			    .antMatchers("/auth/registerUser", "/auth/register").permitAll()
			    
			    .anyRequest().authenticated()
		        .and()
		        .formLogin()
		        .loginPage("/auth/loginPage")
		        .permitAll()
		        .loginProcessingUrl("/login") 
		        .defaultSuccessUrl("/index.jsp") 
		        .and()
			    .logout().
			    logoutUrl("/logout")
			    .logoutSuccessUrl("/")
		        .and()
		        .csrf()
		        .and().exceptionHandling() 
		        .accessDeniedPage("/Greska.jsp");
		        
			}
	
 

}
