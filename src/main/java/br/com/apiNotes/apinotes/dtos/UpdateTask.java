package br.com.apiNotes.apinotes.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateTask(@NotBlank @NotNull String title, String description, Boolean archive) {
        public UpdateTask(@NotBlank @NotNull String title, String description, Boolean archive) {
                this.title = title;
                this.description = description;
                this.archive = archive;
        }

        public @NotBlank @NotNull String title() {
                return this.title;
        }

        public String description() {
                return this.description;
        }

        public Boolean archive() {
                return this.archive;
        }
}
