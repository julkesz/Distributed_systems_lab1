package staff;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;

import hotel.BookingDetail;
import hotel.BookingManager;
import hotel.BookingManagerInterface;

public class BookingClient extends AbstractScriptedSimpleTest {

	private BookingManagerInterface bm = null;

	public static void main(String[] args) throws Exception {
		BookingClient client = new BookingClient();
		client.run();
	}

	/***************
	 * CONSTRUCTOR *
	 ***************/
	public BookingClient() {
		try {
			//Look up the registered remote instance
			System.setSecurityManager(null);
			Registry registry = LocateRegistry.getRegistry();
			bm = (BookingManagerInterface) registry.lookup("Booking manager");
			System.out.println(bm);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	@Override
	public boolean isRoomAvailable(Integer roomNumber, LocalDate date) throws RemoteException {
		//Implement this method
		return bm.isRoomAvailable(roomNumber, date);
	}

	@Override
	public void addBooking(BookingDetail bookingDetail) {
		//Implement this method
		try {
			bm.addBooking(bookingDetail);
		} catch (Exception exception) {
			System.out.println(Arrays.toString(exception.getStackTrace()));
		}
	}

	@Override
	public Set<Integer> getAvailableRooms(LocalDate date) throws RemoteException {
		//Implement this method
		return bm.getAvailableRooms(date);
	}

	@Override
	public Set<Integer> getAllRooms() throws RemoteException {
		return bm.getAllRooms();
	}
}
