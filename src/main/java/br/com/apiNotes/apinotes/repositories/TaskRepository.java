package br.com.apiNotes.apinotes.repositories;

import br.com.apiNotes.apinotes.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

}