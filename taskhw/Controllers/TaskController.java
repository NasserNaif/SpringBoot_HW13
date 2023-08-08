package com.example.taskhw.Controllers;

import com.example.taskhw.ApiResponse.ApiRespone;
import com.example.taskhw.Models.TaskModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    ArrayList<TaskModel> tasks = new ArrayList<>();

    int id = 1;


    @GetMapping("/get")
    public ArrayList<TaskModel> getTasks() {
        return tasks;
    }


    @PostMapping("/add")
    public ApiRespone addTask(@RequestBody @NotNull TaskModel task) {
        task.setId("" + id);
        tasks.add(task);
        id++;

        return new ApiRespone("task adedd", 201);
    }

    @PutMapping("/update/{id}")
    public ApiRespone updateTask(@RequestBody TaskModel task, @PathVariable String id) {

        for (TaskModel t : tasks) {
            if (t.getId().equals(id)) {
                t.setStatus(task.getStatus());
                t.setTitle(task.getTitle());
                t.setDescription(task.getDescription());
                return new ApiRespone("task updated", 200);
            }
        }

        return new ApiRespone("sorry! there's no item with this ID " + id, 400);
    }

    @DeleteMapping("/delete/{id}")
    public ApiRespone deleteTask(@PathVariable String id) {
        for (TaskModel t : tasks) {
            if (t.getId().equals(id)) {
                tasks.remove(t);
                return new ApiRespone("task deleted", 400);

            }
        }

        return new ApiRespone("sorry! there's no item with this ID " + id, 400);
    }


    @PutMapping("/change/{id}")
    public ApiRespone checkStatus(@PathVariable String id) {
        for (TaskModel a : tasks) {
            if (a.getId().equals(id)) {
                a.setStatus(a.getStatus().equals("done") ? "not done" : "done");
                return new ApiRespone("status changed", 201);
            }
        }
        return new ApiRespone("sorry! there's no item with this ID " + id, 400);
    }


    @GetMapping("/get/{title}")
    public TaskModel getSpecificTask(@PathVariable String title) {
        for (TaskModel a : tasks) {
            if (a.getTitle().equals(title)) return a;
        }
        return new TaskModel(" ", "error", "there's no task with this title " + title, "error");
    }
}
