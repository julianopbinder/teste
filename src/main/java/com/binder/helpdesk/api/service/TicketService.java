package com.binder.helpdesk.api.service;


import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import com.binder.helpdesk.api.entity.ChangeStatus;
import com.binder.helpdesk.api.entity.Ticket;

//classe vai ser gerenciada pelo spring
@Component
public interface TicketService {

	//cria ou altera um ticket
	Ticket createOrUpdate(Ticket ticket);
	
	//busca ticket por Id
	Ticket findById(String id);
	
	//deleta Id
	void  delete(String id);
	
	//paginação de ticket -pesquisa geral por todos tickets
	Page<Ticket> listTicket(int page, int count);
	
	//guarda as alterações salvas pelos tickets
	ChangeStatus createChangeStatus(ChangeStatus changeStatus);
	
	//retorna lista de alterações feitas por determinado ticket
	Iterable<ChangeStatus> listChangeStatus(String ticketId);
	
	//list os tickets somente do próprio usuário
	Page<Ticket> findByCurrentUser(int page, int count, String userId);
	
	//pesquisa por parametros o tickets
	Page<Ticket> findByParameters(int page, int count, String title, String status , String priority);
	
	//Pesquisa ticket por usuário logado.
	Page<Ticket> findByParametersAndCurrentUser(int page, int count,String title,String status,String priority,String userId);
	
	//Pesquisa por número de ticket
	Page<Ticket> findByNumber(int page, int count ,int number);
	
	//resumo de todos os tickets
	Iterable<Ticket> findAll();
	
	//pesquisa por parametros de ticket - quando técnico estiver logado
	Page<Ticket> findByParametersAndAssignedUser(int page, int count, String title, String status, String priority,String assignedUserId);
			     
	
}
