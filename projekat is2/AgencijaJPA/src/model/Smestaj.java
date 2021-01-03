package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name="Smestaj.findAll", query="SELECT s FROM Smestaj s")
public class Smestaj implements Serializable  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSmestaj;
	
	private String nazivS;
	
	private String opisS;
	
	private int cenaPoOsobi;
	
	
	@OneToMany(mappedBy="smestaj")
	private List<Aranzman> aranzmans;

	public Aranzman addAranzman(Aranzman aranzman) {
		getAranzmans().add(aranzman);
		aranzman.setSmestaj(this);

		return aranzman;
	}

	public Aranzman removeAranzman(Aranzman aranzman)  {
		getAranzmans().remove(aranzman);
		aranzman.setSmestaj(null);

		return aranzman;
	}
	
	public List<Aranzman> getAranzmans() {
		return aranzmans;
	}

	public void setAranzmans(List<Aranzman> aranzmans) {
		this.aranzmans = aranzmans;
	}

	public Destinacija getDestinacija() {
		return destinacija;
	}

	public void setDestinacija(Destinacija destinacija) {
		this.destinacija = destinacija;
	}

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="destinacija_idDestinacija", referencedColumnName="idDestinacija"),
		})
	private Destinacija destinacija;

	public int getCenaPoOsobi() {
		return cenaPoOsobi;
	}

	public void setCenaPoOsobi(int cenaPoOsobi) {
		this.cenaPoOsobi = cenaPoOsobi;
	}

	

	public String getOpisS() {
		return opisS;
	}

	public void setOpisS(String opisS) {
		this.opisS = opisS;
	}

	public String getNazivS() {
		return nazivS;
	}

	public void setNazivS(String nazivS) {
		this.nazivS = nazivS;
	}

	public int getIdSmestaj() {
		return idSmestaj;
	}

	public void setIdSmestaj(int idSmestaj) {
		this.idSmestaj = idSmestaj;
	}
	

}
