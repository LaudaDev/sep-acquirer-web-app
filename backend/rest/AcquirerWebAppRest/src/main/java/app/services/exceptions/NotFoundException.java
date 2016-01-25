package app.services.exceptions;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such entity.") 
public class NotFoundException extends RuntimeException
{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(NotFoundException.class);
	
	public NotFoundException(String message)
	{
		logger.error(message);
	}

}
