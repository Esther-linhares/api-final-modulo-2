package br.com.apiNotes.apinotes.dtos;

public record ErrorData(String message) {
    public ErrorData(String message) {
        this.message = message;
    }

    public String message() {
        return this.message;
    }
}

