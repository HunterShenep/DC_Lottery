package com.huntershenep.DCLOTTERY;

import java.util.ArrayList;







public class Ticket {
	public int id;
	public String username;
	public static int ticketCount = 1;
	public static ArrayList<Ticket> theTickets = new ArrayList<Ticket>();

	
	public Ticket() {
		System.out.println("Error with tickets");
	}
	
	public Ticket(String username) {
		this.id = ticketCount;
		this.username = username;
		ticketCount++;
	}
	
	public static void newTicket(String username) {
		Ticket aTicket = new Ticket(username);
		theTickets.add(aTicket);
	}
	
	
}
