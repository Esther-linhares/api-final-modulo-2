package br.com.apiNotes.apinotes.models;

import br.com.apiNotes.apinotes.dtos.AddTask;
import br.com.apiNotes.apinotes.dtos.UpdateTask;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String description;
    private Boolean archive;
    private String useremail;


    public Task(AddTask newTask, String useremail) {
        this.title = newTask.title();
        this.description = newTask.description();
        this.archive = false;
        this.useremail = newTask.useremail();
    }

    public void UpdateTask(UpdateTask taskUpdated) {
            if(taskUpdated.title() != null) {
                title = taskUpdated.title();
            }
            if(taskUpdated.description() != null) {
                description = taskUpdated.description();
            }
    }

}

