package com.example.usuarioapi.service;

import com.example.usuarioapi.model.Usuario;
import com.example.usuarioapi.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService service;

    @Test
    void deveSalvarUsuarioComSucesso() {
        Usuario usuario = new Usuario();
        usuario.setNome("João");
        usuario.setEmail("joao@email.com");

        when(repository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario salvo = service.salvar(usuario);

        assertEquals("João", salvo.getNome());
        verify(repository).save(usuario);
    }

    @Test
    void deveBuscarUsuarioPorId() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(usuario));

        Optional<Usuario> encontrado = service.buscarPorId(1L);

        assertTrue(encontrado.isPresent());
    }
}