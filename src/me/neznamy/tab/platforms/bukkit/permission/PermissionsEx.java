package me.neznamy.tab.platforms.bukkit.permission;

import me.neznamy.tab.shared.ITabPlayer;
import me.neznamy.tab.shared.permission.PermissionPlugin;

@SuppressWarnings("deprecation")
public class PermissionsEx implements PermissionPlugin {

	@Override
	public String getPrimaryGroup(ITabPlayer p) {
		String[] groups = getAllGroups(p);
		if (groups.length == 0) return "null";
		return groups[0];
	}

	@Override
	public String[] getAllGroups(ITabPlayer p) {
		return ru.tehkode.permissions.bukkit.PermissionsEx.getUser(p.getName()).getGroupNames();
	}
}