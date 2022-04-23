package br.com.nava.inicializacao;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.nava.entity.Aluno;
import br.com.nava.entity.AlunoDisciplina;
import br.com.nava.entity.Avaliacao;
import br.com.nava.entity.Disciplina;
import br.com.nava.entity.Turma;
import br.com.nava.repository.AlunoRepository;
import br.com.nava.repository.DisciplinaRepository;
import br.com.nava.service.AvaliacaoService;
import br.com.nava.service.DisciplinaService;
import br.com.nava.service.TurmaService;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent>{
	@Autowired
	AlunoRepository alunoRepo;
	@Autowired
	DisciplinaRepository disciplinaRepo;
	@Autowired
	TurmaService turmaService;
	@Autowired
	DisciplinaService disciplinaService;
	@Autowired
	AvaliacaoService avaliacaoService;
	
	@Override
	public void onApplicationEvent (ContextRefreshedEvent event) {
		
		Aluno aluno1 = new Aluno();
		aluno1.setNome("Lucas");
	
		Aluno aluno2 = new Aluno();
		aluno2.setNome("Arthur");
		
		Aluno aluno3 = new Aluno();
		aluno3.setNome("João");
		
		Aluno aluno4 = new Aluno();
		aluno4.setNome("Aline");
		
		Aluno aluno5 = new Aluno();
		aluno5.setNome("Renata");
		
		alunoRepo.saveAll(Arrays.asList(aluno1, aluno2, aluno3, aluno4, aluno5));
		
		Disciplina java1 =new Disciplina();
		java1.setNome("Java I");
		
		Disciplina java2 = new Disciplina();
		java2.setNome("Java II");
		
		Disciplina arquitetura = new Disciplina();
      	arquitetura.setNome("Arquitetura");
      	
      	Disciplina python1= new Disciplina();
		python1.setNome("Python I");
		
		Disciplina python2= new Disciplina();
		python2.setNome("Python II");
		
		disciplinaRepo.saveAll(Arrays.asList(java1, java2, arquitetura, python1, python2));
		
		Turma ads = new Turma();
		ads.setNome("ADS");		
		turmaService.salvar(ads);
		
		Turma rede = new Turma();
		rede.setNome("Rede");		
		turmaService.salvar(rede);
		
		
//		List<Turma> listaTurmas = turmaService.listaTodasTurmas();
//		
//		for(Turma turma : listaTurmas) {
//			System.out.println("Nome da turma: "+ turma.getNome());
//		}
//		
//		Turma turma =turmaService.buscaPorId(2);
//		System.out.println(turma.getNome());
		
					
		disciplinaService.salvar(java1);
		disciplinaService.salvar(java2);
		disciplinaService.salvar(arquitetura);
		disciplinaService.salvar(python1);
		disciplinaService.salvar(python2);					
					
				
		aluno1.setTurma(ads);
		aluno2.setTurma(ads);
		aluno3.setTurma(rede);
		aluno4.setTurma(rede);
		aluno5.setTurma(rede);
							
		
		aluno1.setDisciplinas(Arrays.asList(java1, arquitetura, java2));
		aluno2.setDisciplinas(Arrays.asList(java1, python1, python2));
		aluno3.setDisciplinas(Arrays.asList(java2, arquitetura, python2));
		aluno4.setDisciplinas(Arrays.asList(java1, java2));
		aluno5.setDisciplinas(Arrays.asList(arquitetura, python1, python2));
		
		alunoRepo.save(aluno1);
		alunoRepo.save(aluno2);
		alunoRepo.save(aluno3);
		alunoRepo.save(aluno4);
		alunoRepo.save(aluno5);
		
		
		
		AlunoDisciplina lucasArquitetura = new AlunoDisciplina();
		lucasArquitetura.setAluno(aluno1);
		lucasArquitetura.setDisciplina(arquitetura);
		
		Avaliacao avaliacaolucasArquit = new Avaliacao();
		avaliacaolucasArquit.setAlunoDisciplina(lucasArquitetura);
		avaliacaolucasArquit.setConceito("A");
		avaliacaoService.save(avaliacaolucasArquit);
		
				
		AlunoDisciplina joaoJava1 = new AlunoDisciplina();
		joaoJava1.setAluno(aluno2);
		joaoJava1.setDisciplina(java1);
		
		Avaliacao avaliacaoJoaoJava1 = new Avaliacao();
		avaliacaoJoaoJava1.setAlunoDisciplina(joaoJava1);
		avaliacaoJoaoJava1.setConceito("B");		
		avaliacaoService.save(avaliacaoJoaoJava1);
		
		Avaliacao aval = avaliacaoService.buscarNotaAlunoDisciplina(lucasArquitetura);
		System.out.println("Aluno: "+ aval.getAlunoDisciplina().getAluno().getNome());
		System.out.println("Disciplina: "+ aval.getAlunoDisciplina().getDisciplina().getNome());
		System.out.println("Avaliacão: "+ aval.getConceito());
		
	}
	
	
}
