package br.com.apiNotes.apinotes.dtos;

import jakarta.validation.constraints.NotBlank;

public record RequestLogin(@NotBlank String email, @NotBlank String password) {
    public RequestLogin(@NotBlank String email, @NotBlank String password) {
        this.email = email;
        this.password = password;
    }

    public @NotBlank String email() {
        return this.email;
    }

    public @NotBlank String password() {
        return this.password;
    }
}