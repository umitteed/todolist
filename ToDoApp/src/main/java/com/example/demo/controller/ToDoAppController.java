package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.ToDoEntity;
import com.example.demo.service.ToDoAppService;

@Controller
public class ToDoAppController {

    @Autowired
    private ToDoAppService todoService;

    @GetMapping("/")
    public String redirectToTodos() {
        return "redirect:/todos";  
    }

    @GetMapping("/todos")
    public String showList(Model model) {
        List<ToDoEntity> todoList = todoService.getAllToDos();
        model.addAttribute("listTodos", todoList);
        return "ToDoApp/list";
    }

    @PostMapping("/todos/saveTodo")
    public String saveTodo(@RequestParam("taskName") String taskName) {
        ToDoEntity todo = new ToDoEntity();
        todo.setTaskName(taskName);
        todo.setStatus(false);
        todoService.saveToDo(todo);
        return "redirect:/todos";
    }

    @PostMapping("/todos/updateStatus")
    public String updateStatus(@RequestParam("todoId") Long todoId, @RequestParam("status") boolean status) {
        System.out.println("Received todoId: " + todoId + ", status: " + status);
        todoService.updateToDoStatus(todoId, status);
        return "redirect:/todos";
    }

    
    @PostMapping("/todos/deleteTodo")
    public String deleteTodo(@RequestParam("todoId") Long todoId) {
        todoService.deleteToDoById(todoId);
        return "redirect:/todos";
    }


}
