package be.flexteam.flex_imdb.domain.service;

import be.flexteam.flex_imdb.domain.port.input.ActorServicePort;
import be.flexteam.flex_imdb.domain.port.output.ActorPersistencePort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class ActorServiceImplTest {

    @Autowired
    private ActorServicePort tested;

    @MockBean
    private ActorPersistencePort mockActorPersistencePort;

    @Test
    void getAll() {
        when(mockActorPersistencePort.getAll()).thenReturn(null);

        assertThat(tested.getAll()).isNotNull();
    }

    @Test
    void get() {
    }
}