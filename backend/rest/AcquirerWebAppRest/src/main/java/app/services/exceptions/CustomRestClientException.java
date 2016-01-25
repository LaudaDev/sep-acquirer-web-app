package app.services.exceptions;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.SERVICE_UNAVAILABLE) 
public class CustomRestClientException extends RuntimeException
{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CustomRestClientException.class);
	
	public CustomRestClientException(String message)
	{
		logger.error(message);
	}

}