package com.renatohvo.tdd01.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.renatohvo.tdd01.dto.DepartmentDTO;
import com.renatohvo.tdd01.entities.Department;
import com.renatohvo.tdd01.repositories.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository repository;
	
	@Transactional(readOnly = true)
	public List<DepartmentDTO> findAll(){
		List<Department> list = repository.findAll(Sort.by("name"));
		return list.stream().map(x -> new DepartmentDTO(x)).collect(Collectors.toList());
	}
}
