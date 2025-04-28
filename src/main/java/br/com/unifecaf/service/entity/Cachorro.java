package br.com.unifecaf.service.entity;

import org.hibernate.validator.constraints.URL;

import br.com.unifecaf.service.enumerates.CastradoENUM;
import br.com.unifecaf.service.enumerates.PorteENUM;
import br.com.unifecaf.service.enumerates.SexoENUM;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "TB_CACHORRO")
public class Cachorro extends Animal {

	@Column(name = "RACA")
	private String raca;

	@Column(name = "PORTE")
	@Enumerated(EnumType.STRING)
	private PorteENUM porte;

	@Column(name = "CASTRADO")
	@Enumerated(EnumType.STRING)
	private CastradoENUM castrado;

	@Column(name = "IMAGEM")
	@URL(message = "URL inválida") 
    @Size(max = 2000, message = "URL muito longa")
	private String imagem;

	@Column(name = "DESCRICAO")
	private String descricao;

	public Cachorro() {
	}

	public Cachorro(String nome, Integer idade, Double peso, SexoENUM sexo, String raca, PorteENUM porte,
			CastradoENUM castrado, String imagem, String descricao) {
		super(nome, idade, peso, sexo);
		this.raca = raca;
		this.porte = porte;
		this.castrado = castrado;
		this.imagem = imagem;
		this.descricao = descricao;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public PorteENUM getPorte() {
		return porte;
	}

	public void setPorte(PorteENUM porte) {
		this.porte = porte;
	}

	public CastradoENUM getCastrado() {
		return castrado;
	}

	public void setCastrado(CastradoENUM castrado) {
		this.castrado = castrado;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
