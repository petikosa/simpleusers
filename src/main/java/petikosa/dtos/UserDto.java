package petikosa.dtos;

public class UserDto {

    public long id;
    public String name;
    public String username;
    public String password;

    public UserDto(long id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }
}
