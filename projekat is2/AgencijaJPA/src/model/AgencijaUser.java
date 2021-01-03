package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the agencija_user database table.
 * 
 */
@Entity
@Table(name="agencija_user")
@NamedQuery(name="AgencijaUser.findAll", query="SELECT a FROM AgencijaUser a")
public class AgencijaUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUser;

	private String name;

	private String password;

	private String phone;

	private String username;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy="users")
	private Set<AgencijaRole> roles =new HashSet<>();


	public Set<AgencijaRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<AgencijaRole> roles) {
		this.roles = roles;
	}
	
	public void addRole(AgencijaRole r) {
		this.roles.add(r);
	}


	//bi-directional many-to-one association to Rezervacija
	@OneToMany(mappedBy="agencijaUser")
	private List<Rezervacija> rezervacijas;

	public AgencijaUser() {
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Rezervacija> getRezervacijas() {
		return this.rezervacijas;
	}

	public void setRezervacijas(List<Rezervacija> rezervacijas) {
		this.rezervacijas = rezervacijas;
	}

	public Rezervacija addRezervacija(Rezervacija rezervacija) {
		getRezervacijas().add(rezervacija);
		rezervacija.setAgencijaUser(this);

		return rezervacija;
	}

	public Rezervacija removeRezervacija(Rezervacija rezervacija) {
		getRezervacijas().remove(rezervacija);
		rezervacija.setAgencijaUser(null);

		return rezervacija;
	}

}