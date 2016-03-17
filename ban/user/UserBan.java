package ban.user;

public class UserBan {

	private boolean permanent;
	private String reason;
	private String staffer;
	private long end;
	
	public UserBan(boolean permanent, String reason, String staffer, long end) {
		super();
		this.permanent = permanent;
		this.reason = reason;
		this.staffer = staffer;
		this.end = end;
	}

	public boolean isPermanent() {
		return permanent;
	}

	public void setPermanent(boolean permanent) {
		this.permanent = permanent;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStaffer() {
		return staffer;
	}

	public void setStaffer(String staffer) {
		this.staffer = staffer;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "UserBan [permanent=" + permanent + ", reason=" + reason + ", staffer=" + staffer + ", end=" + end + "]";
	}
}
