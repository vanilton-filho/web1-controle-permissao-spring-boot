package web.controller.wrapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import web.controller.dto.UsuarioDTO;
import web.domain.model.Usuario;

@Component
public class UsuarioDTOWrapper {

	public UsuarioDTO toDTOModel(Usuario usuario) {
		UsuarioDTO usuarioModel = new UsuarioDTO();
		usuarioModel.setStatus(usuario.getStatus());
		usuarioModel.setId(usuario.getId());
		usuarioModel.setNome(usuario.getNome());
		usuarioModel.setLogin(usuario.getLogin());
		usuarioModel.setEmail(usuario.getEmail());
		usuarioModel.setDataNascimento(usuario.getDataNascimento());
		return usuarioModel;
	}

	public List<UsuarioDTO> toDTOCollection(List<Usuario> usuarios) {
		return usuarios.stream().map(usuario -> toDTOModel(usuario)).collect(Collectors.toList());
	}
}
