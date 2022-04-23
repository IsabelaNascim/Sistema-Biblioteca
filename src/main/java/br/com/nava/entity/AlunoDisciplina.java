package br.com.nava.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable // --> indicativo de chave composta
public class AlunoDisciplina implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4348149382215556719L;

			//setando a chave composta//
	@ManyToOne
	private Aluno aluno;
	
	@ManyToOne
	private Disciplina disciplina;
	
	
			//getters and setters//
	public Aluno getAluno() {
		return aluno;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	
}
