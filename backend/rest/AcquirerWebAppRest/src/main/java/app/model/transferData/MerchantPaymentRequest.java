package app.model.transferData;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class MerchantPaymentRequest {

	@NotNull(message = "error.merchantId.notnull")
	@Size(min = 5, max = 30, message = "error.merchantId.size")
	private String merchantId;

	@NotNull(message = "error.merchatPassword.notnull")
	@Size(min = 5, max = 30, message = "error.merchatPassword.size")
	private String merchatPassword;

	@NotNull(message = "error.amount.notnull")
	@Min(1)
	private BigDecimal amount;

	@NotNull(message = "error.merchantOrderId.notnull")
	private int merchantOrderId;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "error.merchantTimestamp.notnull")
	private Date merchantTimestamp;

	@NotNull(message = "error.errorUrl.notnull")
	private URL errorUrl;

	public MerchantPaymentRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MerchantPaymentRequest(String merchantId, String merchatPassword, BigDecimal amount, int merchantOrderId,
			Date merchantTimestamp, URL errorUrl) {
		super();
		this.merchantId = merchantId;
		this.merchatPassword = merchatPassword;
		this.amount = amount;
		this.merchantOrderId = merchantOrderId;
		this.merchantTimestamp = merchantTimestamp;
		this.errorUrl = errorUrl;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchatPassword() {
		return merchatPassword;
	}

	public void setMerchatPassword(String merchatPassword) {
		this.merchatPassword = merchatPassword;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public int getMerchantOrderId() {
		return merchantOrderId;
	}

	public void setMerchantOrderId(int merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}

	public Date getMerchantTimestamp() {
		return merchantTimestamp;
	}

	public void setMerchantTimestamp(Date merchantTimestamp) {
		this.merchantTimestamp = merchantTimestamp;
	}

	public URL getErrorUrl() {
		return errorUrl;
	}

	public void setErrorUrl(URL errorUrl) {
		this.errorUrl = errorUrl;
	}

	@Override
	public String toString() {
		return "PaymentRequest [merchantId=" + merchantId + ", merchatPassword=" + merchatPassword + ", amount="
				+ amount + ", merchantOrderId=" + merchantOrderId + ", merchantTimestamp=" + merchantTimestamp
				+ ", errorUrl=" + errorUrl + "]";
	}

}
