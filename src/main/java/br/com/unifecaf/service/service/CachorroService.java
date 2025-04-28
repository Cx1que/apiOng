package br.com.unifecaf.service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.unifecaf.service.dto.DadosAtualizarCachorro;
import br.com.unifecaf.service.dto.DadosCachorro;
import br.com.unifecaf.service.dto.DadosDetalhamentoCachorro;
import br.com.unifecaf.service.entity.Cachorro;
import br.com.unifecaf.service.repository.CachorroRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class CachorroService {

	
	@Autowired
	private CachorroRepository repository;
	
	
    public DadosDetalhamentoCachorro criarCachorro(DadosCachorro dados) {
    	
    	Cachorro cachorro = new Cachorro();
        
        cachorro.setNome(dados.nome());
        cachorro.setIdade(dados.idade());
        cachorro.setPeso(dados.peso());
        cachorro.setSexo(dados.sexo());
        
        cachorro.setRaca(dados.raca());
        cachorro.setPorte(dados.porte());
        cachorro.setCastrado(dados.castrado());
        cachorro.setImagem(dados.imagem());
        cachorro.setDescricao(dados.descricao());
        Cachorro cachorroSalvo = repository.save(cachorro);

        return new DadosDetalhamentoCachorro(cachorroSalvo);
        
    }

	
	public Page<Cachorro> buscar(Pageable paginacao) {
        return repository.findAll(paginacao);
    }
	
	public Optional<DadosDetalhamentoCachorro> buscarPorId(Long id) {
		return repository.findById(id).map(DadosDetalhamentoCachorro::new);
	}
	
	@Transactional
	public DadosDetalhamentoCachorro atualizar(@Valid DadosAtualizarCachorro dados) {
	    Cachorro cachorro = repository.findById(dados.id())
	        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cachorro não encontrado"));

	    if (dados.nome() != null) {
	        cachorro.setNome(dados.nome());
	    }
	    if (dados.idade() != null) {
	        cachorro.setIdade(dados.idade());
	    }
	    if (dados.peso() != null) {
	        cachorro.setPeso(dados.peso());
	    }
	    if (dados.sexo() != null) {
	        cachorro.setSexo(dados.sexo());
	    }
	    if (dados.raca() != null) {
	        cachorro.setRaca(dados.raca());
	    }
	    if (dados.porte() != null) {
	        cachorro.setPorte(dados.porte());
	    }
	    if (dados.castrado() != null) {
	        cachorro.setCastrado(dados.castrado());
	    }
	    if (dados.imagem() != null) {
	        cachorro.setImagem(dados.imagem());
	    }
	    if (dados.descricao() != null) {
	        cachorro.setDescricao(dados.descricao());
	    }

	    return new DadosDetalhamentoCachorro(cachorro);
	}
	
	@Transactional
	public DadosDetalhamentoCachorro deletar(Long id) {
		
		Cachorro cachorro = repository.findById(id)
		        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cachorro não encontrado"));
		
		DadosDetalhamentoCachorro cachorroDeletado = new DadosDetalhamentoCachorro(cachorro);
		
		repository.deleteById(id);
		
		return cachorroDeletado;
	}


}
