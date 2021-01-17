package VuelingServices;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import data.Flight;
import data.FlightAssambler;
import data.FlightDTO;

public class VuelingService extends UnicastRemoteObject  implements IVuelingService{

	private static final long serialVersionUID = 1L;
	//private FlightAssambler flightAssambler; 
	
	private List<Flight> defaultFlights = new ArrayList<>();

	//private List<Flight> selectedFlights;
	//private List<FlightDTO> selectedFlightsDTO;
	
	public List<Integer> selectedFlights; 
	
	Flight f1 = new Flight(2221, "13-02-2020", "13-02-2020", "13:00", "11:30", "Madrid", "Roma", "Vueling");
	Flight f2 = new Flight(2222, "13-02-2020", "13-02-2020", "16:30", "14:00", "Roma", "Madrid", "Vueling");
	
	
	public VuelingService() throws RemoteException{
		defaultFlights.add(f1); 
		defaultFlights.add(f2); 
		
	}
	
	@Override
	public List<Integer> getFlights(String depAirport, String arrivalAirport, String depDate)
			throws RemoteException {

		selectedFlights = new ArrayList<>(); 
	 
		
		for (Flight dflight : defaultFlights) {
			
			if (dflight.getArrivalAirport().compareTo(arrivalAirport) == 0 && dflight.getDepAirport().compareTo(depAirport) == 0 && dflight.getDepDate().compareTo(depDate)== 0) {
				
				selectedFlights.add(dflight.getFlightID()); 	
			}	
		}
		
		
		return selectedFlights;
	}
	
	

	/*
	@Override
	public List<FlightDTO> getFlights(String depAirport, String arrivalAirport, String depDate)
			throws RemoteException {

		selectedFlights = new ArrayList<>(); 
		selectedFlightsDTO = new ArrayList<>(); 
		
		for (Flight dflight : defaultFlights) {
			
			if (dflight.getArrivalAirport().compareTo(arrivalAirport) == 0 && dflight.getDepAirport().compareTo(depAirport) == 0 && dflight.getDepDate().compareTo(depDate)== 0) {
				
				selectedFlights.add(dflight); 	
			}	
		}
		
		selectedFlightsDTO = flightAssambler.assemble(selectedFlights);
		
		return selectedFlightsDTO;
	}
	
	*/
	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("usage: java [policy] [codebase] server.Server [host] [port] [server]");
			System.exit(0);
		}

		
		
		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {	
			IVuelingService vueling = new VuelingService();
			Naming.rebind(name, vueling);
			System.out.println("* Server '" + name + "' active and waiting...");
			
			
		} catch (Exception e) {
			System.err.println("- Exception running the server: " + e.getMessage());
			e.printStackTrace();
		}
	}
	

}
