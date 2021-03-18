package web.controller.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioAtivoDTO {

    private Long id;

    private String nome;

    private String login;

    private String email;

    private LocalDate dataNascimento;

    private List<RecursoDTO> recursos;
}
