package com.example.Backend_dev_assgn.service;

import java.util.List;

import org.springframework.stereotype.Service;

//import com.example.Backend_dev_assgn.dto.TaskRequest;
import com.example.Backend_dev_assgn.entity.Task;
import com.example.Backend_dev_assgn.repository.TaskRepository;
//import com.sun.tools.javac.util.List;

@Service
public class TaskService {
	
	 private final TaskRepository taskRepository;

	    public TaskService(TaskRepository taskRepository) {
	        this.taskRepository = taskRepository;
	    }
	    
	    public Task createTask(Task task) {
	        return taskRepository.save(task);
	    }

	    public List<Task> getAllTasks() {
	        return taskRepository.findAll();
	    }

	    public Task updateTask(Long id, Task updatedTask) {

	        Task task = taskRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Task not found"));

	        task.setTitle(updatedTask.getTitle());
	        task.setDescription(updatedTask.getDescription());
	        task.setStatus(updatedTask.getStatus());

	        return taskRepository.save(task);
	    }

	    public void deleteTask(Long id) {

	        Task task = taskRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Task not found"));

	        taskRepository.delete(task);
	    }
	
	

}
