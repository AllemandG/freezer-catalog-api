package fr.allemandg.freezercatalogAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.PRECONDITION_FAILED, reason="The action could not be done due to preconditions not fulfilled.")
public class PreconditionFailedException extends RuntimeException {
	private static final long serialVersionUID = -4003399132891526209L;
}
