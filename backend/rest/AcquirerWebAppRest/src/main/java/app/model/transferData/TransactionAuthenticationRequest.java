package app.model.transferData;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class TransactionAuthenticationRequest {
	
	@NotNull(message = "error.paymentCardDetails.notnull")
	private PaymentCardInfo cardInfo;

	@NotNull(message = "error.acquirerInfo.notnull")
	private AcquirerInfo acquirerInfo;
	
	@NotNull(message = "error.transactionAmount.notnull")
	private BigDecimal transactionAmount;
	
	public TransactionAuthenticationRequest() {
		super();
		// TODO Auto-generated constructor stub
	}


	public TransactionAuthenticationRequest(PaymentCardInfo cardInfo, AcquirerInfo acquirerInfo,
			BigDecimal transactionAmount) {
		super();
		this.cardInfo = cardInfo;
		this.acquirerInfo = acquirerInfo;
		this.transactionAmount = transactionAmount;
	}


	public AcquirerInfo getAcquirerInfo() {
		return acquirerInfo;
	}

	public void setAcquirerInfo(AcquirerInfo acquirerInfo) {
		this.acquirerInfo = acquirerInfo;
	}


	public PaymentCardInfo getCardInfo() {
		return cardInfo;
	}


	public void setCardInfo(PaymentCardInfo cardInfo) {
		this.cardInfo = cardInfo;
	}


	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}


	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}


	@Override
	public String toString() {
		return "TransactionAuthenticationRequest [cardInfo=" + cardInfo + ", acquirerInfo=" + acquirerInfo
				+ ", transactionAmount=" + transactionAmount + "]";
	}



	
}
