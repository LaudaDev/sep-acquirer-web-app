package app.model.transferData;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class TransactionAuthenticationRequest {

	@NotNull(message = "error.acquirerOrderId.notnull")
	private int acquirerOrderId;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "error.acquirerTimestamp.notnull")
	private Date acquirerTimestamp;

	@NotNull(message = "error.paymentCardDetails.notnull")
	private PaymentCardDetails paymentCardDetails;

	public TransactionAuthenticationRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TransactionAuthenticationRequest(int acquirerOrderId, Date acquirerTimestamp,
			PaymentCardDetails paymentCardDetails) {
		super();
		this.acquirerOrderId = acquirerOrderId;
		this.acquirerTimestamp = acquirerTimestamp;
		this.paymentCardDetails = paymentCardDetails;
	}

	public int getAcquirerOrderId() {
		return acquirerOrderId;
	}

	public void setAcquirerOrderId(int acquirerOrderId) {
		this.acquirerOrderId = acquirerOrderId;
	}

	public Date getAcquirerTimestamp() {
		return acquirerTimestamp;
	}

	public void setAcquirerTimestamp(Date acquirerTimestamp) {
		this.acquirerTimestamp = acquirerTimestamp;
	}

	public PaymentCardDetails getPaymentCardDetails() {
		return paymentCardDetails;
	}

	public void setPaymentCardDetails(PaymentCardDetails paymentCardDetails) {
		this.paymentCardDetails = paymentCardDetails;
	}

	@Override
	public String toString() {
		return "TransactionAuthenticationRequest [acquirerOrderId=" + acquirerOrderId + ", acquirerTimestamp="
				+ acquirerTimestamp + ", paymentCardDetails=" + paymentCardDetails + "]";
	}

}
