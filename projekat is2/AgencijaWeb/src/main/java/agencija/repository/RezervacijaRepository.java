package agencija.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import model.AgencijaUser;
import model.Aranzman;
import model.Rezervacija;

public interface RezervacijaRepository extends JpaRepository<Rezervacija, Integer> {
	List<Rezervacija> findByAranzman(Aranzman a);
	List<Rezervacija> findByAgencijaUser(AgencijaUser user);

	//List<Rezervacija> findByPlaceno(boolean placeno);
	
	List<Rezervacija> findByPlacenoOrderByAranzman(boolean placeno);
}
