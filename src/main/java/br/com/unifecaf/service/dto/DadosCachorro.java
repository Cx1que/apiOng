package br.com.unifecaf.service.dto;

import br.com.unifecaf.service.enumerates.CastradoENUM;
import br.com.unifecaf.service.enumerates.PorteENUM;
import br.com.unifecaf.service.enumerates.SexoENUM;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DadosCachorro(
		
		@NotBlank(message = "O Nome é obrigatório.")
		String nome,
		
		@NotNull(message = "O Idade é obrigatória.")
		@Positive(message = "Idade deve ser positiva")
		Integer idade,
		
		@NotNull(message = "O Peso é obrigatório.")
		@DecimalMin(value = "0.1", message = "Peso deve ser maior que zero")
		Double peso,

		@NotNull(message = "O Sexo é obrigatório.")
		SexoENUM sexo,
		
		@NotNull(message = "O Raça é obrigatória.")
		String raca,
		
		@NotNull(message = "O Porte é obrigatória.")
		PorteENUM porte,
		
		CastradoENUM castrado,
		
		String imagem,

		String descricao) {

	
	
	

}


