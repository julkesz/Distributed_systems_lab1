package hotel;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class BookingManager implements BookingManagerInterface {

	private Room[] rooms;
	private BookingDetail[] bookings;

	public BookingManager() {
		this.rooms = initializeRooms();
		this.bookings = new BookingDetail[100];
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
		for (BookingDetail bookingDetail: bookings)
			if (bookingDetail.getDate().equals(date) && bookingDetail.getRoomNumber().equals(roomNumber))
				return false;
		return true;
//		return bookings.stream().filter(bookingDetail -> bookingDetail.getRoomNumber().equals(roomNumber) && bookingDetail.getDate().equals(date)).toList().isEmpty();
	}

	public void addBooking(BookingDetail bookingDetail) throws Exception {
		//implement this method
		if (isRoomAvailable(bookingDetail.getRoomNumber(), bookingDetail.getDate()))
			bookings[bookings.length - 1] = bookingDetail;
		else
			throw new Exception("The room is not available for this date");
	}

	public Set<Integer> getAvailableRooms(LocalDate date) {
		//implement this method
		return null;
//		return bookings.stream().filter(bookingDetail -> bookingDetail.getDate().equals(date)).map(BookingDetail::getRoomNumber).collect(Collectors.toSet());
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
