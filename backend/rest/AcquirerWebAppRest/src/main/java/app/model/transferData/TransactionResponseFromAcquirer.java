package app.model.transferData;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class TransactionResponseFromAcquirer {

	@NotNull(message = "error.isCardAuthenticated.notnull")
	private boolean isCardAuthenticated;

	@NotNull(message = "error.isCardAuthorized.notnull")
	private boolean isCardAuthorized;

	@NotNull(message = "error.isTransactionSucceded.notnull")
	private boolean isTransactionSucceded;

	@NotNull(message = "error.acquirerOrderId.notnull")
	private int acquirerOrderId;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "error.acquirerTimestamp.notnull")
	private Date acquirerTimestamp;

	@NotNull(message = "error.issuerOrderId.notnull")
	private int issuerOrderId;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "error.issuerTimestamp.notnull")
	private Date issuerTimestamp;

	public TransactionResponseFromAcquirer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TransactionResponseFromAcquirer(boolean isCardAuthenticated, boolean isCardAuthorized,
			boolean isTransactionSucceded, int acquirerOrderId, Date acquirerTimestamp, int issuerOrderId,
			Date issuerTimestamp) {
		super();
		this.isCardAuthenticated = isCardAuthenticated;
		this.isCardAuthorized = isCardAuthorized;
		this.isTransactionSucceded = isTransactionSucceded;
		this.acquirerOrderId = acquirerOrderId;
		this.acquirerTimestamp = acquirerTimestamp;
		this.issuerOrderId = issuerOrderId;
		this.issuerTimestamp = issuerTimestamp;
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

	@Override
	public String toString() {
		return "TransactionResponseFromAcquirer [isCardAuthenticated=" + isCardAuthenticated + ", isCardAuthorized="
				+ isCardAuthorized + ", isTransactionSucceded=" + isTransactionSucceded + ", acquirerOrderId="
				+ acquirerOrderId + ", acquirerTimestamp=" + acquirerTimestamp + ", issuerOrderId=" + issuerOrderId
				+ ", issuerTimestamp=" + issuerTimestamp + "]";
	}

	
}
