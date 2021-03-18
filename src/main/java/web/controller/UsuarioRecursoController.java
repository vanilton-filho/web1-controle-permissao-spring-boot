package web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import web.controller.dto.RecursoDTO;
import web.controller.wrapper.RecursoDTOWrapper;
import web.domain.service.UsuarioService;

@RestController
@RequestMapping("/usuarios/{id}/recursos")
public class UsuarioRecursoController {

	@Autowired
	public UsuarioService usuarioService;

	@Autowired
	public RecursoDTOWrapper recursoDTOWrapper;

	@GetMapping
	public List<RecursoDTO> getAll(@PathVariable Long id) {
		var usuario = usuarioService.getByIdOrFail(id);
		return recursoDTOWrapper.toDTOCollection(usuario.getRecursos());
	}

	@PutMapping("/{recursoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long id, @PathVariable Long recursoId) {
		usuarioService.associar(id, recursoId);
	}

	@DeleteMapping("/{recursoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long id, @PathVariable Long recursoId) {
		usuarioService.desassociar(id, recursoId);
	}
}
