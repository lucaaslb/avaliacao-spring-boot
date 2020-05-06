package br.com.tokiomarine.seguradora.avaliacao.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import lombok.Getter;

@Getter
public class EstudanteDto {

	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private String matricula;
	private String curso;

	public EstudanteDto(Estudante estudante) {

		this.id = estudante.getId();
		this.nome = estudante.getNome();
		this.email = estudante.getEmail();
		this.telefone = estudante.getTelefone();
		this.matricula = estudante.getMatricula();
		this.curso = estudante.getCurso();
	}

	public static List<EstudanteDto> converter(List<Estudante> estudante) {
		return estudante.stream().map(EstudanteDto::new).collect(Collectors.toList());
	}

}
