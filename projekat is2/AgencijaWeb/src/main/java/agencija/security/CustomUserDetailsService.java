package agencija.security;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import agencija.repository.AgencijaUserRepository;
import model.AgencijaUser;

@Service("customUserDetailsService")

public class CustomUserDetailsService implements UserDetailsService{
	
	
	@Autowired
    private AgencijaUserRepository korisnikRepository;  
    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AgencijaUser user = korisnikRepository.findByUsername(username);
		UserDetailsImpl userDetails =new UserDetailsImpl();
		userDetails.setUsername(user.getUsername());
		userDetails.setPassword(user.getPassword());
		userDetails.setPhone(user.getPhone());;
		userDetails.setRezervacijas(user.getRezervacijas());
		userDetails.setRoles(user.getRoles());
		return userDetails;
		
    }
    
    
   



}
