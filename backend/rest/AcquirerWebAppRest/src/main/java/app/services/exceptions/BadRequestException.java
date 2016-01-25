package app.services.exceptions;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Entity is not valid.") 
public class BadRequestException  extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(BadRequestException.class);
	
	public BadRequestException(String message)
	{
		logger.error(message);
	}
}
