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
package com.Balor.bukkit.GiftPost;

import org.bukkit.ChatColor;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;

/**
 * @author Balor (aka Antoine Aflalo)
 * 
 */
public class GPPlayerListener extends PlayerListener {
	private GiftPostWorker worker;

	public GPPlayerListener(GiftPostWorker gpw) {
		worker = gpw;
	}

	public void onPlayerJoin(PlayerJoinEvent event) {
		if (!worker.getChest(event.getPlayer().getName()).isEmpty())
			event.getPlayer()
					.sendMessage(
							ChatColor.GOLD
									+ "Something wait you in your chest (command"+ChatColor.RED+" /gp c"+ChatColor.GOLD+" to see it)");
	}

}