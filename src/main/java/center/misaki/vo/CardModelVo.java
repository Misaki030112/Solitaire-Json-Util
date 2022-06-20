package center.misaki.vo;

import center.misaki.model.CardAov;
import center.misaki.model.CardBottom;
import lombok.Data;

import java.util.List;

/**
 * @author Misaki
 * 标准将要被json序列化的实体，视图
 */
@Data
public class CardModelVo {
    private List<List<CardAovVo>> aov_list;
    
    private List<CardBottomVo> bottom_card_list;

    public CardModelVo(List<List<CardAovVo>> aov_list, List<CardBottomVo> bottom_card_list) {
        this.aov_list = aov_list;
        this.bottom_card_list = bottom_card_list;
    }

    @Data
    public static class CardAovVo{
        private final Card card;
        private Integer[] children;
        private Integer[] up_child;
        private Integer[] under_child;
        private final String type="puzzle";
        private Integer enter_line;
        private Integer level;
        private Integer index;

        public CardAovVo(CardAov cardAov) {
            this.card=new Card(cardAov);
            this.level=cardAov.getLevel();
            this.index=cardAov.getIndex();
        }
    }
    @Data
    public static class CardBottomVo{
        private final Card card;
        private final String type="bottom";
        private Integer index;

        public CardBottomVo(CardBottom cardBottom) {
            this.card=new Card(cardBottom);
            this.index=cardBottom.getIndex();
        }
    }
    
    @Data
    public static class Card{
        private Integer number; 
        private Object decor=null;
        private Object type=null;

        public Card(CardAov cardAov) {
            this.number=cardAov.getValue();
        }
        
        public Card(CardBottom cardBottom){
            this.number=cardBottom.getValue();
        }
    }
    
}
