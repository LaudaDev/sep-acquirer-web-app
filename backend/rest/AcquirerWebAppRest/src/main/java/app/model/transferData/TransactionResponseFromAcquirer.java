package app.model.transferData;

import javax.validation.constraints.NotNull;

public class TransactionResponseFromAcquirer {

	@NotNull(message = "error.acquirerInfo.notnull")
	private AcquirerInfo acquirerInfo;
	
	@NotNull(message = "error.issuerInfo.notnull")
	private IssuerInfo issuerInfo;

	@NotNull(message = "error.transactionStatus.notnull")
	private TransactionStatus transactionStatus;

	public TransactionResponseFromAcquirer() {
		super();
	}

	public TransactionResponseFromAcquirer(AcquirerInfo acquirerInfo, IssuerInfo issuerInfo,
			TransactionStatus transactionStatus) {
		super();
		this.acquirerInfo = acquirerInfo;
		this.issuerInfo = issuerInfo;
		this.transactionStatus = transactionStatus;
	}

	public AcquirerInfo getAcquirerInfo() {
		return acquirerInfo;
	}

	public void setAcquirerInfo(AcquirerInfo acquirerInfo) {
		this.acquirerInfo = acquirerInfo;
	}

	public IssuerInfo getIssuerInfo() {
		return issuerInfo;
	}

	public void setIssuerInfo(IssuerInfo issuerInfo) {
		this.issuerInfo = issuerInfo;
	}

	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	@Override
	public String toString() {
		return "TransactionResponse [acquirerInfo=" + acquirerInfo + ", issuerInfo=" + issuerInfo
				+ ", transactionStatus=" + transactionStatus + "]";
	}

}
