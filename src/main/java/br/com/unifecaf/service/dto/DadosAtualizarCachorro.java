package br.com.unifecaf.service.dto;

import br.com.unifecaf.service.enumerates.CastradoENUM;
import br.com.unifecaf.service.enumerates.PorteENUM;
import br.com.unifecaf.service.enumerates.SexoENUM;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarCachorro(
		
		@NotNull(message = "Campo Id é obrigatótio")
	    Long id,

	    String nome,
	    
	    Integer idade,
	    
	    Double peso,
	    
	    SexoENUM sexo,
	    
	    String raca,
	    
	    PorteENUM porte,
	    
	    CastradoENUM castrado,
	    
	    String imagem,
	    
	    String descricao) {}
