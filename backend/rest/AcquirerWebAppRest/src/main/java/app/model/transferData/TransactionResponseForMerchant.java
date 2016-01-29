package app.model.transferData;

import javax.validation.constraints.NotNull;

public class TransactionResponseForMerchant {

	@NotNull(message = "error.merchantOrderId.notnull")
	private int merchantOrderId;

	@NotNull(message = "error.paymentId.notnull")
	private int paymentId;
	
	@NotNull(message = "error.acquirerInfo.notnull")
	private AcquirerInfo acquirerInfo;
	
	@NotNull(message = "error.transactionStatus.notnull")
	private TransactionStatus transactionStatus;

	public TransactionResponseForMerchant() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public TransactionResponseForMerchant(int merchantOrderId, int paymentId, AcquirerInfo acquirerInfo,
			TransactionStatus transactionStatus) {
		super();
		this.merchantOrderId = merchantOrderId;
		this.paymentId = paymentId;
		this.acquirerInfo = acquirerInfo;
		this.transactionStatus = transactionStatus;
	}



	public int getMerchantOrderId() {
		return merchantOrderId;
	}

	public void setMerchantOrderId(int merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public AcquirerInfo getAcquirerInfo() {
		return acquirerInfo;
	}

	public void setAcquirerInfo(AcquirerInfo acquirerInfo) {
		this.acquirerInfo = acquirerInfo;
	}

	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	@Override
	public String toString() {
		return "TransactionResponseForMerchant [merchantOrderId=" + merchantOrderId + ", paymentId=" + paymentId
				+ ", acquirerInfo=" + acquirerInfo + ", transactionStatus=" + transactionStatus + "]";
	}

	
	
}
