/************************************************************************
 * This file is part of GiftPost.									
 *																		
 * GiftPost is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by	
 * the Free Software Foundation, either version 3 of the License, or		
 * (at your option) any later version.									
 *																		
 * GiftPost is distributed in the hope that it will be useful,	
 * but WITHOUT ANY WARRANTY; without even the implied warranty of		
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the			
 * GNU General Public License for more details.							
 *																		
 * You should have received a copy of the GNU General Public License
 * along with GiftPost.  If not, see <http://www.gnu.org/licenses/>.
 ************************************************************************/
package com.Balor.commands;

import static com.Balor.utils.Display.chestKeeper;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Balor.bukkit.GiftPost.GiftPostWorker;

/**
 * @author Balor (aka Antoine Aflalo)
 * 
 */
public class EmptyChest implements GPCommand {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.Balor.commands.GPCommand#execute(com.Balor.bukkit.GiftPost.GiftPostWorker
	 * , org.bukkit.command.CommandSender, java.lang.String[])
	 */
	public void execute(GiftPostWorker gpw, CommandSender sender, String[] args) {
		if (args.length > 1 && gpw.hasPerm((Player) sender, "giftpost.admin.empty")) {
			gpw.getDefaultChest(args[1]).emptyChest();
		} else {
			Player p = (Player) sender;
			gpw.getDefaultChest(p.getName()).emptyChest();
		}
		sender.sendMessage(chestKeeper() + ChatColor.GREEN + "Chest emptied succefuly");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.Balor.commands.GPCommand#validate(com.Balor.bukkit.GiftPost.
	 * GiftPostWorker, org.bukkit.command.CommandSender, java.lang.String[])
	 */
	public boolean validate(GiftPostWorker gpw, CommandSender sender, String[] args) {
		return (gpw.hasFlag(args, "e") || gpw.hasFlag(args, "empty"))
				&& (gpw.hasPerm((Player) sender, getPermName()) || gpw.hasPerm((Player) sender,
						"giftpost.admin.empty"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.Balor.commands.GPCommand#getPermName()
	 */
	public String getPermName() {
		return "giftpost.chest.empty";
	}

	public String getHelp() {
		return ChatColor.GOLD + "/gp e" + ChatColor.WHITE + ": empty your default chest.";
	}

}
