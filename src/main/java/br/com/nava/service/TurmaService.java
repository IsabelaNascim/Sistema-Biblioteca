package br.com.nava.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nava.entity.Turma;
import br.com.nava.repository.TurmaRepository;

@Service
public class TurmaService {
	
	@Autowired
	TurmaRepository repo;
	
	//criar método de listar todas as turmas
	public List<Turma> listaTodasTurmas(){
		return repo.findAll();
	}	
	
	//criar um método para trazer a turma por Id
	public Turma buscaPorId(Integer id) throws ObjectNotFoundException{
		Optional<Turma> turma = repo.findById(id);
		return turma.orElseThrow(() -> new ObjectNotFoundException(null, "Turma não encontrada."));
	}
	
	
	//criar um método para inserir a turma
	public Turma salvar(Turma turma) {
		return repo.save(turma);
	}
		
	
	//criar um método para fazer o update da turma	
	public Turma alterar(Turma objTurma) {
		Turma turma = buscaPorId(objTurma.getId());
		turma.setNome(objTurma.getNome());
		return salvar(turma);
	}
	
	///criar um método para deletar a turma
	public void excluir(Integer id) {
		repo.deleteById(id);		
	}
}
