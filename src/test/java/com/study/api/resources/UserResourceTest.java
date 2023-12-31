package com.study.api.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
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
    void whenCreateThenReturnCreated() {
        when(service.createUser(any())).thenReturn(user);

        ResponseEntity<UserDTO> response = resource.create(userDTO);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders());

    }

    @Test
    void whenDeleteThenReturnSuccess() {
        doNothing().when(service).deleteUser(anyInt());

        ResponseEntity<UserDTO> response = resource.delete(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        verify(service, times(1)).deleteUser(anyInt());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

    }

    @Test
    void whenFindAllThenReturnAListOfUserDTO() {
        when(service.findAll()).thenReturn(List.of(user));

        ResponseEntity<List<UserDTO>> response = resource.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());

        if (response.getBody() != null) {
            assertEquals(ArrayList.class, response.getBody().getClass());
            assertEquals(UserDTO.class, response.getBody().get(INDEX).getClass());
            assertEquals(ID, response.getBody().get(INDEX).getId());
            assertEquals(NAME, response.getBody().get(INDEX).getName());
            assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
            assertEquals(PASSWORD, response.getBody().get(INDEX).getPassword());
        }
    }

    @Test
    void whenFindByIdReturnSuccess() {
        when(service.findById(anyInt())).thenReturn(user);

        ResponseEntity<UserDTO> response = resource.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());

        if (response.getBody() != null) {
            assertEquals(UserDTO.class, response.getBody().getClass());
            assertEquals(ID, response.getBody().getId());
            assertEquals(NAME, response.getBody().getName());
            assertEquals(EMAIL, response.getBody().getEmail());
            assertEquals(PASSWORD, response.getBody().getPassword());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(service.updateUser(userDTO)).thenReturn(user);

        ResponseEntity<UserDTO> response = resource.update(ID, userDTO);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        
        if (response.getBody() != null) {
            assertEquals(ID, response.getBody().getId());
            assertEquals(NAME, response.getBody().getName());
            assertEquals(EMAIL, response.getBody().getEmail());
        }
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
    }
}
