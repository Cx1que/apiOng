package br.com.unifecaf.service.controller;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    
    
    void iniciarCachorro() {
        DadosCachorro dadosCachorro = new DadosCachorro(
            "Bob", 
            2, 
            2.20, 
            SexoENUM.MACHO, 
            "Labrador", 
            PorteENUM.MEDIO, 
            CastradoENUM.NAO, 
            "imagembonita", 
            "Cachorro brincalhão"
        );
        ResponseEntity<DadosDetalhamentoCachorro> response = restTemplate.postForEntity(
            "/cachorro/cadastrar", // Corrigido o endpoint
            dadosCachorro, 
            DadosDetalhamentoCachorro.class
        );
        
        if (response.getStatusCode() != HttpStatus.CREATED) {
            System.err.println("Falha ao iniciar cachorro: " + response.getBody());
        }
    }

    @Test
    @DisplayName("Deve cadastrar um cachorro com sucesso")
    void criarCachorro() {
        DadosCachorro dadosCachorro = new DadosCachorro(
            "Rex", // Nome diferente do inicializado para evitar conflito
            	3, 
            3.10, 
            SexoENUM.MACHO, 
            "Golden Retriever", 
            PorteENUM.GRANDE, 
            CastradoENUM.SIM, 
            "imagembonita", 
            "Cachorro tranquilo"
        );
        
        ResponseEntity<DadosDetalhamentoCachorro> response = restTemplate.postForEntity(
            "/cachorro/cadastrar",
            dadosCachorro, 
            DadosDetalhamentoCachorro.class
        );
        
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().id());
    }
    
    @Test
    @DisplayName("Deve retornar um cachorro existente por ID")
    void buscarPorId_comIdExistente_deveRetornarCachorro() {
        // Primeiro cadastra um cachorro para ter um ID válido
        DadosCachorro dadosCachorro = new DadosCachorro(
            "Rex", 
            3, 
            3.10, 
            SexoENUM.MACHO, 
            "Golden Retriever", 
            PorteENUM.GRANDE, 
            CastradoENUM.SIM, 
            null, 
            "Cachorro tranquilo"
        );
        
        ResponseEntity<DadosDetalhamentoCachorro> responseCadastro = restTemplate.postForEntity(
            "/cachorro/cadastrar",
            dadosCachorro, 
            DadosDetalhamentoCachorro.class
        );
        
        Long idCachorro = responseCadastro.getBody().id();
        
        // Agora testa a busca
        ResponseEntity<DadosDetalhamentoCachorro> responseBusca = restTemplate.getForEntity(
            "/cachorro/buscar/" + idCachorro,
            DadosDetalhamentoCachorro.class
        );
        
        assertEquals(HttpStatus.OK, responseBusca.getStatusCode());
        assertNotNull(responseBusca.getBody());
        assertEquals(idCachorro, responseBusca.getBody().id());
        assertEquals("Rex", responseBusca.getBody().nome());
    }

   
   
   
}