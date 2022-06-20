package center.misaki.util;


import center.misaki.model.CardAov;
import center.misaki.model.StructAov;

import java.util.Map;

/**
 * @author Misaki
 */
public class CheckUtil {

    /**
     * 刷新覆盖等级
     * @param structAov 建立完成还未刷新覆盖等级的牌数据结构
     */
    public static void refreshLevel(StructAov structAov){
        if(structAov.getRefresh()) return;
        Map<Integer, CardAov> cardAovHashMap = structAov.getCardAovHashMap();
        cardAovHashMap.forEach((k,v)->{
            if(v.getLevel()==-1) return;
            dfs(v);
        });
        structAov.setRefresh(true);
    }

    /**
     * 辅助刷新覆盖等级函数，深度优先算法。
     * @param v 上一级CardAov
     */
    private static void dfs(CardAov v){
        v.getCover().forEach((k,v1)->{
            v1.setLevel(Math.max(v1.getLevel(),v.getLevel()+1));
            if(v1.getLevel()==v.getLevel()+1) dfs(v1);
        });
    }
    
    
}
