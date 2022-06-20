package center.misaki.model;

import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * 牌面上的牌实体
 * @author Misaki
 */
@Data
public class CardAov {
    
    //卡牌值
    private Integer value;
    
    //卡牌所覆盖住的卡牌
    @ToString.Exclude
    private Map<Integer, CardAov> cover=new HashMap<>();
    
    //覆盖住该卡牌的卡牌
    @ToString.Exclude
    private Map<Integer,CardAov> covered=new HashMap<>();
    
    //卡牌索引
    private Integer index;
    
    //卡牌覆盖等级
    private Integer level=-1;
    
    //卡牌层次坐标
    private Integer height;
    

    public CardAov(Integer index) {
        this.index = index;
    }
    
}
