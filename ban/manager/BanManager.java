package ban.manager;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import ban.user.User;
import ban.user.UserBan;

public class BanManager {

	private static BanManager instance = new BanManager();
	
	public static BanManager getInstance() { return instance; }
	
	public static Map<UUID, User> mapUsers = new HashMap<>();
	
	/** SEPARATOR **/
	
	private File pluginDir;
	private File usersDir;
	private JSONParser parser;
	
	public void setup(Plugin plugin) {
		this.pluginDir = plugin.getDataFolder();
		this.usersDir = new File(this.pluginDir, "users");
		this.parser = new JSONParser();
		
		if (!this.usersDir.exists())
			this.usersDir.mkdirs();
	}
	
	public void createBans(Player player) throws IOException {
		File userDir = new File(this.usersDir, player.getUniqueId().toString());
		File banFile = new File(userDir, "ban.json");
		
		userDir.mkdir();
		banFile.createNewFile();
		
		JSONObject banObject = new JSONObject();
		
		FileWriter fw = new FileWriter(banFile);
		fw.write(banObject.toJSONString());
		fw.flush();
		fw.close();
	}
	
	public void loadBans(UUID uniqueId, String userName) throws IOException, ParseException {
		File userDir = new File(this.usersDir, uniqueId.toString());
		File banFile = new File(userDir, "ban.json");
		
		JSONObject banObject = (JSONObject) parser.parse(new FileReader(banFile));
		
		User user;
		
		if (banObject.toJSONString().equals("{}"))
			user = new User(uniqueId, userName, null);
		else
			user = new User(uniqueId, userName, new UserBan(Boolean.valueOf(banObject.get("permanent").toString()), 
					banObject.get("reason").toString(),
					banObject.get("staffer").toString(), 
					Long.valueOf(banObject.get("end").toString())));
		
		mapUsers.put(uniqueId, user);
	}
	
	@SuppressWarnings("unchecked")
	public void saveBans(User user) throws IOException {
		File userDir = new File(this.usersDir, user.getUniqueId().toString());
		File banFile = new File(userDir, "ban.json");
		
		JSONObject banObject = new JSONObject();
		
		if (user.getBan() != null) {
			banObject.put("permanent", user.getBan().isPermanent());
			banObject.put("reason", user.getBan().getReason());
			banObject.put("staffer", user.getBan().getStaffer());
			banObject.put("reason", user.getBan().getEnd());
		}
		
		FileWriter fw = new FileWriter(banFile);
		fw.write(banObject.toJSONString());
		fw.flush();
		fw.close();
	}
}
