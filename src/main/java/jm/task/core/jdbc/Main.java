package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;



public class Main {
    private final static UserService userService = new UserServiceImpl();

    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        userService.createUsersTable();

        userService.saveUser("John", "Lennon", (byte) 40);
        userService.saveUser("Paul", "McCartney", (byte) 80);
        userService.saveUser("George", "Harrison", (byte) 58);
        userService.saveUser("Ringo", "Starr", (byte) 82);

        userService.getAllUsers();

        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}
