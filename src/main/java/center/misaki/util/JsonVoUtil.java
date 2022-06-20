package center.misaki.util;

import center.misaki.model.CardAov;
import center.misaki.model.CardBottom;
import center.misaki.model.StructAov;
import center.misaki.vo.CardModelVo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Misaki
 * Json操作工具类，在原有FastJson基础上进一步封装
 */
public class JsonVoUtil {

    /**
     * 将牌面数据结构转换为需求的视图层结构
     * @param structAov 完成的牌面数据结构
     * @return 需求的牌面视图层
     */
    public static CardModelVo converterToVo(StructAov structAov){
        //牌面集合
        Map<Integer, CardAov> cardAovHashMap = structAov.getCardAovHashMap();
        //牌底集合
        Map<Integer, CardBottom> cardBottomHashMap = structAov.getCardBottomHashMap();
        //构造 aov_list 
        List<List<CardModelVo.CardAovVo>> aov_list = new ArrayList<>();
        boolean[] vis=new boolean[cardAovHashMap.size()];
        Arrays.fill(vis,false);
        cardAovHashMap.forEach((k,v)->{
            if(vis[k]) return;
            vis[k]=true;
            List<CardModelVo.CardAovVo> cardAovVos = new ArrayList<>();
            dfs(v,vis,cardAovVos);
            aov_list.add(cardAovVos);
        });
        //构造 bottom_card_list
        List<CardModelVo.CardBottomVo> bottom_card_list = new ArrayList<>();
        cardBottomHashMap.forEach((k,v)->{
            bottom_card_list.add(new CardModelVo.CardBottomVo(v));
        });
        
        return new CardModelVo(aov_list,bottom_card_list);
    }

    /**
     * 辅助函数，深度优先搜索确定，同组中所有cardAovVos中的元素
     * @param v 起点 CardAov
     * @param vis 标志数组，标志是否遍历
     * @param cardAovVos 同组集合
     */
    private static void dfs(CardAov v,boolean[] vis,List<CardModelVo.CardAovVo> cardAovVos){
        v.getCover().forEach((k,v1)->{
            if(vis[k])return;
            vis[k]=true;
            CardModelVo.CardAovVo cardAovVo = new CardModelVo.CardAovVo(v);
            computeDetailCardAovVo(cardAovVo,v);
            cardAovVos.add(cardAovVo);   
            dfs(v1,vis,cardAovVos);
        });
        v.getCovered().forEach((k,v1)->{
            if(vis[k])return;
            vis[k]=true;
            CardModelVo.CardAovVo cardAovVo = new CardModelVo.CardAovVo(v);
            computeDetailCardAovVo(cardAovVo,v);
            cardAovVos.add(cardAovVo);
            dfs(v1,vis,cardAovVos);
        });
        
    }

    /**
     * 根据CardAov中的已知信息，详细计算 CardAovVo中的详细信息
     * @param cardAovVo vo对象
     * @param v 原始信息对象
     */
    private static void computeDetailCardAovVo(CardModelVo.CardAovVo cardAovVo,CardAov v){
        Integer[] children = v.getCover().keySet().toArray(new Integer[0]);
        List<Integer> up_childList = new ArrayList<>();
        List<Integer> under_childList = new ArrayList<>();
        Integer enter_line=v.getCovered().size();
        v.getCover().forEach((k,v1)->{
            if(v1.getHeight()>v.getHeight())up_childList.add(k);
            if(v1.getHeight()<v.getHeight())under_childList.add(k);
        });
        v.getCovered().forEach((k,v1)->{
            if(v1.getHeight()>v.getHeight())up_childList.add(k);
            if(v1.getHeight()<v.getHeight())under_childList.add(k);
        });
        cardAovVo.setChildren(children);
        cardAovVo.setEnter_line(enter_line);
        cardAovVo.setUp_child(up_childList.toArray(new Integer[0]));
        cardAovVo.setUnder_child(under_childList.toArray(new Integer[0]));
    }
}
