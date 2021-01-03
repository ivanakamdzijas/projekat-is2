package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rezervacija database table.
 * 
 */
@Entity
@NamedQuery(name="Rezervacija.findAll", query="SELECT r FROM Rezervacija r")
public class Rezervacija implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRezervacija;

	private int brOsoba;

	private boolean placeno;

	private int ukupnaCena;
	@ManyToOne
	@JoinColumn(name="aranzman_idAranzman")
	private Aranzman aranzman;

	public Aranzman getAranzman() {
		return aranzman;
	}



	public void setAranzman(Aranzman aranzman) {
		this.aranzman = aranzman;
	}

	//bi-directional many-to-one association to AgencijaUser
	@ManyToOne
	@JoinColumn(name="idUser")
	private AgencijaUser agencijaUser;

	public Rezervacija() {
	}

	

	public int getIdRezervacija() {
		return idRezervacija;
	}



	public void setIdRezervacija(int idRezervacija) {
		this.idRezervacija = idRezervacija;
	}



	public int getBrOsoba() {
		return this.brOsoba;
	}

	public void setBrOsoba(int brOsoba) {
		this.brOsoba = brOsoba;
	}

	public boolean getPlaceno() {
		return this.placeno;
	}

	public void setPlaceno(boolean placeno) {
		this.placeno = placeno;
	}

	public int getUkupnaCena() {
		return this.ukupnaCena;
	}

	public void setUkupnaCena(int ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}

	public AgencijaUser getAgencijaUser() {
		return this.agencijaUser;
	}

	public void setAgencijaUser(AgencijaUser agencijaUser) {
		this.agencijaUser = agencijaUser;
	}

}