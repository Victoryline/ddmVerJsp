package dto;

import lombok.Data;

import java.util.Date;

@Data
public class BoardDTO {
    private int bId;
    private String title;
    private String content;
    private int uId;
    private int cateCd;
    private Date instDt;
    private int recommendCount;
}
