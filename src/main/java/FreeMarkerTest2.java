import com.lowagie.text.pdf.BaseFont;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FreeMarkerTest2 {

    public static void main(String[] args) throws Exception {
        /* 创建配置 */
        Configuration cfg = new Configuration();
        /* 指定模板存放的路径*/
        cfg.setDirectoryForTemplateLoading(new File("src/com"));
       // cfg.setObjectWrapper(ObjectWrapper.BEANS_WRAPPER);
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        /* 从上面指定的模板目录中加载对应的模板文件*/
        Template temp = cfg.getTemplate("spsShouYe.ftl"); 
        
        /* 创建数据模型 */
        Map root = new HashMap();
        root.put("user", "Big Joe");
        Map latest = new HashMap();
        root.put("latestProduct", latest);
        latest.put("name", "haha");
        latest.put("add", "北京");
     
		String outputFileUrl = "src/com/my.pdf";
		OutputStream os = new FileOutputStream(outputFileUrl);
		
		Writer tempWriter = new CharArrayWriter();
		temp.process(root, tempWriter);
		
		ITextRenderer renderer = new ITextRenderer();
		System.out.println(tempWriter.toString());
		renderer.setDocumentFromString(tempWriter.toString());
		
		//解决中文问题
		ITextFontResolver fontResolver = renderer.getFontResolver();
		fontResolver.addFont("C:/Windows/Fonts/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		fontResolver.addFont("C:/Windows/Fonts/STZHONGS.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		
		renderer.layout();
		renderer.createPDF(os);
		System.out.println("转换成功！");
		os.close();
    }
}