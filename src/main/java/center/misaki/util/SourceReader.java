package center.misaki.util;

import center.misaki.model.CardAov;
import center.misaki.model.CardBottom;
import center.misaki.model.StructAov;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author Misaki
 * 资源读取类，负责读取资源建造数据结构，初始化等等。
 */
public class SourceReader {
    private final Scanner scanner;
    private final InputStream inputStream;

    public SourceReader(String path) {
        try {
            inputStream = new FileInputStream(path);
            scanner=new Scanner(inputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解析资源文件，解析出牌面，牌底数据结构。
     * @return 牌面与牌底集合数据结构
     */
    public StructAov buildAov(){
        //牌面上牌的数量
        int n = scanner.nextInt();
        //牌底牌的数量
        int m= scanner.nextInt();

        //存储牌面上牌的集合数据结构
        Map<Integer, CardAov> cardAovHashMap = new TreeMap<>();
        //存储牌底下牌的集合数据结构
        Map<Integer, CardBottom> cardBottomHashMap = new TreeMap<>();

        //建立数据结构阶段
        int i=0,j=0;
        while((i=scanner.nextInt())!=-1&&(j=scanner.nextInt())!=-1){
            cardAovHashMap.putIfAbsent(i,new CardAov(i));
            cardAovHashMap.putIfAbsent(j,new CardAov(j));
            CardAov cardAov_i=cardAovHashMap.get(i);
            CardAov cardAov_j=cardAovHashMap.get(j);
            //覆盖与被覆盖的关系
            cardAov_i.getCover().putIfAbsent(j,cardAov_j);
            cardAov_j.getCovered().putIfAbsent(i,cardAov_i);
        }
        for(i=n;i<m+n;i++){
            cardBottomHashMap.put(i,new CardBottom(i));
        }
        
        //初始化被翻面的牌面的覆盖等级
        while((i=scanner.nextInt())!=-1){
            cardAovHashMap.get(i).setLevel(0);
        }
        
        return new StructAov(cardAovHashMap,cardBottomHashMap,n,m);
    }


    /**
     * 以现有的牌数据结构读取，牌面各位置的值
     * @param structAov 已知牌面数据结构
     */
    public void readValueOfAov(StructAov structAov){
        Map<Integer, CardAov> cardAovHashMap = structAov.getCardAovHashMap();
        Map<Integer, CardBottom> cardBottomHashMap = structAov.getCardBottomHashMap();
        Integer n = structAov.getUpNum();
        int i=0,j=0,height=0;
        while((i=scanner.nextInt())!=-1&&(j=scanner.nextInt())!=-1){
            height=scanner.nextInt();
            
            if(i<n){
                cardAovHashMap.get(i).setValue(j);
                if(height!=-1) cardAovHashMap.get(i).setHeight(height);
            }
            else cardBottomHashMap.get(i).setValue(j);
        }
    }

    /**
     * 返回应该放置的目标路径
     * @return 目标路径
     */
    public String outPutPath(String target){
        int n = scanner.nextInt();
        if(target==null||target.equals("")){
            return n+".json";
        }else{
            return target+n+".json";
        }
    }
    

}
