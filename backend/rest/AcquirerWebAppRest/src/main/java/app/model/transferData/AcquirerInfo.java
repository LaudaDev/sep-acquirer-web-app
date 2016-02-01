package app.model.transferData;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class AcquirerInfo {

	@NotNull(message = "error.acquirerOrderId.notnull")
	private Integer orderId;

	@DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
	@NotNull(message = "error.acquirerTimestamp.notnull")
	private Date timestamp;
	
	
	public AcquirerInfo(Integer acquirerOrderId, Date acquirerTimestamp) {
		super();
		this.orderId = acquirerOrderId;
		this.timestamp = acquirerTimestamp;
	}

	public Integer getAcquirerOrderId() {
		return orderId;
	}

	public void setAcquirerOrderId(Integer acquirerOrderId) {
		this.orderId = acquirerOrderId;
	}

	public Date getAcquirerTimestamp() {
		return timestamp;
	}

	public void setAcquirerTimestamp(Date acquirerTimestamp) {
		this.timestamp = acquirerTimestamp;
	}

	@Override
	public String toString() {
		return "AcquirerInfo [acquirerOrderId=" + orderId + ", acquirerTimestamp=" + timestamp + "]";
	}

	
}
