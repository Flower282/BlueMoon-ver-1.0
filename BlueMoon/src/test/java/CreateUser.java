import org.example.bluemoon.dao.UserDAO;
import org.example.bluemoon.models.enums.Role;
import org.example.bluemoon.models.User;
import org.example.bluemoon.security.PasswordUtil;

public class CreateUser {
    public static void main(String[] args) {
        String username = "admin@gmail.com";
        String password = "admin";
        UserDAO userDAO = new UserDAO();
        User user = User.builder()
                .username(username)
                .password(PasswordUtil.hashPassword(password))
                .role(Role.KE_TOAN)
                .isDeleted(false)
                .build();
        userDAO.save(user);
    }
}
