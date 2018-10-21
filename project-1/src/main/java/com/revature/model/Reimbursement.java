package com.revature.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ers_reimbursements")
public class Reimbursement {
	@Id
	@Column(name = "r_id")
	private int id;
	@Column(name = "r_amount")
	private double amount;
	@Column(name = "r_description")
	private String description;
	@Column(name = "r_receipt")
	private byte[] recipt;
	@Column(name = "r_submitted")
	private Timestamp submitted_ts;
	@Column(name = "r_resolved")
	private Timestamp resolved_ts;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "u_id_author", referencedColumnName = "u_id")
	private ErsUser author;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "u_id_resolver", referencedColumnName = "u_id")
	private ErsUser resolver;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "rt_id")
	private ReimbursementType reimbursementType;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "rs_id")
	private ReimbursementStatus reimbursementStatus;

	public Reimbursement(int id, double amount, String description, byte[] recipt, Timestamp submitted_ts,
			Timestamp resolved_ts, ErsUser author, ErsUser resolver, ReimbursementType reimbursementType,
			ReimbursementStatus reimbursementStatus) {
		super();
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.recipt = recipt;
		this.submitted_ts = submitted_ts;
		this.resolved_ts = resolved_ts;
		this.author = author;
		this.resolver = resolver;
		this.reimbursementType = reimbursementType;
		this.reimbursementStatus = reimbursementStatus;
	}

	public Reimbursement() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getRecipt() {
		return recipt;
	}

	public void setRecipt(byte[] recipt) {
		this.recipt = recipt;
	}

	public Timestamp getSubmitted_ts() {
		return submitted_ts;
	}

	public void setSubmitted_ts(Timestamp submitted_ts) {
		this.submitted_ts = submitted_ts;
	}

	public Timestamp getResolved_ts() {
		return resolved_ts;
	}

	public void setResolved_ts(Timestamp resolved_ts) {
		this.resolved_ts = resolved_ts;
	}

	public ErsUser getAuthor() {
		return author;
	}

	public void setAuthor(ErsUser author) {
		this.author = author;
	}

	public ErsUser getResolver() {
		return resolver;
	}

	public void setResolver(ErsUser resolver) {
		this.resolver = resolver;
	}

	public ReimbursementType getReimbursementType() {
		return reimbursementType;
	}

	public void setReimbursementType(ReimbursementType reimbursementType) {
		this.reimbursementType = reimbursementType;
	}

	public ReimbursementStatus getReimbursementStatus() {
		return reimbursementStatus;
	}

	public void setReimbursementStatus(ReimbursementStatus reimbursementStatus) {
		this.reimbursementStatus = reimbursementStatus;
	}

}
