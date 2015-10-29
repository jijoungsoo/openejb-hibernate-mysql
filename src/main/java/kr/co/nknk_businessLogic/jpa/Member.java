package kr.co.nknk_businessLogic.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name="tb_member")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long uid;

	@Column
	private String email;
	@Column
	private String password;
	@Column
	private String name;

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Column(name = "reg_date")
	private Date regDate;

	@PrePersist
	public void prePersist() {
		regDate = new Date();
	}
	public Member(){
		
	}

	public Member(String email, String password, String name) {
		this.email = email;
		this.password = password;
		this.name = name;
	}

}
