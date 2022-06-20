package center.misaki;

import center.misaki.model.StructAov;
import center.misaki.util.CheckUtil;
import center.misaki.util.SourceReader;
import org.junit.jupiter.api.Test;

/**
 * @author Misaki
 */
public class RefreshLevelTest {
    
    @Test
    public void refresh(){
        SourceReader sourceReader = new SourceReader("/home/misaki/Projects/IdeaProjects/JsonUtil/src/test/resources/testExample1.txt");
        StructAov structAov = sourceReader.buildAov();
        
        CheckUtil.refreshLevel(structAov);
        System.out.println(structAov);
    }
}
