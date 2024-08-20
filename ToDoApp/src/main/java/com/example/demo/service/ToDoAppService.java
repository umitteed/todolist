package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ToDoEntity;
import com.example.demo.repository.ToDoRepository;

@Service
public class ToDoAppService {

    @Autowired
    private ToDoRepository toDoRepository;

    public List<ToDoEntity> getAllToDos() {
        return toDoRepository.findAll();
    }

    public void saveToDo(ToDoEntity toDo) {
        toDoRepository.save(toDo);
    }

    public ToDoEntity getToDoById(Long id) {
        return toDoRepository.findById(id).orElse(null);
    }

    public void updateToDoStatus(Long id, boolean status) {
        ToDoEntity todo = getToDoById(id);
        if (todo != null) {
            todo.setStatus(status);
            saveToDo(todo);
        }
    }
    
    public void deleteToDoById(Long id) {
        toDoRepository.deleteById(id);
    }

}
