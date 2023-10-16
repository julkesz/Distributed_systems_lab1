package hotel;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class BookingServer {
    public static void main(String[] args) {
        try {
            System.setSecurityManager(null);
            BookingManagerInterface bookingManager = new BookingManager();
            Registry registry = LocateRegistry.getRegistry();
            BookingManagerInterface stub = (BookingManagerInterface) UnicastRemoteObject.exportObject(bookingManager, 0);
            registry.rebind("Booking manager", stub);
        } catch (RemoteException remoteException) {
            remoteException.printStackTrace();
        }
    }
}
