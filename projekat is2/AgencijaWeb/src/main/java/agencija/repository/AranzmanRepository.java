package agencija.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Aranzman;

public interface AranzmanRepository extends JpaRepository<Aranzman, Integer> {
	@Query("select a from Aranzman a order by a.smestaj.cenaPoOsobi asc")
	List<Aranzman> filtrirajPoCeniRastuce();
	@Query("select a from Aranzman a order by a.smestaj.cenaPoOsobi desc")
	List<Aranzman> filtrirajPoCeniOpadajuce();
}
