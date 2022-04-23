package br.com.nava.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.nava.constantes.Messages;
import br.com.nava.entity.Aluno;
import br.com.nava.entity.AlunoDisciplina;
import br.com.nava.entity.Avaliacao;
import br.com.nava.entity.Disciplina;
import br.com.nava.service.AvaliacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Avaliação")
@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoResource {
	
	@Autowired
	private AvaliacaoService service;
	
	@Operation(description = Messages.Swagger_GET_ALL)
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Avaliacao>> listarAvaliacao(){
		List<Avaliacao> listaAvaliacao = service.findAll();		
		return ResponseEntity.ok().body(listaAvaliacao);
	}
	@Operation(description = Messages.Swagger_INSERT)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@RequestBody Avaliacao objAvaliacao){
		objAvaliacao = service.save(objAvaliacao);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objAvaliacao.getAlunoDisciplina()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@Operation(description = Messages.Swagger_GET_ID)
	@RequestMapping(value = "/{idAluno}/{idDisciplina}", method = RequestMethod.GET)
	public ResponseEntity<Avaliacao> buscarAvaliacaoAlunoPorDisciplina(@PathVariable Integer idAluno, @PathVariable Integer idDisciplina){
		Aluno aluno = new Aluno();
		aluno.setId(idAluno);
		
		Disciplina disciplina = new Disciplina();
		disciplina.setId(idDisciplina);
		
		
		AlunoDisciplina alunoDisciplina = new AlunoDisciplina();
		alunoDisciplina.setAluno(aluno);
		alunoDisciplina.setDisciplina(disciplina);
		
		Avaliacao avaliacao = service.buscarNotaAlunoDisciplina(alunoDisciplina);
		
		return ResponseEntity.ok().body(avaliacao);
	}
}
