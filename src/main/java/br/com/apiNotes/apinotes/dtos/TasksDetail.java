package br.com.apiNotes.apinotes.dtos;

import br.com.apiNotes.apinotes.models.Task;
import lombok.Getter;

import java.util.Collection;
import java.util.UUID;

    public record TasksDetail(UUID id, String title, String description, Boolean archived) {
        public TasksDetail(Task task) {
            this(task.getId(), task.getTitle(), task.getDescription(), task.getArchive());
        }

        public TasksDetail(UUID id, String title, String description, Boolean archived) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.archived = archived;
        }

        public UUID id() {
            return this.id;
        }

        public String title() {
            return this.title;
        }

        public String description() {
            return this.description;
        }

        public Boolean archived() {
            return this.archived;
        }
    }