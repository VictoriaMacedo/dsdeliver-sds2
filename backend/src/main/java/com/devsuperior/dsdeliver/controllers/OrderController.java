package com.devsuperior.dsdeliver.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.dsdeliver.dto.OrderDTO;
import com.devsuperior.dsdeliver.services.OrderService;

@RestController
@RequestMapping(value = "/orders" )
public class OrderController {

	@Autowired
	private OrderService service;
	
	@GetMapping
	public ResponseEntity<List<OrderDTO>> findAll ()	{
		List<OrderDTO> List = service.findAll();
		return ResponseEntity.ok().body(List);
	}
	
    @PostMapping
    public ResponseEntity<OrderDTO> insert(@RequestBody OrderDTO dto) {
    	dto = service.insert(dto);
    	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
    	ResponseEntity.ok().body(dto);
		return ResponseEntity.created(uri).body(dto);
    	
    	// o certo é retornar o código 201, pq é o código quando algo é criado
    	}
}


/* nesse código eu inseri no banco de dados um novo pedido (order) por isso eu usei esse código : 
[@Transactional (readOnly = true)
 public List<OrderDTO> findAll () {
	List<Order> list = repository.findOrderWithProducts();
	return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());] .... e nesse código eu estou puxando da lista de "ProductRepository" */