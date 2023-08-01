package br.com.apiNotes.apinotes.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUser(@NotBlank @Email String email, @NotBlank String password, @NotBlank String repassword) {
        public CreateUser(@NotBlank @Email String email, @NotBlank String password, @NotBlank String repassword) {
                this.email = email;
                this.password = password;
                this.repassword = repassword;
        }

        public @NotBlank @Email String email() {
                return this.email;
        }

        public @NotBlank String password() {
                return this.password;
        }

        public @NotBlank String repassword() {
                return this.repassword;
        }
}
