package app.model.transferData;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class PaymentCardInfo {

	@NotNull(message = "error.pan.notnull")
	@Size(min = 10, max = 10, message = "error.pan.size")
	private String pan;

	@NotNull(message = "error.securityCode.notnull")
	private Integer securityCode;

	@NotNull(message = "error.cardHolderName.notnull")
	private String holderName;

	@DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
	@NotNull(message = "error.expirationDate.notnull")
	private Date expirationDate;


	public PaymentCardInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaymentCardInfo(String pan, Integer securityCode, String cardHolderName, Date expirationDate) {
		super();
		this.pan = pan;
		this.securityCode = securityCode;
		this.holderName = cardHolderName;
		this.expirationDate = expirationDate;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public Integer getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(Integer securityCode) {
		this.securityCode = securityCode;
	}

	public String getCardHolderName() {
		return holderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.holderName = cardHolderName;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	
	@Override
	public String toString() {
		return "PaymentCardDetails [pan=" + pan + ", securityCode=" + securityCode + ", cardHolderName="
				+ holderName + ", expirationDate=" + expirationDate+"]";
	}

}
