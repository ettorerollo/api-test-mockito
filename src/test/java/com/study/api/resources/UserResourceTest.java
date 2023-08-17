package com.study.api.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.study.api.domain.User;
import com.study.api.domain.dto.UserDTO;
import com.study.api.services.impl.UserServiceImpl;

@SpringBootTest
public class UserResourceTest {

    /**
     *
     */
    private static final int INDEX = 0;

    /**
     * VARIÁVEL ID PARA INICIAR OS OBJETOS
     */
    private static final Integer ID = 1;

    /**
     * VARIÁVEL NAME PARA INICIAR OS OBJETOS
     */
    private static final String NAME = "Teste";

    /**
     * VARIÁVEL EMAIL PARA INICIAR OS OBJETOS
     */
    private static final String EMAIL = "teste@teste.com";

    /**
     * VARIÁVEL PASSWORD PARA INICIAR OS OBJETOS
     */
    private static final String PASSWORD = "123";

    /**
     * Mensagem de usuário não encontrado
     */
    private static final String USUÁRIO_NAO_ENCONTRADO = "Usuário não encontrado.";

    /**
     * Mensagem de email não encontrado
     */
    private static final String EMAIL_NAO_ENCONTRADO = "E-mail já cadastrado no sistema.";

    private User user;
    private UserDTO userDTO;

    @InjectMocks
    private UserResource resource;

    @Mock
    private UserServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void testCreate() {

    }

    @Test
    void testDelete() {

    }

    @Test
    void testFindAll() {

    }

    @Test
    void whenFindByIdReturnSuccess() {
        when(service.findById(anyInt())).thenReturn(user);

        ResponseEntity<UserDTO> response = resource.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(PASSWORD, response.getBody().getPassword());
    }

    @Test
    void testUpdate() {

    }
    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
    }
}
