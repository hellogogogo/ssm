package cn.tycoding.controller.pdf.ItextExportPDF;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.attach.impl.OutlineHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import com.itextpdf.layout.font.FontProvider;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Itext7 实现HTML转PDF
 *
 * @author darren
 * @date 2019-05-25
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Itext7Generator {

    private FontProvider fontProvider;

    private Itext7Generator instanse;

    String html() {
        StringBuilder out = new StringBuilder();
        try (InputStream input = this.getClass().getResourceAsStream("/templates/123.html")) {
            byte[] b = new byte[4096];
            int n;
            while ((n = input.read(b)) != -1) {
                out.append(new String(b, 0, n));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ;
        return out.toString();
    }

    public static Itext7Generator getInstanse(String fontPath) {
        Itext7Generator instanse = new Itext7Generator();
        instanse.initFont(fontPath);
        return instanse;
    }

    private void initFont(String fontPath) {
        fontProvider = new FontProvider();
        //设置中文字体文件的路径，可以在classpath目录下
        fontProvider.addFont(fontPath);
    }

    public void generatePdf(String html, String outputFile) throws Exception {
        //outputFile也可以是输出流
        PdfWriter writer = new PdfWriter(outputFile, new WriterProperties().setFullCompressionMode(Boolean.TRUE));
        PdfDocument doc = new PdfDocument(writer);
        doc.setDefaultPageSize(PageSize.A4);
        doc.getDefaultPageSize().applyMargins(20, 20, 20, 20, true);

        //获取字体，提供给水印 和 页码使用
        PdfFont pdfFont = fontProvider.getFontSet()
                .getFonts()
                .stream()
                .findFirst()
                .map(fontProvider::getPdfFont)
                .orElse(null);
        //水印
//      doc.addEventHandler(PdfDocumentEvent.END_PAGE, new WaterMarker(pdfFont));
        doc.addEventHandler(PdfDocumentEvent.END_PAGE, new PageMarker(pdfFont));
        ConverterProperties properties = new ConverterProperties();
        properties.setFontProvider(fontProvider);
        //PDF目录
        properties.setOutlineHandler(OutlineHandler.createStandardHandler());
        HtmlConverter.convertToPdf(html, doc, properties);
    }

    public void generatePdf(String html, OutputStream outputFile) throws Exception {
        //outputFile也可以是输出流
        PdfWriter writer = new PdfWriter(outputFile, new WriterProperties().setFullCompressionMode(Boolean.TRUE));
        PdfDocument doc = new PdfDocument(writer);
        doc.setDefaultPageSize(PageSize.A4);
        doc.getDefaultPageSize().applyMargins(20, 20, 20, 20, true);

        //获取字体，提供给水印 和 页码使用
        PdfFont pdfFont = fontProvider.getFontSet()
                .getFonts()
                .stream()
                .findFirst()
                .map(fontProvider::getPdfFont)
                .orElse(null);
        //水印
//      doc.addEventHandler(PdfDocumentEvent.END_PAGE, new WaterMarker(pdfFont));
        doc.addEventHandler(PdfDocumentEvent.END_PAGE, new PageMarker(pdfFont));
        ConverterProperties properties = new ConverterProperties();
        properties.setFontProvider(fontProvider);
        //PDF目录
        properties.setOutlineHandler(OutlineHandler.createStandardHandler());
        HtmlConverter.convertToPdf(html, doc, properties);
    }
}
