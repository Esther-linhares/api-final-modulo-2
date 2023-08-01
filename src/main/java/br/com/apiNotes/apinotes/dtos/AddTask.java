package br.com.apiNotes.apinotes.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddTask(@NotBlank @NotNull String title, String description, @NotNull String useremail) {
        public AddTask(@NotBlank @NotNull String title, String description, @NotNull String useremail) {
                this.title = title;
                this.description = description;
                this.useremail = useremail;
        }

        public @NotBlank @NotNull String title() {
                return this.title;
        }

        public String description() {
                return this.description;
        }

        public @NotNull String useremail() {
                return this.useremail;
        }
}

