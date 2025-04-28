package br.com.unifecaf.service.entity;

import java.io.Serializable;


import br.com.unifecaf.service.enumerates.SexoENUM;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Animal implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "NOME")
	@NotBlank(message = "Nome é obrigatório")
	private String nome;

	@Column(name = "IDADE")
	@Positive(message = "Idade deve ser positiva")
	private Integer idade;

	@Column(name = "PESO")
	@DecimalMin(value = "0.1", message = "Peso deve ser maior que zero")
	private Double peso;

	@Column(name = "SEXO")
	@Enumerated(EnumType.STRING)
	private SexoENUM sexo;
	
	

	public Animal() {}

	public Animal( String nome, Integer idade, Double peso, SexoENUM sexo) {
		this.nome = nome;
		this.idade = idade;
		this.peso = peso;
		this.sexo = sexo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public SexoENUM getSexo() {
		return sexo;
	}

	public void setSexo(SexoENUM sexo) {
		this.sexo = sexo;
	}
	
	

}
