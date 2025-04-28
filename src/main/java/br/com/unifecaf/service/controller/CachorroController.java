package br.com.unifecaf.service.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.unifecaf.service.dto.DadosAtualizarCachorro;
import br.com.unifecaf.service.dto.DadosCachorro;
import br.com.unifecaf.service.dto.DadosDetalhamentoCachorro;
import br.com.unifecaf.service.entity.Cachorro;
import br.com.unifecaf.service.service.CachorroService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cachorro")
public class CachorroController {
	
	@Autowired
	private CachorroService service;
	
	
	@PostMapping("/cadastrar")
	@Transactional
	public ResponseEntity<DadosDetalhamentoCachorro> cadastrar(@RequestBody @Valid DadosCachorro dados) {
	    return ResponseEntity.status(HttpStatus.CREATED).body(service.criarCachorro(dados));
	}

	@GetMapping("/buscar")
    public ResponseEntity<Page<Cachorro>> buscar(
            @PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
        Page<Cachorro> cachorroListagem = service.buscar(paginacao);
        return ResponseEntity.ok(cachorroListagem);
    }
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Optional<DadosDetalhamentoCachorro>> buscarPorId (@PathVariable Long id) {
		return ResponseEntity.ok(service.buscarPorId(id));
	}
	
	@PatchMapping("/atualizar")
	@Transactional
	public ResponseEntity<DadosDetalhamentoCachorro> atualizar(@RequestBody @Valid DadosAtualizarCachorro dados){
		return ResponseEntity.ok(service.atualizar(dados));
	}
	
	@DeleteMapping("deletar/{id}")
	@Transactional 
	public ResponseEntity<DadosDetalhamentoCachorro> excluir(@PathVariable Long id) {
	    DadosDetalhamentoCachorro dadosExcluidos = service.deletar(id);
	    return ResponseEntity.ok(dadosExcluidos);
	}
	
	

}
