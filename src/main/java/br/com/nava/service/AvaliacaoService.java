package br.com.nava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nava.entity.AlunoDisciplina;
import br.com.nava.entity.Avaliacao;
import br.com.nava.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {
	
	@Autowired //injeção de dependência
	AvaliacaoRepository repo;
	
	//métodos
	public Avaliacao save (Avaliacao avaliacao) {
		return repo.save(avaliacao);			
	}
	
	public List<Avaliacao> findAll(){
		return repo.findAll();
	}
	
	public Avaliacao buscarNotaAlunoDisciplina(AlunoDisciplina alunoDisciplina) {
		return repo.findByAlunoDisciplina(alunoDisciplina);
	}
}
