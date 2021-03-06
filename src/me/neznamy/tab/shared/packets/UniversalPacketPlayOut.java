package me.neznamy.tab.shared.packets;

import me.neznamy.tab.platforms.bukkit.packets.PacketPlayOut;
import me.neznamy.tab.shared.ProtocolVersion;
import me.neznamy.tab.shared.Shared;
import me.neznamy.tab.shared.placeholders.Placeholders;

public abstract class UniversalPacketPlayOut extends PacketPlayOut{

	public abstract Object toBungee(ProtocolVersion clientVersion);
	public abstract Object toVelocity(ProtocolVersion clientVersion);
	
	public String cutTo(String string, int length) {
		if (string == null || string.length() <= length) return string;
		if (string.charAt(length-1) == Placeholders.colorChar) {
			return string.substring(0, length-1); //cutting one extra character to prevent prefix ending with "&"
		} else {
			return string.substring(0, length);
		}
	}
	public Object build(ProtocolVersion clientVersion) {
		try {
			return Shared.platform.buildPacket(this, clientVersion);
		} catch (Exception e) {
			Shared.errorManager.printError("An error occurred when creating " + getClass().getSimpleName(), e);
			return null;
		}
	}
}