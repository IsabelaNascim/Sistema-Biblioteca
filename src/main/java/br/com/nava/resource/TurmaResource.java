package br.com.nava.resource;

import java.net.URI;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.apache.logging.log4j.message.Message;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.nava.constantes.Messages;
import br.com.nava.entity.Turma;
import br.com.nava.service.TurmaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = Messages.Swagger_TAG_TURMA_ENDPOINT)
@RestController
@RequestMapping("/turma")
public class TurmaResource {
	
	@Autowired
	private TurmaService turmaService;
	
	@Operation(description = Messages.Swagger_GET_ALL)
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Turma>> listarTurmas(){
		List<Turma> turmas = turmaService.listaTodasTurmas();
		return ResponseEntity.ok().body(turmas);
	}
	
	@Operation(description = Messages.Swagger_GET_ID)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET )
	public ResponseEntity<Turma> buscaPorId(@ PathVariable Integer id) throws ObjectNotFoundException {
		Turma turma = turmaService.buscaPorId(id);
		return ResponseEntity.ok().body(turma);
	}
	
	@Operation(description = Messages.Swagger_INSERT)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@RequestBody Turma turma){
		Turma objTurma = turmaService.salvar(turma);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objTurma.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@Operation(description = Messages.Swagger_DELETE)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable Integer id){
		turmaService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(description = Messages.Swagger_UPDATE)
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> alterar(@RequestBody Turma objTurma, @PathVariable Integer id){
		objTurma.setId(id);
		turmaService.alterar(objTurma);
		return ResponseEntity.noContent().build();
	}
}
