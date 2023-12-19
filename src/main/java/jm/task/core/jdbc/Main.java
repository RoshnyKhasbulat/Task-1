package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDao userDao = new UserDaoJDBCImpl();
        UserService userService = new UserServiceImpl(userDao);
        userService.createUsersTable();
        userService.saveUser("Лев", "Толстой", (byte) 28);
        userService.saveUser("Антон", "Чехов", (byte) 35);
        userService.saveUser("Михаил", "Булгаков", (byte) 32);
        userService.saveUser("Александр", "Пушкин", (byte) 37);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
