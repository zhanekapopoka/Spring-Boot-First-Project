package com.example.springBootfirstapp;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
@RestController
public class TasksController {
    private List<Task> tasks = new ArrayList<>();

    @PostMapping("/tasks")
        public String addTask(@RequestBody Task task){
        tasks.add(task);
        return "Задача успешно добавлена!";
    }
    @GetMapping("/tasks")
    public List<Task> getTasks(
            @RequestParam(required = false) Boolean completed,
            @RequestParam(required = false, defaultValue = "asc") String sort) {

        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (completed == null || t.isCompleted() == completed) {
                result.add(t);
            }
        }
        if ("desc".equalsIgnoreCase(sort)) {
            result.sort((t1, t2) -> t2.getDateTime().compareTo(t1.getDateTime()));
        } else {
            result.sort(Comparator.comparing(Task::getDateTime));
        }
        return result;
    }
}
