package web.controller.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecursoIdDTOArray {

    private List<Long> ids;

}
