import com.lowagie.text.pdf.BaseFont;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class IText {
	
	public static void main(String[] args)throws Exception {
		
		String inputFile = "src/com/MyHtml.html";
		String url = new File(inputFile).toURI().toURL().toString();
		String outputFile = "src/com/my.pdf";
		OutputStream os =new FileOutputStream(outputFile);
		ITextRenderer renderer = new ITextRenderer();
		
		renderer.setDocument(url);
		ITextFontResolver fontResolver = renderer.getFontResolver();
		//fontResolver.addFont("C:/Windows/Fonts/simsun.ttc", BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
		//fontResolver.addFont("C:/Windows/Fonts/arialuni.ttf", BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
		fontResolver.addFont("C:/Windows/fonts/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

		renderer.layout();
		renderer.createPDF(os);
		System.out.println("转换成功！");
		os.close();
	
//		    String inputFile = "src/com/MyHtml.html";
//	        Document doc = new Document();
//	        BaseFont bf = BaseFont.createFont("STSong-Light,Bold", "UniGB-UCS2-H",
//	                BaseFont.NOT_EMBEDDED);
//	        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
//	        PdfWriter.getInstance(doc, new FileOutputStream("src/com/my.pdf"));
//	        SAXmyHtmlHandler saxHandler = new SAXmyHtmlHandler(doc, bf);
//	        parser.parse(new File(inputFile), saxHandler);

		/*Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		PdfWriter writer = PdfWriter.getInstance(document, os);
		
		BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		com.lowagie.text.Font fontChinese = new com.lowagie.text.Font(bf, 12, com.lowagie.text.Font.NORMAL);
		Paragraph pragraph=new Paragraph("好好",fontChinese);  
		
		document.open();
		document.add(pragraph);
		document.close();*/
	} 
}

