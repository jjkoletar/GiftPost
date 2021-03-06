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

import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import VirtualChest.Manager.Permissions.NoPermissionsPlugin;

import com.nijiko.permissions.PermissionHandler;

/**
 * @author Balor (aka Antoine Aflalo)
 * 
 */
public class YetiPermissions implements IPermissionPlugin {
	protected PermissionHandler permission = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * be.Balor.Manager.Permissions.AbstractPermission#hasPerm(org.bukkit.command
	 * .CommandSender, java.lang.String, boolean)
	 */
	/**
	 *
	 */
	public YetiPermissions(PermissionHandler perm) {
		this.permission = perm;
	}

	/**
	 * @return the permission
	 */
	public PermissionHandler getPermission() {
		return permission;
	}


	public boolean hasPerm(CommandSender player, String perm, boolean errorMsg) {
		if (!(player instanceof Player))
			return true;
		if (permission.has((Player) player, perm)) {
			return true;
		} else {
			if (errorMsg)
				player.sendMessage(ChatColor.RED + "You don't have the permission to do that "
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

	public boolean hasPerm(CommandSender player, Permission perm, boolean errorMsg) {
		if (!(player instanceof Player))
			return true;
		if (permission.has((Player) player, perm.getName())) {
			return true;
		} else {
			if (errorMsg)
				player.sendMessage(ChatColor.RED + "You don't have the permission to do that "
						+ ChatColor.BLUE + "(" + perm.getName() + ")");
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * be.Balor.Manager.Permissions.AbstractPermission#isInGroup(org.java.lang
	 * .String, org.bukkit.entity.Player)
	 */

	public boolean isInGroup(String groupName, Player player) {
		return permission.inGroup(player.getWorld().getName(), player.getName(), groupName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * be.Balor.Manager.Permissions.AbstractPermission#getUsers(org.java.lang
	 * .String)
	 */

	public Set<Player> getUsers(String groupName) throws NoPermissionsPlugin {
		throw new NoPermissionsPlugin(
				"To use this functionality you need a newer Permissions plugin!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * be.Balor.Manager.Permissions.AbstractPermission#getPermissionLimit(org
	 * .bukkit.entity.Player, java.lang.String)
	 */
	@SuppressWarnings("deprecation")

	public String getPermissionLimit(Player p, String type) {
		Integer limitInteger = null;
		try {
			limitInteger = permission.getInfoInteger(p.getWorld().getName(), p.getName(),
					"admincmd." + type, false);
		} catch (NoSuchMethodError e) {
			try {

				limitInteger = permission.getPermissionInteger(p.getWorld().getName(), p.getName(),
						"admincmd." + type);
			} catch (Throwable e2) {
				limitInteger = null;
			}
		}
		if (limitInteger != null && limitInteger != -1)
			return limitInteger.toString();
		else
			return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * be.Balor.Manager.Permissions.AbstractPermission#getPrefix(java.lang.String
	 * , java.lang.String)
	 */
	@SuppressWarnings("deprecation")

	public String getPrefix(Player player) {
		String world = player.getWorld().getName();
		String pName = player.getName();
		String prefixstring = null;
		try {
			prefixstring = permission.safeGetUser(world, pName).getPrefix();
		} catch (Exception e) {
			String group = permission.getGroup(world, pName);
			prefixstring = permission.getGroupPrefix(world, group);
		} catch (NoSuchMethodError e) {
			String group = permission.getGroup(world, pName);
			prefixstring = permission.getGroupPrefix(world, group);
		}
		return prefixstring;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * be.Balor.Manager.Permissions.IPermissionPlugin#getSuffix(org.bukkit.entity
	 * .Player)
	 */

	@SuppressWarnings("deprecation")
	public String getSuffix(Player player) {
		String world = player.getWorld().getName();
		String pName = player.getName();
		String prefixstring = null;
		try {
			prefixstring = permission.safeGetUser(world, pName).getSuffix();
		} catch (Exception e) {
			String group = permission.getGroup(world, pName);
			prefixstring = permission.getGroupSuffix(world, group);
		} catch (NoSuchMethodError e) {
			String group = permission.getGroup(world, pName);
			prefixstring = permission.getGroupSuffix(world, group);
		}
		return prefixstring;
	}

}
