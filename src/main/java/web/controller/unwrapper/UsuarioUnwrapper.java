package web.controller.unwrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import web.controller.dto.request.UsuarioDTORequestBody;
import web.domain.exception.DataNotFoundException;
import web.domain.model.Recurso;
import web.domain.model.Usuario;
import web.domain.repository.RecursoRepository;

import java.util.Optional;

@Component
public class UsuarioUnwrapper {

	@Autowired
	private RecursoRepository recursoRepository;

	public Usuario toDomainModel(UsuarioDTORequestBody usuarioDTOInput) {
		Usuario usuario = new Usuario();
		usuario.setNome(usuarioDTOInput.getNome());
		usuario.setLogin(usuarioDTOInput.getLogin());
		usuario.setEmail(usuarioDTOInput.getEmail());
		usuario.setSenha(usuarioDTOInput.getSenha());
		usuario.setDataNascimento(usuarioDTOInput.getDataNascimento());

		// Vou percorrer a lista de ids de recursos passado no DTO e
		// associar ao usuário que será criado.
		// TODO Implementar um serviço para Recurso
		usuarioDTOInput.getRecursos().forEach(id -> {
			Recurso recurso = recursoRepository.findById(id)
					.orElseThrow(() -> new DataNotFoundException(
							String.format("Não existe entidade recurso de id %d", id)
					));

			usuario.associarRecurso(recurso);
		});

		return usuario;
	}
}
