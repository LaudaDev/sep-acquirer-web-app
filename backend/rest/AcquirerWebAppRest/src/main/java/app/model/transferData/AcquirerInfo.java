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

	public AcquirerInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AcquirerInfo(Integer orderId, Date timestamp) {
		super();
		this.orderId = orderId;
		this.timestamp = timestamp;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "AcquirerInfo [acquirerOrderId=" + orderId + ", acquirerTimestamp=" + timestamp + "]";
	}

}
