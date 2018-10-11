package com.revature.model;

public class Reimbursement {
	private int id;
	private int amount;
	private String description;
	private byte[] blob;
	private String submit_ts;
	private String resolved_ts;
	private int id_author;
	private int id_resolver;
	private int type;
	private int status;

	public Reimbursement(int id, int amount, String description, byte[] blob, String submit_ts, String resolved_ts,
			int id_author, int id_resolver, int type, int status) {
		super();
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.blob = blob;
		this.submit_ts = submit_ts;
		this.resolved_ts = resolved_ts;
		this.id_author = id_author;
		this.id_resolver = id_resolver;
		this.type = type;
		this.status = status;
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getBlob() {
		return blob;
	}

	public void setBlob(byte[] blob) {
		this.blob = blob;
	}

	public String getSubmit_ts() {
		return submit_ts;
	}

	public void setSubmit_ts(String submit_ts) {
		this.submit_ts = submit_ts;
	}

	public String getResolved_ts() {
		return resolved_ts;
	}

	public void setResolved_ts(String resolved_ts) {
		this.resolved_ts = resolved_ts;
	}

	public int getId_author() {
		return id_author;
	}

	public void setId_author(int id_author) {
		this.id_author = id_author;
	}

	public int getId_resolver() {
		return id_resolver;
	}

	public void setId_resolver(int id_resolver) {
		this.id_resolver = id_resolver;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
