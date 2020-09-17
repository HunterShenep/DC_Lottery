package com.huntershenep.DCLOTTERY;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.huntershenep.DCCORE.CoreMethods;

public class Commands implements CommandExecutor {
    private final Main plugin;

    public Commands(Main plugin) {
        this.plugin = plugin;
    }

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		Player player = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("lottery")) {
			
			if(args.length == 0) {
				player.sendMessage(CoreMethods.chatColorNoTag("&8===================== &bDC LOTTERY &8====================="));
				player.sendMessage(CoreMethods.chatColorNoTag(""));
				player.sendMessage(CoreMethods.chatColorNoTag("Our lottery costs $" + Methods.efficientTicketPrice + " per ticket and draws every " + Methods.efficientDrawInterval + " minutes."
						+ " If the server reboots before the lottery is over, the lottery will draw right at the time of the reboot. We do not allow you to see how many tickets other players have purchased. "
						+ "You can be offline to win. If you win, and log in 10 days later, your prize(s) will still await you. Prize isn't announced until user claims reward."));
				player.sendMessage(" ");
				player.sendMessage(CoreMethods.chatColorNoTag("&a/lottery enter &for&a buy &fpurchases a ticket. Multiples allowed. Example: /lottery buy 10 (Maximum of 25 per command)"));
				player.sendMessage(CoreMethods.chatColorNoTag("&a/lottery tickets &fchecks to see how many tickets you have."));
				player.sendMessage(CoreMethods.chatColorNoTag("&a/lottery claim &fclaims prize(s) after winning lottery."));
				player.sendMessage(CoreMethods.chatColorNoTag("&a/lottery time &for&a timeleft &fchecks the timeleft before next draw."));
				player.sendMessage(CoreMethods.chatColorNoTag(""));
				player.sendMessage(CoreMethods.chatColorNoTag("&8===================== &bDC LOTTERY &8====================="));

				
				
			}
			
			if(args.length == 1) {
				
				//Purchase a ticket
				if(args[0].equalsIgnoreCase("enter") || args[0].equalsIgnoreCase("buy")) {
					Methods.enterLottery(player);
				}
				
				//Manually draw winner
				if(args[0].equalsIgnoreCase("draw")) {
					if(player.hasPermission("diamcraft.admin")) {
						Methods.drawWinner();
						Methods.resetTimeLeft();
					}	
				}
				
				//REMOVE ME ================3243243242342=========================343243242343223423432423================================== 299239 299239
				if(args[0].equalsIgnoreCase("test")) {

				}
				
				//Check to see how many tickets you have.
				if(args[0].equalsIgnoreCase("tickets")) {
					int amt = Methods.getTicketAmount(player);
					CoreMethods.sendmessage("You have " + amt + " tickets for this lottery pool.", player, "Lottery");
				}
				
				//Claim offline rewards
				if(args[0].equalsIgnoreCase("claim")) {
					Winner.rewardPlayer(player.getName());
				}
				
				//Check time left
				if(args[0].equalsIgnoreCase("time") || args[0].equalsIgnoreCase("timeleft")) {
					int minuteLeft = Methods.timeLeft / 1200;
					int b = (Methods.timeLeft / 20);
					int secondsLeft = (b % 60);

					//player.sendMessage(Integer.toString(minuteLeft) + " minutes left" + " " + secondsLeft + " seconds left");
					CoreMethods.sendmessage((Integer.toString(minuteLeft) + " minute(s) and" + " " + secondsLeft + " second(s) left"), player, "Lottery");
				}
				
			}
			if(args.length == 2) {
				if(args[0].equalsIgnoreCase("enter") || args[0].equalsIgnoreCase("buy")) {
					
					int amt = Integer.parseInt(args[1]);
					if (amt > 25) {
						CoreMethods.sendmessage("Please enter a value between 1-25", player, "Lottery");
					}
					else {
						for(int i = 0; i < amt; i++) {
							if(amt > 0) {
								Methods.enterLottery(player);
							}
						}
					}
					
				}
			}
			
			
		}
		return true;
	}
	
}
