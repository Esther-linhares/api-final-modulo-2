package br.com.apiNotes.apinotes.controllers;

import br.com.apiNotes.apinotes.dtos.CreateUser;
import br.com.apiNotes.apinotes.dtos.ErrorData;
import br.com.apiNotes.apinotes.dtos.UserDetail;
import br.com.apiNotes.apinotes.models.User;
import br.com.apiNotes.apinotes.repositories.UsersRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UserController {
    @Autowired
    public UsersRepository usersRepository;

    @GetMapping
    public ResponseEntity getAllUsers(){
        List<User> userList = this.usersRepository.findAll();
        return ResponseEntity.ok().body(userList);
    }
    @PostMapping
    @Transactional
    public ResponseEntity createUser(@RequestBody CreateUser data) {
        if (this.usersRepository.existsByEmail(data.email())) {
            return ResponseEntity.badRequest().body(new ErrorData("Email já cadastrado"));
        } else {
            User user = new User(data.email(), data.password());
            this.usersRepository.save(user);
            return ResponseEntity.ok().body("Conta criada com sucesso!");
        }
    }

    @GetMapping("/{email}")
    public  ResponseEntity getUser(@PathVariable String email){
        var user = usersRepository.getByEmail(email);

        if(user == null){
            return ResponseEntity.badRequest().body(new ErrorData("User não localizado"));
        }
        var userDetails = new UserDetail(user);

        return  ResponseEntity.ok(userDetails);
    }
    @GetMapping("/login/{email}/{password}")
    public ResponseEntity login(@PathVariable @Valid String email, @PathVariable @Valid String password){
        try {
            var user = usersRepository.getByEmail(email);
            if(user.getEmail().equals(email) && user.getPassword().equals(password)){
                return ResponseEntity.ok().body(user);
            }
            return ResponseEntity.badRequest().body(new ErrorData("E-mail ou senha inválidos."));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorData("Login inválido."));
        }
    }

}
