package me.neznamy.tab.platforms.bukkit.features;

import org.bukkit.entity.Player;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.neznamy.tab.api.EnumProperty;
import me.neznamy.tab.platforms.bukkit.Main;
import me.neznamy.tab.shared.ITabPlayer;
import me.neznamy.tab.shared.Shared;

public class TabExpansion extends PlaceholderExpansion {

	public TabExpansion() {
		register();
	}
	
	@Override
	public boolean persist(){
		return true;
	}

	@Override
	public boolean canRegister(){
		return true;
	}

	@Override
	public String getAuthor(){
		return Main.instance.getDescription().getAuthors().toString();
	}

	@Override
	public String getIdentifier(){
		return "tab";
	}

	@Override
	public String getVersion(){
		return Main.instance.getDescription().getVersion();
	}

	@Override
	public String onPlaceholderRequest(Player player, String identifier){
		if (player == null) return "";
		ITabPlayer p = Shared.getPlayer(player.getUniqueId());
		if (p == null) return "";
		for (EnumProperty property : EnumProperty.values()) {
			if (identifier.equals(property.toString())) {
				return p.properties.get(property.toString()).lastReplacedValue;
			}
			if (identifier.equals(property.toString() + "_raw")) {
				return p.properties.get(property.toString()).getCurrentRawValue();
			}
		}
		if (identifier.equals("scoreboard_visible")) {
			return p.hiddenScoreboard ? "Disabled" : "Enabled";
		}
		if (identifier.equals("bossbar_visible")) {
			return p.bossbarVisible ? "Enabled" : "Disabled";
		}
		return null;
	}
}