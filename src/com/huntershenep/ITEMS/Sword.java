package com.huntershenep.ITEMS;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import com.huntershenep.DCCORE.CoreMethods;

public class Sword {
	public static String theEnchant1 = "Sharpness";
	public static String theEnchant2 = "Smite";
	public static String theEnchant3 = "Fire Aspect";
	public static String theEnchant4 = "Knockback";
	public static String theEnchant5 = "Unbreaking";
	public static int level1 = 0;
	public static int level2 = 0;
	public static int level3 = 0;
	public static int level4 = 0;
	public static int level5 = 0;
	public static String type = "Diamond Sword";
	
	public String oEnchant1;
	public String oEnchant2;
	public String oEnchant3;
	public String oEnchant4;
	public String oEnchant5;
	public int oLevel1;
	public int oLevel2;
	public int oLevel3;
	public int oLevel4;
	public int oLevel5;
	public ItemStack oItem;
	public String oType;
	
	public Sword() {
		this.oItem = SwordReward();
		this.oType = type;
		this.oEnchant1 = theEnchant1;
		this.oEnchant2 = theEnchant2;
		this.oEnchant3 = theEnchant3;
		this.oEnchant4 = theEnchant4;
		this.oEnchant5 = theEnchant5;
		this.oLevel1 = level1;
		this.oLevel2 = level2;
		this.oLevel3 = level3;
		this.oLevel4 = level4;
		this.oLevel5 = level5;
		
		
	}
	public static ItemStack SwordReward() {
		Material material1 = Material.DIAMOND_SWORD;
		ItemStack theItem = new ItemStack(material1, 1);
		
		int theRand1 = CoreMethods.rand(2,5);
		theItem.addEnchantment(Enchantment.DAMAGE_ALL, theRand1);
		level1 = theRand1;
		
		if(CoreMethods.rand(1,3) == 1) {
			int theRand = CoreMethods.rand(1,5);
			theItem.addEnchantment(Enchantment.DAMAGE_UNDEAD, theRand);
			level2 = theRand;
		}
		
		if(CoreMethods.rand(1,2) == 1) {
			int theRand = CoreMethods.rand(1,2);
			theItem.addEnchantment(Enchantment.FIRE_ASPECT, theRand);
			level3 = theRand;
		}
		if(CoreMethods.rand(1,5) == 1) {
			int theRand = CoreMethods.rand(1,2);
			theItem.addEnchantment(Enchantment.KNOCKBACK, theRand);
			level4 = theRand;
			
		}
		if(CoreMethods.rand(1,2) == 1) {
			int theRand = CoreMethods.rand(1,3);
			theItem.addEnchantment(Enchantment.DURABILITY, theRand);
			level5 = theRand;
		}
		
		return theItem;
	}
	public static void resetValues() {
		level1 = 0;
		level2 = 0;
		level3 = 0;
		level4 = 0;
		level5 = 0;
	}
		
}
