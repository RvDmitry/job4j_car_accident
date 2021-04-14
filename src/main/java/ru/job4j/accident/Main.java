package ru.job4j.accident;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Class Main
 * Класс используется для получения зашифрованного пароля.
 * @author Dmitry Razumov
 * @version 1
 */
public class Main {
    /**
     * Главный метод программы.
     * @param args Параметры командной строки.
     */
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd = encoder.encode("12345");
        System.out.println(pwd);
    }
}
