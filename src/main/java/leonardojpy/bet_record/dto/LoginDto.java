package leonardojpy.bet_record.dto;

import leonardojpy.bet_record.model.User;

public class LoginDto {
    private String email;
    private String password;

    LoginDto(){}

    LoginDto(User user){
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
