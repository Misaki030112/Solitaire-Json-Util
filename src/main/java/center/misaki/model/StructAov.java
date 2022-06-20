package center.misaki.model;

import center.misaki.model.CardAov;
import center.misaki.model.CardBottom;
import lombok.Data;

import java.util.Map;

/**
 * @author Misaki
 * 纸牌数据结构，包含了该牌面牌底的所有信息
 */
@Data
public class StructAov {
    
    //牌面集合
    private final Map<Integer, CardAov> cardAovHashMap;
    //牌底集合
    private final Map<Integer, CardBottom> cardBottomHashMap;
    //牌面数量
    private final Integer upNum;
    //牌底数量
    private final Integer downNum;
    
    private Boolean refresh=Boolean.FALSE;

    public StructAov(Map<Integer, CardAov> cardAovHashMap, Map<Integer, CardBottom> cardBottomHashMap,int n,int m) {
        this.cardAovHashMap = cardAovHashMap;
        this.cardBottomHashMap = cardBottomHashMap;
        this.downNum=m;
        this.upNum=n;
    }
    
}
