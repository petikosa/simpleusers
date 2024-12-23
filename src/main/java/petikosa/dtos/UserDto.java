package petikosa.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import petikosa.general.ValidPassword;

public class UserDto {

    @NotNull
    public long id;
    @NotBlank(message = "Name should not be empty")
    public String name;
    @NotBlank(message = "Username should not be empty")
    public String username;
    @ValidPassword
    public String password;

    public UserDto(long id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }
}
