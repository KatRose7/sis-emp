package com.example.sistema.empresarial.sistema_empresarial.security;

import com.example.sistema.empresarial.sistema_empresarial.model.UsuarioEntidad;
import com.example.sistema.empresarial.sistema_empresarial.repository.UsuarioEntidadRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UsuarioEntidadRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDetailsServiceImpl(UsuarioEntidadRepository usuarioRepository,
                                  PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UsuarioEntidad usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(usuario.getRol()) // ajusta según tu DB, puede ser ROLE_USER, ROLE_ADMIN
                .build();
    }
}
