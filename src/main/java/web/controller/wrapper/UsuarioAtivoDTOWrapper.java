package web.controller.wrapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import web.controller.dto.UsuarioAtivoDTO;
import web.domain.model.Usuario;

@Component
public class UsuarioAtivoDTOWrapper {

    @Autowired
    private RecursoDTOWrapper recursoDTOWrapper;

    public UsuarioAtivoDTO toDTOModel(Usuario usuario) {
        UsuarioAtivoDTO usuarioModel = new UsuarioAtivoDTO();
        usuarioModel.setId(usuario.getId());
        usuarioModel.setNome(usuario.getNome());
        usuarioModel.setLogin(usuario.getLogin());
        usuarioModel.setEmail(usuario.getEmail());
        usuarioModel.setDataNascimento(usuario.getDataNascimento());
        usuarioModel.setRecursos(recursoDTOWrapper.toDTOCollection(usuario.getRecursos()));
        return usuarioModel;
    }

    public List<UsuarioAtivoDTO> toDTOCollection(List<Usuario> usuarios) {
        return usuarios.stream().map(usuario -> toDTOModel(usuario)).collect(Collectors.toList());
    }
}
