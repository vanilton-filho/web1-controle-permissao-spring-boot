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

	public Usuario toDomainModel(UsuarioDTORequestBody usuarioDTORequestBody) {
		Usuario usuario = new Usuario();
		usuario.setNome(usuarioDTORequestBody.getNome());
		usuario.setLogin(usuarioDTORequestBody.getLogin());
		usuario.setEmail(usuarioDTORequestBody.getEmail());
		usuario.setSenha(usuarioDTORequestBody.getSenha());
		usuario.setDataNascimento(usuarioDTORequestBody.getDataNascimento());

		// Vou percorrer a lista de ids de recursos passado no DTO e
		// associar ao usuário que será criado.
		usuarioDTORequestBody.getRecursos().forEach(id -> {
			Recurso recurso = recursoRepository.findById(id)
					.orElseThrow(() -> new DataNotFoundException(
							String.format("Não existe entidade recurso de id %d", id)
					));

			usuario.associarRecurso(recurso);
		});

		return usuario;
	}
}
