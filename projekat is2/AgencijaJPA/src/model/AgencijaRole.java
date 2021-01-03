package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.HashSet;

import java.util.Set;


/**
 * The persistent class for the agencija_role database table.
 * 
 */
@Entity
@Table(name="agencija_role")
@NamedQuery(name="AgencijaRole.findAll", query="SELECT a FROM AgencijaRole a")
public class AgencijaRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRole;

	private String name;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="agencija_user_role", joinColumns = @JoinColumn(name = "idRole",referencedColumnName = "idRole"),inverseJoinColumns = @JoinColumn(name = "idUser"))
	private Set<AgencijaUser> users =new HashSet<>();

	public AgencijaRole() {
	}

	public int getIdRole() {
		return this.idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<AgencijaUser> getUsers() {
		return users;
	}

	public void setUsers(Set<AgencijaUser> users) {
		this.users = users;
	}
	public void addUser(AgencijaUser u) {
		this.users.add(u);
	}

}