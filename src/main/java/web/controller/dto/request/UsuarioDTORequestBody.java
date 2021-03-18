package web.controller.dto.request;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTORequestBody {
	
	private String nome;
	
	private String login;
	
	private String email;

	private String senha;

	private LocalDate dataNascimento;

    // O payload nessa parte deve ser um array de ids [1, 2, 3]
	private List<Long> recursos;
	
}
