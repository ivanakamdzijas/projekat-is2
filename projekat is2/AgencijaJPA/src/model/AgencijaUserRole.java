package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the agencija_user_role database table.
 * 
 */
@Entity
@Table(name="agencija_user_role")
@NamedQuery(name="AgencijaUserRole.findAll", query="SELECT a FROM AgencijaUserRole a")
public class AgencijaUserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUserRole;

	//bi-directional many-to-one association to AgencijaRole
	@ManyToOne
	@JoinColumn(name="idRole")
	private AgencijaRole agencijaRole;

	//bi-directional many-to-one association to AgencijaUser
	@ManyToOne
	@JoinColumn(name="idUser")
	private AgencijaUser agencijaUser;

	public AgencijaUserRole() {
	}

	public int getIdUserRole() {
		return this.idUserRole;
	}

	public void setIdUserRole(int idUserRole) {
		this.idUserRole = idUserRole;
	}

	public AgencijaRole getAgencijaRole() {
		return this.agencijaRole;
	}

	public void setAgencijaRole(AgencijaRole agencijaRole) {
		this.agencijaRole = agencijaRole;
	}

	public AgencijaUser getAgencijaUser() {
		return this.agencijaUser;
	}

	public void setAgencijaUser(AgencijaUser agencijaUser) {
		this.agencijaUser = agencijaUser;
	}

}