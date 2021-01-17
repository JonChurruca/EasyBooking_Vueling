package VuelingServices;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import data.FlightDTO;



public interface IVuelingService extends Remote {
    //List<FlightDTO> getFlights(String depAirport, String arrivalAirport, String depDate) throws RemoteException;    
    List<Integer> getFlights(String depAirport, String arrivalAirport, String depDate) throws RemoteException;    


}
