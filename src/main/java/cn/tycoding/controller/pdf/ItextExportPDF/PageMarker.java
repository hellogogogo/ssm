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
import lombok.AllArgsConstructor;

/**
 * Itext7 实现页码
 *
 * @author darren
 * @date 2019-05-25
 */
@AllArgsConstructor
public class PageMarker implements IEventHandler {

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
        float  x      = (pageSize.getLeft() + pageSize.getRight()) / 2;
        float  y      = pageSize.getBottom() + 15;
        Paragraph p = new Paragraph("第" + pdf.getPageNumber(page) + "页")
                .setFontSize(12)
                .setFont(pdfFont);
        canvas.showTextAligned(p, x, y, TextAlignment.CENTER);
        canvas.close();
    }
}
