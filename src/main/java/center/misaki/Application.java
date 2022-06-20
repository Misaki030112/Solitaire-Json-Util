package center.misaki;

import center.misaki.model.StructAov;
import center.misaki.util.CheckUtil;
import center.misaki.util.JsonVoUtil;
import center.misaki.util.SourceReader;
import center.misaki.vo.CardModelVo;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Misaki
 * 主类，选择执行
 */
public class Application {
    public static void main(String[] args) {
        String sourcePath=args[0];
        String targetPath=null;
        SourceReader sourceReader = new SourceReader(sourcePath);
        targetPath=sourceReader.outPutPath(targetPath);
        StructAov structAov = sourceReader.buildAov();
        CheckUtil.refreshLevel(structAov);
        sourceReader.readValueOfAov(structAov);
        CardModelVo cardModelVo = JsonVoUtil.converterToVo(structAov);
        String res = JSON.toJSONString(cardModelVo, JSONWriter.Feature.PrettyFormat, JSONWriter.Feature.WriteMapNullValue);
        FileOutputStream outputStream=null;
        try {
            outputStream= new FileOutputStream(targetPath);
            outputStream.write(res.getBytes(StandardCharsets.UTF_8));
            System.out.println("执行成功！");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
