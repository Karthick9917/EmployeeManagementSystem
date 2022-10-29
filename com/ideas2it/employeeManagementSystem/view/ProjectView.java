package com.ideas2it.employeeManagementSystem.view;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.constants.Constants;
import com.ideas2it.employeeManagementSystem.controller.ProjectController;
import com.ideas2it.employeeManagementSystem.dto.EmployeeDTO;
import com.ideas2it.employeeManagementSystem.dto.ProjectDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ProjectView {

    private static Logger logger = LogManager.getLogger(EmployeeView.class.getName());
    private Scanner scanner = new Scanner(System.in);

    private ProjectController projectController = new ProjectController();

    /**
     * To get a user input to perform CRUD operation on project management
     * and assign employees to the project.
     */
    public void manageProject () {
        int option = 0;

        do {
            try {
                System.out.println(Constants.PROJECT_MENU);
                scanner = new Scanner(System.in);
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        this.createProject();
                        break;

                    case 2:
                        this.displayProject();
                        break;

                    case 3:
                        this.updateProject();
                        break;

                    case 4:
                        this.deleteProject();
                        break;

                    case 5:
                        this.assignEmployeesForProject();
                        break;

                    case 6:
                        System.exit(6);

                    default:
                        logger.warn("invalid data");
                        System.out.println(Constants.
                                SELECT_OPTION_ERROR);
                }
            } catch (InputMismatchException inputMismatchException) {
                logger.error("input mismatch");
                System.out.println(Constants.INPUT_MISMATCH_EXCEPTION);
            }
        } while (option != 6);
    }

    /**
     * pass the project object for assigning project to employee.
     */
    private void assignEmployeesForProject() {
        List<EmployeeDTO> employeeDTOList = new ArrayList<EmployeeDTO>();
        System.out.println(Constants.PROJECT_ID + "to assign employees");
        int id =  Integer.parseInt(getId(Constants.
                ID_PATTERN, "id eg: 1 or 12"));
        ProjectDTO projectDTO = projectController.getProjectById(id);
        System.out.println("How Many Employees to Assign for this Project");
        int employeeCount = Integer.parseInt(getUserInput(Constants.ID_PATTERN,"count of employee"));
        for (int count = 0; count < employeeCount; count++) {
            employeeDTOList.add(getEmployee());
        }
        projectDTO.setEmployeeDTO(employeeDTOList);
        try {
            projectController.assignEmployeesForProject(projectDTO);
            logger.info("Project " + id + "has been updated successfully");
            System.out.println(Constants
                    .SUCCESSFUL_MESSAGE + "updated");
        } catch (EmsException emsException) {
            logger.error("database not connected..!!");
            System.out.println(emsException.getMessage());
        }
    }

    public EmployeeDTO getEmployee() {
        EmployeeDTO employeeDto = null;
        System.out.println(Constants.EMPLOYEE_ID + " to Assign");
        int employeeId = Integer.parseInt(getUserInput(Constants.ID_PATTERN,"employee id"));
        try {
            employeeDto = projectController.getEmployeeById(employeeId);
            if (employeeDto == null) {
                System.out.println(Constants.ERROR_404);
                getEmployee();
            }
        } catch (EmsException e) {
            logger.error("empty record..!!");
            System.out.println(Constants.ERROR_404);
        }
        return employeeDto;
    }

    /**
     * Delete the employee details based on the employee id.
     */
    private void deleteProject() {
        System.out.println(Constants.PROJECT_ID + "to delete");
        int id =  Integer.parseInt(getId(Constants.
                ID_PATTERN, "id eg: 1 or 12"));
        try {
            projectController.deleteProject(id);
            logger.info("Project " + id + "has been removed successfully");
            System.out.println(Constants.
                    SUCCESSFUL_MESSAGE + "deleted");
        } catch (EmsException e) {
            logger.error("empty record..!!");
            System.out.println(Constants.ERROR_404);
        }
    }

    /**
     * To get a user input to perform project Create operation.
     */
    public void createProject() {
        ProjectDTO projectDTO = new ProjectDTO();
        System.out.println(Constants.PROJECT_NAME);
        String name = getUserInput(Constants.PROJECT_NAME_PATTERN,"project name");
        projectDTO.setProjectName(name);
        System.out.println(Constants.PROJECT_DOMAIN);
        String domain = getUserInput(Constants.PROJECT_DOMAIN_PATTERN,"project domain name");
        projectDTO.setDomain(domain);
        System.out.println(Constants.PROJECT_DUE_DATE);
        projectDTO.setProjectDue(getDate());
        System.out.println(Constants.PROJECT_START_DATE);
        projectDTO.setProjectStart(getDate());
        System.out.println(Constants.PROJECT_END_DATE);
        projectDTO.setProjectEnd(getDate());
        try {
            if (projectController.addProject(projectDTO)) {
                System.out.println(Constants.
                        SUCCESSFUL_MESSAGE + "created ");
            } else {
                System.out.println(Constants.NOT_ADDED_MESSAGE);
            }
        } catch (EmsException emsException) {
            logger.error("Database not connected");
            System.out.println(emsException.getMessage());
        }
    }

    /**
     * To display all the projects.
     */
    private void displayProject() {
        try {
            List<ProjectDTO> projectDTOList = projectController.
                    getAllProject();
            if (projectDTOList.isEmpty()) {
                logger.warn("No records in database");
                System.out.println(Constants.RECORD_EMPTY_MESSAGE);
            } else {
                for (ProjectDTO projectDTO : projectDTOList) {
                    System.out.println(projectDTO);
                }
                logger.info("Project displayed");
            }
        } catch (EmsException emsException) {
            logger.error("Database not connected");
            System.out.println(emsException.getMessage());
        }
    }

    /**
     * Update the project based on the project id.
     */
    private void updateProject() {
        System.out.println(Constants.PROJECT_ID + "to update ");
        int id = Integer.parseInt(getId(Constants.
                ID_PATTERN, "id eg: 1 or 12"));
        ProjectDTO projectDTO = projectController.getProjectById(id);
        projectDTO.setId(id);
        int option = 0;
        do {
            try {
                System.out.println(Constants.UPDATE_PROJECT_MENU);
                scanner = new Scanner(System.in);
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        System.out.println(Constants.PROJECT_NAME);
                        projectDTO.setProjectName(getUserInput(Constants
                                .PROJECT_NAME_PATTERN,"project name"));
                        break;
                    case 2:
                        System.out.println(Constants.PROJECT_DOMAIN);
                        projectDTO.setDomain(getUserInput(Constants
                                .PROJECT_DOMAIN_PATTERN, "project domain"));
                        break;
                    case 3:
                        System.out.println(Constants.PROJECT_DUE_DATE);
                        projectDTO.setProjectDue(getDate());
                        break;
                    case 4:
                        System.out.println(Constants.PROJECT_START_DATE);
                        projectDTO.setProjectStart(getDate());
                        break;
                    case 5:
                        System.out.println(Constants.PROJECT_END_DATE);
                        projectDTO.setProjectEnd(getDate());
                        break;
                    case 06:
                        break;
                    default:
                        logger.warn("invalid data");
                        System.out.println(Constants.
                                SELECT_OPTION_ERROR);
                }
            } catch (InputMismatchException inputMismatchException) {
                logger.error("input mismatch");
                System.out.println(Constants.INPUT_MISMATCH_EXCEPTION);
            }
        } while (option != 6);
        try {
            projectController.updateProject(projectDTO);
            logger.info("Project " + id + "has been updated successfully");
            System.out.println(Constants
                    .SUCCESSFUL_MESSAGE + "updated");
        } catch (EmsException emsException) {
            logger.error("database not connected..!!");
            System.out.println(emsException.getMessage());
        }
    }

    /**
     * Getting the input from the user and check the input is valid or not.
     * @param pattern - Regex matcher pattern
     * @param errorMessage - if the input is not valid this error
     *                       message is display to the user.
     * @return the user input whether is it valid.
     */
    public String getUserInput(String pattern, String errorMessage) {
        boolean isValid = false;
        String userInput;
        do{
            scanner = new Scanner(System.in);
            userInput = scanner.nextLine();
            if(!projectController.userInputValidation(pattern, userInput)) {
                System.out.println(Constants.
                        ASKING_VALID_INPUT + errorMessage);
            } else {
                isValid = true;
            }
        } while (!isValid);
        return userInput;
    }

    /**
     * Getting the date from user and check the date is valid or not based
     * on the given format
     *
     * @return the parsed date after the date is valid.
     */
    public LocalDate getDate() {
        boolean isValid = false;
        String date = null;
        do {
            try {
                scanner = new Scanner(System.in);
                date = scanner.next();
                if(projectController.getDate(date)){
                    System.out.println(Constants.
                            ASKING_VALID_INPUT + "date(YYYY-MM-DD)");
                } else {
                    isValid = true;
                }
            } catch (EmsException emsException) {
                logger.error("date format mismatch");
                System.out.println(emsException.getMessage());
            }
        } while (!isValid);
        return LocalDate.parse(date);
    }

    /**
     * Getting the input from the user and passing for the input is valid or not.
     * @param pattern - for check the given input is matches or not.
     * @param errorMessage - for Displaying the message
     *                       once the given input is invalid.
     * @return the string value once the given input is valid.
     */
    public String getId(String pattern, String errorMessage) {
        boolean isValid = false;
        String id;
        do{
            scanner = new Scanner(System.in);
            id = scanner.nextLine();
            if (!projectController.userInputValidation(pattern, id)) {
                System.out.println(Constants.
                        ASKING_VALID_INPUT + errorMessage);
            } else if(!projectController.validateId(id)){
                System.out.println(Constants.ERROR_404 + "\n" + Constants.
                        ASKING_VALID_INPUT + errorMessage);
            } else {
                isValid = true;
            }
        } while (!isValid);
        return id;
    }
}
