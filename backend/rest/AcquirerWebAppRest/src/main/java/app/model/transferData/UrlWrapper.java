package app.model.transferData;

import java.net.URI;

public class UrlWrapper {

	private URI resultURI;
	
	public UrlWrapper() {
		super();
	
	}

	public UrlWrapper(URI resultURI) {
		super();
		this.resultURI = resultURI;
	}

	public URI getResultURI() {
		return resultURI;
	}

	public void setResultURI(URI resultURI) {
		this.resultURI = resultURI;
	}

	@Override
	public String toString() {
		return "UrlWrapper [resultURI=" + resultURI + "]";
	}
	
	
	
}
