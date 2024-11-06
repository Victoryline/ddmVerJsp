package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendDTO {
    private String uId;
    private int bId;
    private String rec_gbn;
    private String instDt;
}
