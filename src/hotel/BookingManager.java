package hotel;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.*;

public class BookingManager implements BookingManagerInterface {

	private Room[] rooms;

	public BookingManager() {
		this.rooms = initializeRooms();
	}

	public Set<Integer> getAllRooms() {
		Set<Integer> allRooms = new HashSet<Integer>();
		Iterable<Room> roomIterator = Arrays.asList(rooms);
		for (Room room : roomIterator) {
			allRooms.add(room.getRoomNumber());
		}
		return allRooms;
	}

	public boolean isRoomAvailable(Integer roomNumber, LocalDate date) {
		//implement this method
		Room room = Arrays.stream(rooms).filter(room1 -> room1.getRoomNumber().equals(roomNumber)).toList().stream().findFirst().get();
		for (BookingDetail bookingDetail: room.getBookings())
			if (bookingDetail != null)
				if (bookingDetail.getDate().equals(date) && bookingDetail.getRoomNumber().equals(roomNumber))
					return false;
		return true;
	}

	public void addBooking(BookingDetail bookingDetail) throws RemoteException {
		//implement this method
		Room room = Arrays.stream(rooms).filter(room1 -> room1.getRoomNumber().equals(bookingDetail.getRoomNumber())).toList().stream().findFirst().get();
		if (isRoomAvailable(bookingDetail.getRoomNumber(), bookingDetail.getDate()))
			room.addBooking(bookingDetail);
		else
			throw new RemoteException("The room is not available for this date");
	}

	public Set<Integer> getAvailableRooms(LocalDate date) {
		//implement this method
		Set<Integer> rooms = new HashSet<>();
		for (Room room: this.rooms)
			if (isRoomAvailable(room.getRoomNumber(), date))
				rooms.add(room.getRoomNumber());
		return rooms;
	}

	private static Room[] initializeRooms() {
		Room[] rooms = new Room[4];
		rooms[0] = new Room(101);
		rooms[1] = new Room(102);
		rooms[2] = new Room(201);
		rooms[3] = new Room(203);
		return rooms;
	}
}
