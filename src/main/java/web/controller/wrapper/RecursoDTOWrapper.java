package web.controller.wrapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import web.controller.dto.RecursoDTO;
import web.domain.model.Recurso;

@Component
public class RecursoDTOWrapper {

	public RecursoDTO toDTOModel(Recurso recurso) {
		RecursoDTO recursoModel = new RecursoDTO();
		recursoModel.setId(recurso.getId());
		recursoModel.setNome(recurso.getNome());

		return recursoModel;
	}

	public List<RecursoDTO> toDTOCollection(Collection<Recurso> recursos) {
		return recursos.stream().map(recurso -> toDTOModel(recurso)).collect(Collectors.toList());
	}

}
