package app.model.transferData;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import app.commons.Consts;

public class IssuerInfo {

	@NotNull(message = "error.issuerOrderId.notnull")
	private Integer orderId;

	@JsonFormat(pattern = Consts.datePattern)
	@NotNull(message = "error.issuerTimestamp.notnull")
	private Date timestamp;

	public IssuerInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IssuerInfo(Integer orderId, Date timestamp) {
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
		return "IssuerInfo [issuerOrderId=" + orderId + ", issuerTimestamp=" + timestamp + "]";
	}

}
