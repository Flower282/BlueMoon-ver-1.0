package org.example.bluemoon.security;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class PasswordUtil {
    private static final Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

    public static String hashPassword(String password) {
        //số vòng lặp, bộ nhớ và số luồng được sử dụng để tạo một băm mật khẩu
        int iterations = 3;
        int memory = 65536;
        int parallelism = 2;
        try {
            return argon2.hash(iterations, memory, parallelism, password);
        } finally {
            argon2.wipeArray(password.toCharArray()); // Xóa mật khẩu khỏi bộ nhớ để tăng bảo mật
        }
    }

    /**
     * @return
     * @param1 storedPassword
     * @param2 password
     */
    public static boolean verifyPassword(String storedPassword, String password) {
        return argon2.verify(storedPassword, password);
    }
}