package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the destinacija database table.
 * 
 */
@Entity
@NamedQuery(name="Destinacija.findAll", query="SELECT d FROM Destinacija d")
public class Destinacija implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idDestinacija;

	private String nazivD;

	//bi-directional many-to-one association to Smestaj
	@OneToMany(mappedBy="destinacija")
	private List<Smestaj> smestajs;

	public Destinacija() {
	}

	public int getIdDestinacija() {
		return this.idDestinacija;
	}

	public void setIdDestinacija(int idDestinacija) {
		this.idDestinacija = idDestinacija;
	}

	public String getNazivD() {
		return this.nazivD;
	}

	public void setNazivD(String nazivD) {
		this.nazivD = nazivD;
	}

	public List<Smestaj> getSmestajs() {
		return this.smestajs;
	}

	public void setSmestajs(List<Smestaj> smestajs) {
		this.smestajs = smestajs;
	}

	public Smestaj addSmestaj(Smestaj smestaj) {
		getSmestajs().add(smestaj);
		smestaj.setDestinacija(this);

		return smestaj;
	}

	public Smestaj removeSmestaj(Smestaj smestaj) {
		getSmestajs().remove(smestaj);
		smestaj.setDestinacija(null);

		return smestaj;
	}

}