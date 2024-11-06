package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendDTO {
    public RecommendDTO(String uId, int bId, String rec_gbn){
        this.uId = uId;
        this.bId = bId;
        this.rec_gbn = rec_gbn;
    }
    private String uId;
    private int bId;
    private String rec_gbn;
    private String instDt;
}
