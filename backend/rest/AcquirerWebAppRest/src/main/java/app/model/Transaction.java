package app.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import app.model.transferData.MerchantPaymentRequest;

@Document
public class Transaction {

	@Id
	private String Id;
	private int paymentId;
	private int acquirerOrderId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date acquirerTimestamp;
	private int issuerOrderId;
	private Date issuerTimestamp;
	private MerchantPaymentRequest merchantRequestData;
	private boolean isCardAuthorized;
	private boolean isCardAuthenticated;
	private boolean isTransactionSucceded;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Transaction(String id, int paymentId, int acquirerOrderId, Date acquirerTimestamp, int issuerOrderId,
			Date issuerTimestamp, MerchantPaymentRequest merchantRequestData, boolean isCardAuthorized,
			boolean isCardAuthenticated, boolean isTransactionSucceded) {
		super();
		Id = id;
		this.paymentId = paymentId;
		this.acquirerOrderId = acquirerOrderId;
		this.acquirerTimestamp = acquirerTimestamp;
		this.issuerOrderId = issuerOrderId;
		this.issuerTimestamp = issuerTimestamp;
		this.merchantRequestData = merchantRequestData;
		this.isCardAuthorized = isCardAuthorized;
		this.isCardAuthenticated = isCardAuthenticated;
		this.isTransactionSucceded = isTransactionSucceded;
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

	public int getIssuerOrderId() {
		return issuerOrderId;
	}

	public void setIssuerOrderId(int issuerOrderId) {
		this.issuerOrderId = issuerOrderId;
	}

	public Date getIssuerTimestamp() {
		return issuerTimestamp;
	}

	public void setIssuerTimestamp(Date issuerTimestamp) {
		this.issuerTimestamp = issuerTimestamp;
	}

	public MerchantPaymentRequest getMerchantRequestData() {
		return merchantRequestData;
	}

	public void setMerchantRequestData(MerchantPaymentRequest merchantRequestData) {
		this.merchantRequestData = merchantRequestData;
	}

	public boolean isCardAuthorized() {
		return isCardAuthorized;
	}

	public void setCardAuthorized(boolean isCardAuthorized) {
		this.isCardAuthorized = isCardAuthorized;
	}

	public boolean isTransactionSucceded() {
		return isTransactionSucceded;
	}

	public void setTransactionSucceded(boolean isTransactionSucceded) {
		this.isTransactionSucceded = isTransactionSucceded;
	}



	public boolean isCardAuthenticated() {
		return isCardAuthenticated;
	}



	public void setCardAuthenticated(boolean isCardAuthenticated) {
		this.isCardAuthenticated = isCardAuthenticated;
	}



	@Override
	public String toString() {
		return "Transaction [Id=" + Id + ", paymentId=" + paymentId + ", acquirerOrderId=" + acquirerOrderId
				+ ", acquirerTimestamp=" + acquirerTimestamp + ", issuerOrderId=" + issuerOrderId + ", issuerTimestamp="
				+ issuerTimestamp + ", merchantRequestData=" + merchantRequestData + ", isCardAuthorized="
				+ isCardAuthorized + ", isCardAuthenticated=" + isCardAuthenticated + ", isTransactionSucceded="
				+ isTransactionSucceded + "]";
	}

	

}
