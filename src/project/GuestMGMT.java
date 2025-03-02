package project;

import java.util.Scanner;

public class GuestMGMT {
    static Scanner ofs = new Scanner(System.in);

    // Arrays to store room data
    int[] roomNumbers = new int[51]; // assuming 10 rooms for simplicity
    String[] roomTypes = new String[51];
    boolean[] isReserved = new boolean[51]; // to track room availability
    String[] guestNames = new String[51];
    String[] guestPhones = new String[51];
    String[] guestIDs = new String[51];
    double[] roomPrices = new double[51]; // Array to store room prices

    // Method to add a room
    public void add() {
        int roomNumber;

        // Loop until the user enters a valid room number
        while (true) {
            System.out.println("Enter room number (1-50):");
            roomNumber = ofs.nextInt();
            ofs.nextLine(); // Consume the newline character

            // Check if the room number is valid
            if (roomNumber >= 1 && roomNumber <= 50) {
                // Check if the room already exists
                for (int i = 0; i < roomNumbers.length; i++) {
                    if (roomNumbers[i] == roomNumber) {
                        System.out.println("Room number " + roomNumber + " already exists!");
                        return;
                    }
                }
                break; // Exit the loop if the number is valid and room doesn't exist
            }
            System.out.println("Invalid room number! Please enter a number between 1 and 50.");
        }

        System.out.println("Enter room type (Family, Single, Double, Luxury):");
        String roomType = ofs.nextLine();

        // Find an empty room to add
        for (int i = 0; i < roomNumbers.length; i++) {
            if (roomNumbers[i] == 0) { // 0 means room is not used
                roomNumbers[i] = roomNumber;
                roomTypes[i] = roomType;
                isReserved[i] = false; // Mark as not reserved
                System.out.println("Enter the room price:");
                roomPrices[i] = ofs.nextDouble(); // Assign price to room
                ofs.nextLine(); // Consume the newline
                System.out.println("Room added successfully!");
                return;
            }
        }
        System.out.println("No available space to add a new room.");
    }

    // Method to delete a room
    public void delete() {
        System.out.println("Enter the room number to delete:");
        int roomNumber = ofs.nextInt();

        // Find and delete room
        for (int i = 0; i < roomNumbers.length; i++) {
            if (roomNumbers[i] == roomNumber) {
                roomNumbers[i] = 0; // Mark room as empty
                roomTypes[i] = null;
                isReserved[i] = false;
                guestNames[i] = null;
                guestPhones[i] = null;
                guestIDs[i] = null;
                roomPrices[i] = 0; // Reset price
                System.out.println("Room deleted successfully!");
                return;
            }
        }
        System.out.println("Room not found!");
    }

    // Method to reserve a room
    public void reserve() {
        System.out.println("Enter room number to reserve:");
        int roomNumber = ofs.nextInt();
        ofs.nextLine(); // Consume newline

        // Find the room and reserve it
        for (int i = 0; i < roomNumbers.length; i++) {
            if (roomNumbers[i] == roomNumber) {
                if (!isReserved[i]) {
                    System.out.println("Enter guest name:");
                    guestNames[i] = ofs.nextLine();

                    System.out.println("Enter guest phone number:");
                    guestPhones[i] = ofs.nextLine();

                    System.out.println("Enter guest ID number:");
                    guestIDs[i] = ofs.nextLine();

                    isReserved[i] = true; // Mark room as reserved
                    System.out.println("Room reserved successfully!");
                } else {
                    System.out.println("Room is already reserved!");
                }
                return;
            }
        }
        System.out.println("Room not found!");
    }

    // Method to show room details
    public void showRooms() {
        System.out.println("Enter option: \n1. Show all rooms\n2. Show room by ID");
        int option = ofs.nextInt();
        ofs.nextLine(); // Consume newline

        if (option == 1) {
            System.out.println("\nAll Rooms:");
            for (int i = 0; i < roomNumbers.length; i++) {
                if (roomNumbers[i] != 0) {
                    System.out.println("Room Number: " + roomNumbers[i] + ", Type: " + roomTypes[i] + ", Price: " + roomPrices[i] + ", Reserved: " + (isReserved[i] ? "Yes" : "No"));
                }
            }
        } else if (option == 2) {
            System.out.println("Enter room number to view details:");
            int roomNumber = ofs.nextInt();

            for (int i = 0; i < roomNumbers.length; i++) {
                if (roomNumbers[i] == roomNumber) {
                    System.out.println("Room Number: " + roomNumbers[i]);
                    System.out.println("Room Type: " + roomTypes[i]);
                    System.out.println("Price: " + roomPrices[i]);
                    System.out.println("Reserved: " + (isReserved[i] ? "Yes" : "No"));
                    if (isReserved[i]) {
                        System.out.println("Guest Name: " + guestNames[i]);
                        System.out.println("Guest Phone: " + guestPhones[i]);
                        System.out.println("Guest ID: " + guestIDs[i]);
                    }
                    return;
                }
            }
            System.out.println("Room not found!");
        } else {
            System.out.println("Invalid option!");
        }
    }

    // Method to show reserved rooms
    public void showReservedRooms() {
        System.out.println("\nReserved Rooms:");
        for (int i = 0; i < roomNumbers.length; i++) {
            if (isReserved[i]) {
                System.out.println("Room Number: " + roomNumbers[i] + ", Type: " + roomTypes[i] + ", Price: " + roomPrices[i]);
                System.out.println("Guest Name: " + guestNames[i]);
                System.out.println("Guest Phone: " + guestPhones[i]);
                System.out.println("Guest ID: " + guestIDs[i]);
                System.out.println("--------------------------");
            }
        }
    }

    // Method to check out of a room
    public void checkOut() {
        System.out.println("Enter room number to check out:");
        int roomNumber = ofs.nextInt();

        for (int i = 0; i < roomNumbers.length; i++) {
            if (roomNumbers[i] == roomNumber) {
                if (isReserved[i]) {
                    isReserved[i] = false; // Mark room as not reserved
                    guestNames[i] = null;
                    guestPhones[i] = null;
                    guestIDs[i] = null;
                    System.out.println("Room " + roomNumber + " has been checked out successfully!");
                } else {
                    System.out.println("Room " + roomNumber + " is not reserved!");
                }
                return;
            }
        }
        System.out.println("Room not found!");
    }

    // Main method
    public static void main(String[] args) {
        GuestMGMT ofc = new GuestMGMT();
        int choice;
        do {
            System.out.println("\nGuest House Management System");
            System.out.println("1. Add Room");
            System.out.println("2. Delete Room");
            System.out.println("3. Reserve Room");
            System.out.println("4. Check Out");
            System.out.println("5. Show Rooms");
            System.out.println("6. Show Reserved Rooms");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = ofs.nextInt();
            ofs.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    ofc.add();
                    break;
                case 2:
                    ofc.delete();
                    break;
                case 3:
                    ofc.reserve();
                    break;
                case 4:
                    ofc.checkOut();
                    break;
                case 5:
                    ofc.showRooms();
                    break;
                case 6:
                    ofc.showReservedRooms();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 7);
    }
}
