package app.model.transferData;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class PaymentCardDetails {

	@NotNull(message = "error.pan.notnull")
	@Size(min = 10, max = 10, message = "error.pan.size")
	private String pan;

	@NotNull(message = "error.securityCode.notnull")
	private int securityCode;

	@NotNull(message = "error.cardHolderName.notnull")
	private String cardHolderName;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "error.expirationDate.notnull")
	private Date expirationDate;

	@NotNull(message = "error.amount.notnull")
	private double amount;

	public PaymentCardDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaymentCardDetails(String pan, int securityCode, String cardHolderName, Date expirationDate, double amount) {
		super();
		this.pan = pan;
		this.securityCode = securityCode;
		this.cardHolderName = cardHolderName;
		this.expirationDate = expirationDate;
		this.amount = amount;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public int getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "PaymentCardDetails [pan=" + pan + ", securityCode=" + securityCode + ", cardHolderName="
				+ cardHolderName + ", expirationDate=" + expirationDate + ", amount=" + amount + "]";
	}

}
