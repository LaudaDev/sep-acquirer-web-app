package app.model.transferData;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class MerchantInfo {


	@NotNull(message = "error.merchantOrderId.notnull")
	private Integer merchantOrderId;

	@DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
	@NotNull(message = "error.merchantTimestamp.notnull")
	private Date merchantTimestamp;

	
	public MerchantInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MerchantInfo(Integer merchantOrderId, Date merchantTimestamp) {
		super();
		this.merchantOrderId = merchantOrderId;
		this.merchantTimestamp = merchantTimestamp;
	}

	public Integer getMerchantOrderId() {
		return merchantOrderId;
	}

	public void setMerchantOrderId(Integer merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}

	public Date getMerchantTimestamp() {
		return merchantTimestamp;
	}

	public void setMerchantTimestamp(Date merchantTimestamp) {
		this.merchantTimestamp = merchantTimestamp;
	}

	@Override
	public String toString() {
		return "MerchantInfo [merchantOrderId=" + merchantOrderId + ", merchantTimestamp=" + merchantTimestamp + "]";
	}

	
}
