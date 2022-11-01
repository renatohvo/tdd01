package com.renatohvo.tdd01.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.renatohvo.tdd01.entities.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
