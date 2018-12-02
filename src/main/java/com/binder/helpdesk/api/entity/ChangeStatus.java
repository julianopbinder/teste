package com.binder.helpdesk.api.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.binder.helpdesk.api.enums.StatusEnum;

@Document
public class ChangeStatus {

	@Id
	private String id;

	@DBRef
	private Ticket ticket;

	@DBRef
	private User userchanged;

	private Date dateChangedStatus;

	private StatusEnum status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public User getUserchanged() {
		return userchanged;
	}

	public void setUserchanged(User userchanged) {
		this.userchanged = userchanged;
	}

	public Date getDateChangedStatus() {
		return dateChangedStatus;
	}

	public void setDateChangedStatus(Date dateChangedStatus) {
		this.dateChangedStatus = dateChangedStatus;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

}
