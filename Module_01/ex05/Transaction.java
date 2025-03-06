import java.util.UUID;


public class Transaction
{
	public enum TransferCategory {
		CREDIT("OUTCOME", ""),
		DEBIT("INCOME", "+");
	
		private final String TEXT;
		private final String SIGN;
	
		TransferCategory(String text, String sign) {
			this.TEXT = text;
			this.SIGN = sign;
		}
	
		public String getText() {
			return TEXT;
		}
	
		public String getSign() {
			return SIGN;
		}
	}
	private UUID	ID;
	private User	recipient; // User type;
	private User	send; // User type;
 	private TransferCategory transferCategory; // debits ,ceredits
	private int		amount;
	
	public	Transaction(User recipient, User send, int amount, UUID id)
	{
		this.ID = id;
		this.recipient = recipient;
		this.send = send;
		if (amount > 0)
			setTransferCategory(TransferCategory.DEBIT);
		else
			setTransferCategory(TransferCategory.CREDIT);
		this.amount = amount;
	}
	public	Transaction()
	{
		this.ID = UUID.randomUUID();
	}

    // Getters
    public UUID getId()
	{
        return ID;
    }

    public User getRecipient()
	{
        return recipient;
    }

    public User getSender()
	{
        return send;
    }

	public TransferCategory getTransferCategory()
	{
		return transferCategory;
	}

	public int getAmount()
	{
		return amount;
	}

	// Setters
	public void setAmount(int amount)
	{
		this.amount = amount;
	}

    public void setRecipient(User recipient)
	{
		this.recipient = recipient;
    }

    public void setSend(User send)
	{
        this.send = send;
    }

	public void setTransferCategory(TransferCategory transferCategory)
	{
		this.transferCategory = transferCategory;
	}

	@Override
	public String toString() {
		return (String.format("%s -> %s, %s%s, %s, %s", send.getName(), recipient.getName(), transferCategory.getSign(),
				amount, transferCategory.getText(), ID));
	}
}
