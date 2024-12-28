package com.phonebook;

import java.util.Scanner;

public class PhonebookApp {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        DatabaseManager.createTable();

        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addPhoneNumber();
                    break;
                case 2:
                    updatePhoneNumber();
                    break;
                case 3:
                    deletePhoneNumber();
                    break;
                case 4:
                    searchPhoneNumberByName();
                    break;
                case 5:
                    searchPhoneNumberByEmail();
                    break;
                case 6:
                    showAllPhoneNumbers();
                    break;
                case 7:
                    deleteDatabase();
                    break;
                case 0:
                    System.out.println("Exiting the application...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\nChoose an operation:");
        System.out.println("1. Add a phone number");
        System.out.println("2. Update a phone number");
        System.out.println("3. Delete a phone number");
        System.out.println("4. Search for a phone number by name");
        System.out.println("5. Search for a phone number by email");
        System.out.println("6. Show all phone numbers");
        System.out.println("7. Delete the entire database");
        System.out.println("0. Exit");
    }

    public static void addPhoneNumber() {
        DatabaseManager.isDatabaseExists(); 
        System.out.print("Enter the phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the email: ");
        String email = scanner.nextLine();
        if (DatabaseManager.addPhoneNumber(phoneNumber, name, email)) {
            System.out.println("Phone number added successfully.");
        } else {
            System.out.println("Invalid input. Please ensure the phone number, name, and email are correct.");
        }
    }

    public static void updatePhoneNumber() {
        DatabaseManager.isDatabaseExists(); 

        System.out.print("Enter the old phone number: ");
        String oldPhoneNumber = scanner.nextLine();
        System.out.print("Enter the new phone number: ");
        String newPhoneNumber = scanner.nextLine();
        System.out.print("Enter the new name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter the new email: ");
        String newEmail = scanner.nextLine();
        if (DatabaseManager.updatePhoneNumber(oldPhoneNumber, newPhoneNumber, newName, newEmail)) {
            System.out.println("Phone number updated successfully.");
        } else {
            System.out.println("Invalid input. Please ensure the phone number, name, and email are correct.");
        }
    }

    public static void deletePhoneNumber() {
        DatabaseManager.isDatabaseExists(); 

        System.out.print("Enter the phone number to delete: ");
        String phoneNumber = scanner.nextLine();
        if (DatabaseManager.deletePhoneNumber(phoneNumber)) {
            System.out.println("Phone number deleted successfully.");
        } else {
            System.out.println("Phone number not found.");
        }
    }

    public static void searchPhoneNumberByName() {
        DatabaseManager.isDatabaseExists(); 

        System.out.print("Enter the name to search: ");
        String name = scanner.nextLine();
        String result = DatabaseManager.searchPhoneNumberByName(name);
        if (result != null) {
            System.out.println("Result: " + result);
        } else {
            System.out.println("No result found.");
        }
    }

    public static void searchPhoneNumberByEmail() {
        DatabaseManager.isDatabaseExists(); 

        System.out.print("Enter the email to search: ");
        String email = scanner.nextLine();
        String result = DatabaseManager.searchPhoneNumberByEmail(email);
        if (result != null) {
            System.out.println("Result: " + result);
        } else {
            System.out.println("No result found.");
        }
    }

    public static void showAllPhoneNumbers() {
        DatabaseManager.isDatabaseExists(); 

        DatabaseManager.showAllPhoneNumbers();
    }

    public static void deleteDatabase() {
        DatabaseManager.isDatabaseExists(); 

        if (DatabaseManager.deleteDatabase()) {
            System.out.println("Database deleted successfully.");
        } else {
            System.out.println("Error deleting database.");
        }
    }

    
}
