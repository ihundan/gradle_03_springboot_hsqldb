package ihundan.wukonglin.hsqldb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="tb_user")
public class User {
	public Long objid;
	public String username;
	public String password;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getObjid() {
		return objid;
	}
	public void setObjid(Long objid) {
		this.objid = objid;
	}
	@Column(unique = true, nullable = false)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(nullable = false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public User() {
		super();
	}
	public User(Long objid, String username, String password) {
		super();
		this.objid = objid;
		this.username = username;
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [objid=" + objid + ", username=" + username
				+ ", password=" + password + "]";
	}
}
