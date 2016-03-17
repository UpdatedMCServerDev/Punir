package ban.user;

import java.util.UUID;

public class User {

	private UUID uniqueId;
	private String userName;
	private UserBan ban;
	
	public User(UUID uniqueId, String userName, UserBan ban) {
		super();
		this.uniqueId = uniqueId;
		this.userName = userName;
		this.ban = ban;
	}

	public UUID getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(UUID uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UserBan getBan() {
		return ban;
	}

	public void setBan(UserBan ban) {
		this.ban = ban;
	}

	@Override
	public String toString() {
		return "User [uniqueId=" + uniqueId + ", userName=" + userName + ", ban=" + ban + "]";
	}
}
