package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the aranzman database table.
 * 
 */
@Entity
@NamedQuery(name="Aranzman.findAll", query="SELECT a FROM Aranzman a")
public class Aranzman implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAranzman;

	private int brSlobMesta;

	@Temporal(TemporalType.DATE)
	private Date datumD;

	@Temporal(TemporalType.DATE)
	private Date datumP;

	private String nazivA;
	
	@OneToMany(mappedBy="aranzman")
	private List<Rezervacija> rezervacijas;

	public List<Rezervacija> getRezervacijas() {
		return rezervacijas;
	}



	public void setRezervacijas(List<Rezervacija> rezervacijas) {
		this.rezervacijas = rezervacijas;
	}
	public Rezervacija addRezervacija(Rezervacija rezervacija) {
		getRezervacijas().add(rezervacija);
		rezervacija.setAranzman(this);;

		return rezervacija;
	}

	public Rezervacija removeRezervacija(Rezervacija rezervacija) {
		getRezervacijas().remove(rezervacija);
		rezervacija.setAranzman(null);

		return rezervacija;
	}



	public Aranzman() {
	}

	

	public int getBrSlobMesta() {
		return this.brSlobMesta;
	}

	public void setBrSlobMesta(int brSlobMesta) {
		this.brSlobMesta = brSlobMesta;
	}

	public Date getDatumD() {
		return this.datumD;
	}

	public void setDatumD(Date datumD) {
		this.datumD = datumD;
	}

	public Date getDatumP() {
		return this.datumP;
	}

	public void setDatumP(Date datumP) {
		this.datumP = datumP;
	}

	public String getNazivA() {
		return this.nazivA;
	}

	public void setNazivA(String nazivA) {
		this.nazivA = nazivA;
	}
	
	@ManyToOne
	@JoinColumn(name="smestaj_idSmestaj")
	private Smestaj smestaj;

	public int getIdAranzman() {
		return idAranzman;
	}



	public void setIdAranzman(int idAranzman) {
		this.idAranzman = idAranzman;
	}



	public Smestaj getSmestaj() {
		return smestaj;
	}



	public void setSmestaj(Smestaj smestaj) {
		this.smestaj = smestaj;
	}

}