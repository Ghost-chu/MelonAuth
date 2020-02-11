package moe.langua.lab.melonauth.utils;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class MojangAPI {
  private static Gson gson = new Gson();
  public static int getRenameTimes(UUID uuid){
    String response = null;
    try {
      response = HttpRequest.get(new URL(
          "https://api.mojang.com/user/profiles/" + uuid.toString()
              .replace("-", "") + "/names"))
          .execute()
          .expectResponseCode(200)
          .returnContent()
          .asString("UTF-8")
          .trim();
    } catch (IOException e) {
      e.printStackTrace();
      return -1;
    }
    UsrnameHistory[] history = gson.fromJson(response, UsrnameHistory[].class);
    return history.length;
  }
}
