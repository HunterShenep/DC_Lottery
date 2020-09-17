package com.huntershenep.DCLOTTERY;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.huntershenep.DCCORE.CoreMethods;
import com.huntershenep.ITEMS.Armor;
import com.huntershenep.ITEMS.Pickaxe;
import com.huntershenep.ITEMS.Sword;


public class Winner {
	public int id;
	public String username;
	public static int numberOfOfflineWinners;
	
	public Winner(int id, String username) {
		this.id = id;
		this.username = username;
	}
	
	
	//Add offline player
	public static void insertPlayer(String theWinner) {
		
		int amount = configManager.get().getInt(theWinner + ".amount");
		if(amount > 0) {
			//Exists already
			amount = amount+1;
			configManager.addLine(theWinner + ".amount", amount);
		}
		else {
			configManager.addLine(theWinner + ".amount", 1);
			
		}
	}
	
	//========================= Reward Player ===============================
	public static void rewardPlayer(String name) {
		Player player = Bukkit.getServer().getPlayer(name);
		
		int amt = configManager.get().getInt(name + ".amount");
		
		if(amt > 0) {
			for (int i = 1; i <= amt; i++) {
				determinePrize(player);
				//CoreMethods.sendmessage("You have been rewarded. Congratz!!", player, "Lottery");
			}
			configManager.addLine(name + ".amount", 0);
		}
		else {
			CoreMethods.sendmessage("You don't have any pending rewards", player, "Lottery");
		}
		
	}
	
	//========================= Determine Prize ===============================
	public static ItemStack determinePrize(Player player) {
		int typeOfPrize = CoreMethods.rand(1,3);
		String playerName = player.getName();
		
		Material material1 = Material.COBBLESTONE;
		ItemStack theItem = new ItemStack(material1, 1);
		
		//String message = "";
		
		if(typeOfPrize == 1) {
			
			Pickaxe theObjReward = new Pickaxe();
			player.getInventory().addItem(theObjReward.oItem);
			Methods.announceWinning(playerName, theObjReward.oType, theObjReward.oEnchant1, theObjReward.oLevel1, theObjReward.oEnchant2, theObjReward.oLevel2, theObjReward.oEnchant3, theObjReward.oLevel3, "none", 0, "none", 0);
			theObjReward = null;
			Pickaxe.resetValues();
		}
		
		if(typeOfPrize == 2) {
			//theItem = Armor.ArmorReward();
			Armor theObjReward = new Armor();
			player.getInventory().addItem(theObjReward.oItem);
			Methods.announceWinning(playerName, theObjReward.oType, theObjReward.oEnchant1, theObjReward.oLevel1, theObjReward.oEnchant2, theObjReward.oLevel2, theObjReward.oEnchant3, theObjReward.oLevel3, theObjReward.oEnchant4, theObjReward.oLevel4, theObjReward.oEnchant5, theObjReward.oLevel5);
			theObjReward = null;
			Armor.resetValues();
		}
		if(typeOfPrize == 3) {
			//theItem = Sword.SwordReward();
			Sword theObjReward = new Sword();
			player.getInventory().addItem(theObjReward.oItem);
			Methods.announceWinning(playerName, theObjReward.oType, theObjReward.oEnchant1, theObjReward.oLevel1, theObjReward.oEnchant2, theObjReward.oLevel2, theObjReward.oEnchant3, theObjReward.oLevel3, theObjReward.oEnchant4, theObjReward.oLevel4, theObjReward.oEnchant5, theObjReward.oLevel5);
			theObjReward = null;
			Sword.resetValues();
		}
		
		
		System.out.println(theItem.getEnchantments());
			
		
		//CoreMethods.pbroadcast(message, "Lottery");
		return theItem;
	}
	
}
