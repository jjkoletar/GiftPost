/*This file is part of GiftPost .

    GiftPost is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    GiftPost is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with GiftPost.  If not, see <http://www.gnu.org/licenses/>.*/
package com.aranai.virtualchest;

import net.minecraft.server.EntityPlayer;
import net.minecraft.server.ItemStack;

import org.bukkit.entity.Player;
import org.bukkit.craftbukkit.entity.CraftPlayer;

/**
 * VirtualChest for Bukkit
 *
 * @authors Timberjaw and Balor
 * 
 */
public class VirtualChest
{

    protected TileEntityVirtualChest chest;

    /**
     * Constructor
     * @param player
     */
    public VirtualChest(Player player)
    {
        // Large chests are made up of two individual small chests
        // TileEntityVirtualChest extends the TileEntityChest class to remove some bothersome world checks
        // This would NOT work with regular TileEntityChest instances
        chest = new TileEntityVirtualChest();
        //chest2 = new TileEntityVirtualChest();

        // Set up the global chest
        // Note: this is NOT persisted across server restarts
        chest.setName(player.getName());
        //lc = new InventoryLargeChest(owner.getName(), chest, chest2);

    }

    /**
     * Check if the chest is belong to the player
     * @param player
     * @return
     */
    public boolean isBelongTo(Player player)
    {
        return chest.getName().equals(player.getName());
    }

    /**
     * Open the chest for the owner
     */
    public void openChest(Player p)
    {
        if (isBelongTo(p))
        {
            EntityPlayer eh = ((CraftPlayer) p).getHandle();
            eh.a(chest);
        } else
            p.sendMessage("You can't open this chest, it's not yours.");
    }

    /**
     * adding a ItemStack to the chest
     * @param is
     * @return
     */
    public boolean addItemStack(ItemStack is)
    {
        return chest.addItemStack(is);
    }

    /**
     * Empty chest
     */
    public void emptyChest()
    {
        chest.emptyChest();
    }

    /**
     * get all the itemStacks that compose the chest
     * @return
     */
    public ItemStack[] getContents()
    {
        return chest.getContents();
    }

    /**
     * Search for a given itemStack and remove it.
     * @param is
     */
    public boolean removeItemStack(ItemStack is)
    {
        for (int i = 0; i < this.getContents().length; i++)
            if (this.getContents()[i].equals(is))
            {
                chest.removeItemStack(i);
                return true;
            }
        return false;
    }
    public void removeItemStack(int i)
    {
        chest.removeItemStack(i);
    }
}