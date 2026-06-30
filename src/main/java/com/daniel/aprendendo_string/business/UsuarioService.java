package com.daniel.aprendendo_string.business;


import com.daniel.aprendendo_string.infrastructure.Repository.UsuarioRepository;
import com.daniel.aprendendo_string.infrastructure.entity.Usuario;
import com.daniel.aprendendo_string.infrastructure.exceptions.ConflictException;
import com.daniel.aprendendo_string.infrastructure.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UsuarioService {


    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;


    public Usuario salvaUsuario(Usuario usuario) {
        try{
            emailExiste(usuario.getEmail());
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            return repository.save(usuario);
        }catch (ConflictException e){
            throw new ConflictException("Email ja cadastrado " + e.getCause());
        }

    }
    public void emailExiste(String email) {
        try{
           boolean existe =  verificaEmailExistente(email);

           if(existe) {
               throw new ConflictException("Email ja cadastrado " +email);
           }
        }catch (ConflictException e) {
            throw new ConflictException("Email ja cadastrado " + e.getCause());
        }
     }

    public boolean verificaEmailExistente(String email) {
        return repository.existsByEmail(email);
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email não encontrado " +email));
    }

    public void deletaUsuarioPorEmail(String email) {
        repository.deleteByEmail(email);
    }







}
