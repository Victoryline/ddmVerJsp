package dto;

import lombok.Data;

@Data
public class UserDTO {
    private String uId;
    private String name;
    private String email;
    private String pw;
    private String instDt;
}
