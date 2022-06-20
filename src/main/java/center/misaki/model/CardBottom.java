package center.misaki.model;

import lombok.Data;

/**
 * @author Misaki
 * 底牌区的牌实体
 */
@Data
public class CardBottom {
    //卡牌值
    private Integer value;
    //卡牌序号
    private Integer index;

    public CardBottom(Integer index) {
        this.index = index;
    }
}
