package br.com.unifecaf.service.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import br.com.unifecaf.service.dto.DadosCachorro;
import br.com.unifecaf.service.dto.DadosDetalhamentoCachorro;
import br.com.unifecaf.service.enumerates.CastradoENUM;
import br.com.unifecaf.service.enumerates.PorteENUM;
import br.com.unifecaf.service.enumerates.SexoENUM;
import br.com.unifecaf.service.repository.CachorroRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CachorroControllerTest {
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Autowired
    private CachorroRepository cachorroRepository;
    
    private Long idCachorroExistente;
    private Long idCachorroInexistente = 9999L;
    
    @BeforeEach
    void setUp() {
        cachorroRepository.deleteAll();
        
        DadosCachorro dadosCachorro = new DadosCachorro(
            "Rex",
            3, 
            3.10, 
            SexoENUM.MACHO, 
            "Golden Retriever", 
            PorteENUM.GRANDE, 
            CastradoENUM.SIM, 
            "https://exemplo.com/imagem.jpg", 
            "Cachorro tranquilo"
        );
        
        ResponseEntity<DadosDetalhamentoCachorro> response = restTemplate.postForEntity(
            "/cachorro/cadastrar",
            dadosCachorro, 
            DadosDetalhamentoCachorro.class
        );
        
        idCachorroExistente = response.getBody().id();
    }
    
    @Test
    @DisplayName("Deve cadastrar um cachorro com sucesso")
    void criarCachorro() {
        DadosCachorro dados = new DadosCachorro(
            "Rex",
            3, 
            3.10, 
            SexoENUM.MACHO, 
            "Golden Retriever", 
            PorteENUM.GRANDE, 
            CastradoENUM.SIM, 
            "https://exemplo.com/imagem.jpg",
            "Cachorro tranquilo"
        );
        
        try {
            ResponseEntity<DadosDetalhamentoCachorro> response = restTemplate.postForEntity(
                "/cachorro/cadastrar", dados, DadosDetalhamentoCachorro.class);
            
            assertEquals(HttpStatus.CREATED, response.getStatusCode());
            assertNotNull(response.getBody().id());
        } catch (HttpClientErrorException e) {
            fail("Falha ao cadastrar: " + e.getResponseBodyAsString());
        }
    }

    @Test
    @DisplayName("Deve retornar um cachorro existente por ID")
    void buscarPorId_comIdExistente_deveRetornarCachorro() {
        ResponseEntity<DadosDetalhamentoCachorro> responseBusca = restTemplate.getForEntity(
            "/cachorro/buscar/" + idCachorroExistente,
            DadosDetalhamentoCachorro.class
        );
        
        assertEquals(HttpStatus.OK, responseBusca.getStatusCode());
        assertNotNull(responseBusca.getBody());
        assertEquals(idCachorroExistente, responseBusca.getBody().id());
        assertEquals("Rex", responseBusca.getBody().nome());
    }

    @Test
    @DisplayName("Deve deletar um cachorro existente com sucesso")
    void deletarCachorroExistente() {
        ResponseEntity<DadosDetalhamentoCachorro> response = restTemplate.exchange(
            "/cachorro/deletar/{id}",
            HttpMethod.DELETE,
            null,
            DadosDetalhamentoCachorro.class,
            idCachorroExistente
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(idCachorroExistente, response.getBody().id());
        
        assertFalse(cachorroRepository.existsById(idCachorroExistente));
    }

    @Test
    @DisplayName("Deve retornar o objeto correto após exclusão")
    void verificaObjetoRetornadoAposExclusao() {
        ResponseEntity<DadosDetalhamentoCachorro> response = restTemplate.exchange(
            "/cachorro/deletar/{id}",
            HttpMethod.DELETE,
            null,
            DadosDetalhamentoCachorro.class,
            idCachorroExistente
        );

        DadosDetalhamentoCachorro dadosExcluidos = response.getBody();
        assertNotNull(dadosExcluidos);
        assertEquals("Rex", dadosExcluidos.nome());
        assertEquals(3, dadosExcluidos.idade());
        assertEquals(SexoENUM.MACHO, dadosExcluidos.sexo());
        assertEquals("Golden Retriever", dadosExcluidos.raca());
    }
}