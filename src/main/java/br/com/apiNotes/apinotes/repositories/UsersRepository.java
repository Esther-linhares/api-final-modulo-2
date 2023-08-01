package br.com.apiNotes.apinotes.repositories;

import br.com.apiNotes.apinotes.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String email);
    User getByEmail(String email);
}