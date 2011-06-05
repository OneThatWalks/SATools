package onethatwalks.satools;

import org.bukkit.event.block.BlockListener;

/**
 * Handle events for all Block related events
 * 
 * @author OneThatWalks
 */
public class SAToolsBlockListener extends BlockListener {
	@SuppressWarnings("unused")
	private final SATools plugin;

	public SAToolsBlockListener(SATools instance) {
		plugin = instance;
	}

}
