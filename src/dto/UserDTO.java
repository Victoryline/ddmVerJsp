package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String uId;
    private String name;
    private String email;
    private String pw;
    private String instDt;

    public UserDTO(String uId, String name, String email) {
        this.uId = uId;
        this.name = name;
        this.email = email;
    }
}
