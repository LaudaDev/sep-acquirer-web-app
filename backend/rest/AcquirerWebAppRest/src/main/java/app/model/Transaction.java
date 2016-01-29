package app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import app.model.transferData.AcquirerInfo;
import app.model.transferData.IssuerInfo;
import app.model.transferData.MerchantPaymentRequest;
import app.model.transferData.TransactionStatus;

@Document
public class Transaction {

	@Id
	private String Id;
	private int paymentId;
	private AcquirerInfo acquirerInfo;
	private IssuerInfo issuerInfo;
	private MerchantPaymentRequest merchantRequestData;
	private TransactionStatus transactionStatus;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction(String id, int paymentId, AcquirerInfo acquirerInfo, IssuerInfo issuerInfo,
			MerchantPaymentRequest merchantRequestData, TransactionStatus transactionStatus) {
		super();
		Id = id;
		this.paymentId = paymentId;
		this.acquirerInfo = acquirerInfo;
		this.issuerInfo = issuerInfo;
		this.merchantRequestData = merchantRequestData;
		this.transactionStatus = transactionStatus;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
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

	public IssuerInfo getIssuerInfo() {
		return issuerInfo;
	}

	public void setIssuerInfo(IssuerInfo issuerInfo) {
		this.issuerInfo = issuerInfo;
	}

	public MerchantPaymentRequest getMerchantRequestData() {
		return merchantRequestData;
	}

	public void setMerchantRequestData(MerchantPaymentRequest merchantRequestData) {
		this.merchantRequestData = merchantRequestData;
	}

	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	@Override
	public String toString() {
		return "Transaction [Id=" + Id + ", paymentId=" + paymentId + ", acquirerInfo=" + acquirerInfo + ", issuerInfo="
				+ issuerInfo + ", merchantRequestData=" + merchantRequestData + ", transactionStatus="
				+ transactionStatus + "]";
	}

}
