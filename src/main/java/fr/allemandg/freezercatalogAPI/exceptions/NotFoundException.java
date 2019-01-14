package fr.allemandg.freezercatalogAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No result corresponding to the research parameter.")
public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = -7215328788291146001L;
}
