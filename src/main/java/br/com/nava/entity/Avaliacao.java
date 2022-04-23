package br.com.nava.entity;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "avaliacoes")
public class Avaliacao implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5852248540408768070L;

	@EmbeddedId
	private AlunoDisciplina alunoDisciplina; //{ -idAluno  -idDisciplina}
		
	private String conceito;

	
			//getters and setters//
	public AlunoDisciplina getAlunoDisciplina() {
		return alunoDisciplina;
	}

	public String getConceito() {
		return conceito;
	}

	public void setAlunoDisciplina(AlunoDisciplina alunoDisciplina) {
		this.alunoDisciplina = alunoDisciplina;
	}

	public void setConceito(String conceito) {
		this.conceito = conceito;
	}
	
	
}
