package agencija.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.AgencijaUser;

public interface AgencijaUserRepository extends JpaRepository<AgencijaUser , Integer> {
	
	AgencijaUser findByUsername(String username);

}
