package app.model.transferData;

import java.math.BigDecimal;
import java.net.URL;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

	@NotNull(message = "error.merchantInfo.notnull")
	private MerchantInfo merchantInfo;

	@NotNull(message = "error.errorUrl.notnull")
	private URL errorUrl;

	public MerchantPaymentRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public MerchantPaymentRequest(String merchantId, String merchatPassword, BigDecimal amount,
			MerchantInfo merchantInfo, URL errorUrl) {
		super();
		this.merchantId = merchantId;
		this.merchatPassword = merchatPassword;
		this.amount = amount;
		this.merchantInfo = merchantInfo;
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

	public MerchantInfo getMerchantInfo() {
		return merchantInfo;
	}

	public void setMerchantInfo(MerchantInfo merchantInfo) {
		this.merchantInfo = merchantInfo;
	}

	public URL getErrorUrl() {
		return errorUrl;
	}

	public void setErrorUrl(URL errorUrl) {
		this.errorUrl = errorUrl;
	}

	@Override
	public String toString() {
		return "MerchantPaymentRequest [merchantId=" + merchantId + ", merchatPassword=" + merchatPassword + ", amount="
				+ amount + ", merchantInfo=" + merchantInfo + ", errorUrl=" + errorUrl + "]";
	}
}
