package com.example.Backend_dev_assgn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Backend_dev_assgn.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long>{

	
}
