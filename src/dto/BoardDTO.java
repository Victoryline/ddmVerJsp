package dto;

import lombok.Data;

@Data
public class BoardDTO {
    private int bId;
    private String title;
    private String content;
    private int uId;
    private int cateCd;
    private String instDt;
}
