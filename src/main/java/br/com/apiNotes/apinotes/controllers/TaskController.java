package br.com.apiNotes.apinotes.controllers;

import br.com.apiNotes.apinotes.dtos.*;
import br.com.apiNotes.apinotes.models.Task;
import br.com.apiNotes.apinotes.models.User;
import br.com.apiNotes.apinotes.repositories.TaskRepository;
import br.com.apiNotes.apinotes.repositories.UsersRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UsersRepository usersRepostitory;
    @PostMapping("/{email}")
    @Transactional
    public ResponseEntity addTask(@PathVariable String email, @RequestBody @Valid AddTask newTask){
        var userEmail = usersRepostitory.findById(newTask.useremail());

        if(userEmail.isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorData("Usuário não localizado."));
        }

        var user = userEmail.get();

        var task = new Task(newTask, user.getEmail());
        taskRepository.save(task);
        return ResponseEntity.ok().body(newTask);

    }


    @GetMapping("/{email}")
    public ResponseEntity getTasks(@PathVariable String email, @RequestParam(required = false) String title, @RequestParam(required = false) boolean archive) {
        User checkUser = this.usersRepostitory.getByEmail(email);
        List<Task> tasks = checkUser.getTasks();
        if (tasks == null) {
            return ResponseEntity.badRequest().body(new ErrorData("Nenhum recado adicionado."));
        } else if (title != null) {
            tasks = tasks.stream().filter((t) -> {
                return t.getTitle().contains(title);
            }).toList();
            return ResponseEntity.ok().body(tasks);
        } else if (archive) {
            tasks = tasks.stream().filter((a) -> {
                return a.getArchive().equals(true);
            }).toList();
            return ResponseEntity.ok().body(tasks);
        } else {
            return ResponseEntity.ok().body(tasks.stream().map(TasksDetail::new).toList());
        }
    }


    @DeleteMapping({"/{email}/{idTask}"})
    public ResponseEntity deleteTask(@PathVariable String email, @PathVariable UUID idTask) {
        User user = this.usersRepostitory.getByEmail(email);
        Optional<Task> task = this.taskRepository.findById(idTask);

        if (task == null) {
            return ResponseEntity.badRequest().body(new ErrorData("Recado não encontrado!"));
        } else {
            this.taskRepository.delete((Task)task.get());
            return ResponseEntity.ok().body(user.getTasks());
        }
    }

    @PutMapping("/{email}/{idTask}")
    public ResponseEntity updateTask(@PathVariable String email, @PathVariable UUID idTask, @RequestBody UpdateTask taskUpdated) {
        Optional<User> optionalUser = this.usersRepostitory.findById(email);
        User user = (User)optionalUser.get();
        Optional<Task> taskOptional = user.getTasks().stream().filter((t) -> {
            return t.getId().equals(idTask);
        }).findAny();
        Task task = (Task)taskOptional.get();
        task.UpdateTask(taskUpdated);
        this.taskRepository.save(task);
        return ResponseEntity.ok().body(user.getTasks());
    }

    @PutMapping("/{email}/{idTask}/archive")
    public ResponseEntity archiveTask(@PathVariable String email, @PathVariable UUID idTask) {
        try {
            Optional<Task> findtask = this.taskRepository.findById(idTask);
            Task task = (Task)findtask.get();
            Boolean archive = task.getArchive();
            task.setArchive(!archive);
            this.taskRepository.save(task);
            return ResponseEntity.ok().body(task.getArchive());
        } catch (Exception var6) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorData("Task não encontrada"));
        }
    }
}


