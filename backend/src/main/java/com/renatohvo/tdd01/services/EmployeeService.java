package com.renatohvo.tdd01.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.renatohvo.tdd01.dto.EmployeeDTO;
import com.renatohvo.tdd01.entities.Employee;
import com.renatohvo.tdd01.repositories.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repository;
	
	@Transactional(readOnly = true)
	public Page<EmployeeDTO> findAllPaged(Pageable pageable){
		Page<Employee> list = repository.findAll(pageable);
		return list.map(entity -> new EmployeeDTO(entity));
	}
}
