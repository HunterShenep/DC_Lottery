package com.huntershenep.DCLOTTERY;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import com.huntershenep.DCCORE.CoreMethods;
import com.huntershenep.DCCORE.TitleAPI;

public class Methods {
	public static int drawInterval;
	public static int ticketPrice;
	public static int timeLeft;
	public static int efficientTicketPrice;
	public static int efficientDrawInterval;
	//public static ArrayList<OfflineWinners> theOfflineWinners = getOfflineWinners();  -- This doesn't help anything. Look into later.
	
	public static int getDrawInterval() {
		int di = configManager.get().getInt("DrawInterval");
		drawInterval = di;
		return di;
	}
	public static void getTicketPrice() {
		int di = configManager.get().getInt("TicketPrice");
		ticketPrice = di;
	}
	public static void resetTimeLeft() {
		timeLeft = getDrawInterval() * 1200;
	}
	public static void setEffDrawInterval() {
		efficientDrawInterval = drawInterval;
	}
	public static void setEffTicketPrice() {
		efficientTicketPrice = ticketPrice;
	}
	

	public static void enterLottery(Player player) {
		try {
			//Check if they have enough, and if so, give them the ticket.
			if((Economy.hasEnough(player.getName(), ticketPrice) == true)){
				player.sendMessage("You have purchased 1 ticket for " + ticketPrice);
				Economy.subtract(player.getName(), ticketPrice);
				Ticket.newTicket(player.getName());
				
			}
			else {
				CoreMethods.sendmessage("You don't have enough money for that :s", player, "Lottery");
			}
		} catch (UserDoesNotExistException | NoLoanPermittedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	public static int drawWinner() {
		if(Ticket.theTickets.size() > 0) {
			int winningNumber = CoreMethods.rand(0, Ticket.theTickets.size()-1);
			int winningNumber2 = CoreMethods.rand(0, Ticket.theTickets.size()-1);
			String theWinner = Ticket.theTickets.get(winningNumber).username;
			String theWinnerCash = Ticket.theTickets.get(winningNumber2).username;
			double cashPrice = (Ticket.theTickets.size() * ticketPrice) * 0.75;
			
			CoreMethods.log("Ticket #" + winningNumber + " wins, congrats, " + theWinner);
			CoreMethods.pbroadcast("Ticket #" + winningNumber+1 + " wins the item, congrats, " + theWinner, "Lottery");
			CoreMethods.pbroadcast("Ticket #" + winningNumber2+1 + " wins the cash pot of $" + (int) cashPrice + ", congrats, " + theWinnerCash, "Lottery");
			
			for(Player e : Bukkit.getServer().getOnlinePlayers()) {
				TitleAPI.sendTitle(e, 5, 5, 5, CoreMethods.chatColorNoTag("&d" + theWinner), "WON THE LOTTERY!");
			}
			
				try {
					Economy.add(theWinnerCash, cashPrice);
				} catch (NoLoanPermittedException | UserDoesNotExistException e1) {}
				
		    Winner.insertPlayer(theWinner);
			Ticket.theTickets.clear();
			return winningNumber;

		}
		else {
			CoreMethods.pbroadcast("No tickets purchased this round. No winner.", "Lottery");
			CoreMethods.log("No lottery winners this round");
		}
		return 0;
	}
	
	

	
	public static int getTicketAmount(Player player) {
		int amt = 0;
		
		for(int i=0; i < Ticket.theTickets.size(); i++) {
			if(Ticket.theTickets.get(i).username.equalsIgnoreCase(player.getName())) {
				amt++;
			}
		}
		
		return amt;
	}
	
	

	public static void announceWinning(String name, String type, String enchant1, int level1, String enchant2, int level2, String enchant3, int level3, String enchant4, int level4, String enchant5, int level5) {
		String message = (name + " has claimed a&b " + type + "&e ");
		if(level1 > 0) {
			message+= (enchant1 + " " + level1 + ", ");
		}
		if(level2 > 0) {
			message+= (enchant2 + " " + level2 + ", ");
		}
		if(level3 > 0) {
			message+= (enchant3 + " " + level3 + ", ");
		}
		if(level4 > 0) {
			message+= (enchant4 + " " + level4 + ", ");
		}
		if(level5 > 0) {
			message+= (enchant5 + " " + level5 + ", ");
		}
		message+= "&ffrom the lottery.";
		CoreMethods.pbroadcast(message, "Lottery");

		/***
		CoreMethods.broadcast(enchant1 + level1);
		CoreMethods.broadcast(enchant2 + level2);
		CoreMethods.broadcast(enchant3 + level3);
		CoreMethods.broadcast(enchant4 + level4);
		CoreMethods.broadcast(enchant5 + level5);
		CoreMethods.broadcast("-----");
		***/
	}



	
}
