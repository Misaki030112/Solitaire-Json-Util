package center.misaki;

import center.misaki.model.StructAov;
import center.misaki.util.CheckUtil;
import center.misaki.util.JsonVoUtil;
import center.misaki.util.SourceReader;
import center.misaki.vo.CardModelVo;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Misaki
 * Json工具类测试
 */
public class JsonTest {
    
    @Test
    public void toVoTest(){
        SourceReader sourceReader = new SourceReader("/home/misaki/Projects/IdeaProjects/JsonUtil/src/test/resources/testExample1.txt");
        StructAov structAov = sourceReader.buildAov();
        CheckUtil.refreshLevel(structAov);
        sourceReader.readValueOfAov(structAov);
        CardModelVo cardModelVo = JsonVoUtil.converterToVo(structAov);
        System.out.println(cardModelVo);
    }
    
    
    
    int n=1;
    String sourcePath="src/test/resources/testExample"+n+".txt";
    String targetPath="src/test/resources/"+n+".json";
    
    @Test
    public void testOutPutToFile() throws IOException{
        SourceReader sourceReader = new SourceReader(sourcePath);
        StructAov structAov = sourceReader.buildAov();
        CheckUtil.refreshLevel(structAov);
        sourceReader.readValueOfAov(structAov);
        CardModelVo cardModelVo = JsonVoUtil.converterToVo(structAov);
        String res = JSON.toJSONString(cardModelVo, JSONWriter.Feature.PrettyFormat, JSONWriter.Feature.WriteMapNullValue);
        FileOutputStream outputStream = new FileOutputStream(targetPath);
        outputStream.write(res.getBytes(StandardCharsets.UTF_8));
        outputStream.close();
    }
    
    
}
