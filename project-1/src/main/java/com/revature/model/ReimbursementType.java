package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ers_reimbursement_type")
public class ReimbursementType {
	@Id
	@Column(name="rt_id")
	private int id;
	@Column(name="rt_type")
	private String type;

	public ReimbursementType(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}

	public ReimbursementType() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
