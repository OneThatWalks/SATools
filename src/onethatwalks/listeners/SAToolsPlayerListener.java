/*
 * This file is part of SATools, which is licensed under the GNU GPL.
 * 
 * Any use or modification of this file will be in compliance of the
 * GNU GPL v3 or later version(s). Unauthorized distribution or 
 * distributed as "pay-ware" will be handled in the fullest extent
 * of the violators law.
 * 
 * If you have questions contact me at
 * OneThatWalks@live.com.
 */
package onethatwalks.listeners;

import onethatwalks.satools.SATools;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;

public final class SAToolsPlayerListener extends PlayerListener {

	private final SATools p;

	public SAToolsPlayerListener(SATools instance) {
		p = instance;
	}

	@Override
	public void onPlayerJoin(PlayerJoinEvent pje) {
		p.gui.comboBox_SpawnLocation.addItem(pje.getPlayer().getDisplayName());
		p.gui.addPlayerToMenu(pje.getPlayer());
		p.gui.playerList.add(pje.getPlayer());
	}

}
