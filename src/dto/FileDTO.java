package dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class FileDTO {
    private int f_id;
    private int bId;
    private String fileName;
    private String filePath;
    private long fileSize;
    private Timestamp instDt;
}
