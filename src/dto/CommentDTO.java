package dto;

import lombok.Data;

@Data
public class CommentDTO {
    private int cId;
    private int bId;
    private String uId;
    private String content;
    private String created;
}
