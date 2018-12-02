package com.binder.helpdesk.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.binder.helpdesk.api.entity.ChangeStatus;

public interface ChangeStatusRepository extends MongoRepository<ChangeStatus, String> {

	//buscar todas alterações daquele ticket
	Iterable<ChangeStatus> findByTicketIdOrderByDateChangedStatusDesc(String ticketId);
	
}
