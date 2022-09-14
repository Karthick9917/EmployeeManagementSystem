package com.ideas2it.employeeManagementSystem.employeeUtil;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeUtil {

    Scanner scanner = new Scanner(System.in);

    /**
     * receive a String employee details from user.
     *
     * @return the string user value
     */
    public String receiveEmployeeDetail() {
        String receivedEmployeeDetail = scanner.next();
        return receivedEmployeeDetail;
    }

    /**
     * receive a String employee details from user.
     *
     * @return the integer user value.
     */
    public int getEmployeeDetail() {
        int receivedValue = 0;
        boolean isReceivedValue = false;
        do {
            try {
                scanner = new Scanner(System.in);
                receivedValue = scanner.nextInt();
                isReceivedValue = true;
            } catch (InputMismatchException inputMismatch) {
                System.out.println("Please enter the input should be integer..!!");
            }
        } while (!isReceivedValue);
        return receivedValue;
    }
}
