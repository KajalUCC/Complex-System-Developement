
    public class LoginService {

        public boolean isUserValid(String user, String password) {
            return user.equals ("admin") && password.equals ("admin");
        }

    }
