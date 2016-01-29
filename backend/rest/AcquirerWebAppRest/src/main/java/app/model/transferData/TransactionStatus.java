package app.model.transferData;

import javax.validation.constraints.NotNull;

public class TransactionStatus {

	@NotNull(message = "error.code.notnull")
	private String code;
	
	private String message;

	public TransactionStatus() {

	}

	public TransactionStatus(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "TransactionStatus [code=" + code + ", message=" + message + "]";
	}
}
