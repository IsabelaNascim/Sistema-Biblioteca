package br.com.nava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nava.entity.AlunoDisciplina;
import br.com.nava.entity.Avaliacao;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, AlunoDisciplina>{
//< Entidade responsável pela persistência desta Repository, O tipo da chave primária da entidade >
	
	//select * from aliacao where id_aluno = 3 and id_disciplina = 6
	Avaliacao findByAlunoDisciplina(AlunoDisciplina alunoDisciplina);
}
