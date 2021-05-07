package cn.tycoding.controller.pdf.ItextExportPDF;

import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import lombok.AllArgsConstructor;

/**
 * Itext7 实现水印
 *
 * @author darren
 * @date 2019-05-25
 */
@AllArgsConstructor
public class WaterMarker implements IEventHandler {

    private PdfFont pdfFont;

    @Override
    public void handleEvent(Event event) {

        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfDocument      pdf      = docEvent.getDocument();
        PdfPage          page     = docEvent.getPage();
        Rectangle        pageSize = page.getPageSize();
        PdfCanvas pdfCanvas = new PdfCanvas(
                page.getLastContentStream(), page.getResources(), pdf);
        Canvas canvas = new Canvas(pdfCanvas, pdf, pageSize);
        Paragraph waterMarker = new Paragraph("WATER MARKER 文字水印")
                .setFont(pdfFont)
                .setOpacity(.1f)
                .setFontSize(30);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                canvas.showTextAligned(waterMarker, (150 + i * 300), (160 + j * 150), pdf.getNumberOfPages(), TextAlignment.CENTER, VerticalAlignment.BOTTOM, .6f);
            }
        }
        canvas.close();
    }
}
