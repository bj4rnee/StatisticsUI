package dev.bjarne.statisticsUI;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class eventHandler implements Listener{
	public static main Main;
	
	public eventHandler() {
		
	}
	
	public void setMain(main Main){
		eventHandler.Main = Main;
		Main.eH = this;
		
		
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		
		//Player player1 = (Player) event.getWhoClicked();
		
		if(event.getView().getTitle().contains("Statistics of")==false) {
			
			//player1.sendMessage("bruh"); //debugging is fun haha
			return;
		}
		event.setCancelled(true);
		//player1.sendMessage("bru2");
		return;
	}
}
