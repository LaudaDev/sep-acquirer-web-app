package app.model.transferData;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class TransactionResponseForMerchant {

	@NotNull(message = "error.isCardAuthenticated.notnull")
	private boolean isCardAuthenticated;

	@NotNull(message = "error.isCardAuthorized.notnull")
	private boolean isCardAuthorized;

	@NotNull(message = "error.isTransactionSucceded.notnull")
	private boolean isTransactionSucceded;

	@NotNull(message = "error.merchantOrderId.notnull")
	private int merchantOrderId;

	@NotNull(message = "error.acquirerOrderId.notnull")
	private int acquirerOrderId;

	@NotNull(message = "error.paymentId.notnull")
	private int paymentId;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "error.acquirerTimestamp.notnull")
	private Date acquirerTimestamp;

	public TransactionResponseForMerchant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TransactionResponseForMerchant(boolean isCardAuthenticated, boolean isCardAuthorized,
			boolean isTransactionSucceded, int merchantOrderId, int acquirerOrderId, int paymentId,
			Date acquirerTimestamp) {
		super();
		this.isCardAuthenticated = isCardAuthenticated;
		this.isCardAuthorized = isCardAuthorized;
		this.isTransactionSucceded = isTransactionSucceded;
		this.merchantOrderId = merchantOrderId;
		this.acquirerOrderId = acquirerOrderId;
		this.paymentId = paymentId;
		this.acquirerTimestamp = acquirerTimestamp;
	}

	public boolean isCardAuthenticated() {
		return isCardAuthenticated;
	}

	public void setCardAuthenticated(boolean isCardAuthenticated) {
		this.isCardAuthenticated = isCardAuthenticated;
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

	public int getMerchantOrderId() {
		return merchantOrderId;
	}

	public void setMerchantOrderId(int merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}

	public int getAcquirerOrderId() {
		return acquirerOrderId;
	}

	public void setAcquirerOrderId(int acquirerOrderId) {
		this.acquirerOrderId = acquirerOrderId;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public Date getAcquirerTimestamp() {
		return acquirerTimestamp;
	}

	public void setAcquirerTimestamp(Date acquirerTimestamp) {
		this.acquirerTimestamp = acquirerTimestamp;
	}

	@Override
	public String toString() {
		return "TransactionResponseForMerchant [isCardAuthenticated=" + isCardAuthenticated + ", isCardAuthorized="
				+ isCardAuthorized + ", isTransactionSucceded=" + isTransactionSucceded + ", merchantOrderId="
				+ merchantOrderId + ", acquirerOrderId=" + acquirerOrderId + ", paymentId=" + paymentId
				+ ", acquirerTimestamp=" + acquirerTimestamp + "]";
	}

	
}
