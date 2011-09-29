/************************************************************************
 * This file is part of AdminCmd.									
 *																		
 * AdminCmd is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by	
 * the Free Software Foundation, either version 3 of the License, or		
 * (at your option) any later version.									
 *																		
 * AdminCmd is distributed in the hope that it will be useful,	
 * but WITHOUT ANY WARRANTY; without even the implied warranty of		
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the			
 * GNU General Public License for more details.							
 *																		
 * You should have received a copy of the GNU General Public License
 * along with AdminCmd.  If not, see <http://www.gnu.org/licenses/>.
 ************************************************************************/
package VirtualChest.Manager.Permissions.Plugins;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.PermissionManager;
import VirtualChest.Manager.Permissions.AbstractPermission;

/**
 * @author Balor (aka Antoine Aflalo)
 * 
 */
public class PermissionsEx extends AbstractPermission {
	private PermissionManager PEX;

	/**
	 * 
	 */
	public PermissionsEx(PermissionManager PEX) {
		this.PEX = PEX;
		haveInfoNode = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * be.Balor.Manager.Permissions.AbstractPermission#hasPerm(org.bukkit.command
	 * .CommandSender, java.lang.String, boolean)
	 */
	@Override
	public boolean hasPerm(CommandSender player, String perm, boolean errorMsg) {
		if (!(player instanceof Player))
			return true;
		if (PEX.has((Player) player, perm))
			return true;
		else {
			if (errorMsg)
				player.sendMessage(ChatColor.RED + "You don't have the Permissions to do that "
						+ ChatColor.BLUE + "(" + perm + ")");
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * be.Balor.Manager.Permissions.AbstractPermission#hasPerm(org.bukkit.command
	 * .CommandSender, org.bukkit.permissions.Permission, boolean)
	 */
	@Override
	public boolean hasPerm(CommandSender player, Permission perm, boolean errorMsg) {
		if (!(player instanceof Player))
			return true;
		if (PEX.has((Player) player, perm.getName()))
			return true;
		else {
			if (errorMsg)
				player.sendMessage(ChatColor.RED + "You don't have the Permissions to do that "
						+ ChatColor.BLUE + "(" + perm + ")");
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * be.Balor.Manager.Permissions.AbstractPermission#getPermissionLimit(org
	 * .bukkit.entity.Player, java.lang.String)
	 */
	@Override
	public String getPermissionLimit(Player p, String limit) {
		return PEX.getUser(p).getOption("giftpost." + limit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * be.Balor.Manager.Permissions.AbstractPermission#getPrefix(java.lang.String
	 * , java.lang.String)
	 */
	@Override
	public String getPrefix(Player player) {
		String prefix = "";
		for (PermissionGroup group : PEX.getUser(player).getGroups())
			if ((prefix = group.getPrefix()) != null && !prefix.isEmpty())
				break;
		return prefix;
	}

}
