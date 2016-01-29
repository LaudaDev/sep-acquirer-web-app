package app.model.transferData;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class IssuerInfo {
	
	@NotNull(message = "error.issuerOrderId.notnull")
	private int orderId;

	@DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
	@NotNull(message = "error.issuerTimestamp.notnull")
	private Date timestamp;
	
	

	public IssuerInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IssuerInfo(int issuerOrderId, Date issuerTimestamp) {
		super();
		this.orderId = issuerOrderId;
		this.timestamp = issuerTimestamp;
	}
	
	public int getIssuerOrderId() {
		return orderId;
	}

	public void setIssuerOrderId(int issuerOrderId) {
		this.orderId = issuerOrderId;
	}

	public Date getIssuerTimestamp() {
		return timestamp;
	}

	public void setIssuerTimestamp(Date issuerTimestamp) {
		this.timestamp = issuerTimestamp;
	}

	@Override
	public String toString() {
		return "IssuerInfo [issuerOrderId=" + orderId + ", issuerTimestamp=" + timestamp + "]";
	}

	
}
