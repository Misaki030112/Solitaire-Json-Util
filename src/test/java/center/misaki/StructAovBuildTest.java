package center.misaki;


import center.misaki.model.StructAov;
import center.misaki.util.CheckUtil;
import center.misaki.util.SourceReader;
import org.junit.jupiter.api.Test;

/**
 * @author Misaki
 */
public class StructAovBuildTest {
    
    @Test
    public void testExample1(){
        SourceReader sourceReader = new SourceReader("/home/misaki/Projects/IdeaProjects/JsonUtil/src/test/resources/testExample1.txt");
        StructAov structAov = sourceReader.buildAov();
        System.out.println(structAov);
    }
    
    @Test
    public void ReadValueTest(){
        SourceReader sourceReader = new SourceReader("/home/misaki/Projects/IdeaProjects/JsonUtil/src/test/resources/testExample1.txt");
        StructAov structAov = sourceReader.buildAov();
        CheckUtil.refreshLevel(structAov);
        sourceReader.readValueOfAov(structAov);
        System.out.println(structAov);
    }
}
