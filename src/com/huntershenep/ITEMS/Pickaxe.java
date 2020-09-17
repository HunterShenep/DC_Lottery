package com.huntershenep.ITEMS;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import com.huntershenep.DCCORE.CoreMethods;

public class Pickaxe {
	public static String theEnchant1 = "Efficiency";
	public static String theEnchant2 = "Fortune";
	public static String theEnchant3 = "Unbreaking";
	public static int level1 = 0;
	public static int level2 = 0;
	public static int level3 = 0;
	public static String type = "Diamond Pickaxe";
	
	public String oEnchant1;
	public String oEnchant2;
	public String oEnchant3;
	public int oLevel1;
	public int oLevel2;
	public int oLevel3;
	public ItemStack oItem;
	public String oType;
	
	public Pickaxe() {
		this.oItem = PickaxeReward();

		this.oEnchant1 = theEnchant1;
		this.oEnchant2 = theEnchant2;
		this.oEnchant3 = theEnchant3;
		this.oLevel1 = level1;
		this.oLevel2 = level2;
		this.oLevel3 = level3;
		
		this.oType = type;
	}
	
	public static ItemStack PickaxeReward() {
		Material material1 = Material.DIAMOND_PICKAXE;
		ItemStack theItem = new ItemStack(material1, 1);
		
		
		
		int rand1 = CoreMethods.rand(3,5);
		theItem.addEnchantment(Enchantment.DIG_SPEED, rand1);
		
		level1 = rand1;
		
		
		int rand2 = CoreMethods.rand(1,2);
		if(rand2 == 1) {
			theItem.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 1);

			level2 = 1;
		}
		
	
		
			int theRand = CoreMethods.rand(1,3);
			theItem.addEnchantment(Enchantment.DURABILITY, theRand);
			level3 = theRand;
		
		
		return theItem;
	}
	
	public static void resetValues() {
		level1 = 0;
		level2 = 0;
		level3 = 0;
	}
	
}
