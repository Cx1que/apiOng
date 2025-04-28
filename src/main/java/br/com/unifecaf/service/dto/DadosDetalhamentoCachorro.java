package br.com.unifecaf.service.dto;

import br.com.unifecaf.service.entity.Cachorro;
import br.com.unifecaf.service.enumerates.CastradoENUM;
import br.com.unifecaf.service.enumerates.PorteENUM;
import br.com.unifecaf.service.enumerates.SexoENUM;

public record DadosDetalhamentoCachorro(
		
		Long id,
		
		String nome,
		
		Integer idade,
		
		Double peso,

		SexoENUM sexo,
		
		String raca,
		
		PorteENUM porte,
		
		CastradoENUM castrado,
		
		String imagem,

		String descricao) {
	
	public DadosDetalhamentoCachorro (Cachorro cachorro) {
		this(cachorro.getId(),
				cachorro.getNome(),
				cachorro.getIdade(),
				cachorro.getPeso(),
				cachorro.getSexo(),
				cachorro.getRaca(),
				cachorro.getPorte(),
				cachorro.getCastrado(),
				cachorro.getImagem(),
				cachorro.getDescricao());
	}
}
