/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

/**
 *
 * @author Admin
 */
public class test {

    public static void main(String[] args) throws FileNotFoundException {
        String path = "nhom4.pdf";
        PdfWriter pdfWriter = new PdfWriter(path);// tạo file pdf
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4);

        Document document = new Document(pdfDocument);
        float threecol = 190f;
        float twoCol = 285f;
        float twoCol150 = twoCol + 150f;
        float twoColumnWidth[] = {twoCol150, twoCol};
        float threeColumnWidth[] = {threecol,threecol,threecol};
        float fullWidth[] = {threecol * 3};
        Paragraph onesp = new Paragraph("\n");

        Table table = new Table(twoColumnWidth);
        table.addCell(new Cell().add("NHÓM 4").setBorder(Border.NO_BORDER).setBold());
        Table nestedtabe = new Table(new float[]{twoCol / 2, twoCol / 2});
        nestedtabe.addCell(getHeaderTextCell("Mã hóa đơn: "));// set ma hoa don
        nestedtabe.addCell(getHeaderTextCellValue("HD01"));
        nestedtabe.addCell(getHeaderTextCell("Created: "));
        nestedtabe.addCell(getHeaderTextCellValue("3/12/2022"));

        table.addCell(new Cell().add(nestedtabe).setBorder(Border.NO_BORDER));

        Border border = new SolidBorder(Color.GRAY, 1f / 2f);
        Table divider = new Table(fullWidth);
        divider.setBorder(border);
        document.add(table);
        document.add(onesp);
        document.add(divider);
        document.add(onesp);

        Table twoColTable = new Table(twoColumnWidth);
        twoColTable.addCell(getBillingandShippingCell("Billing Information"));
        twoColTable.addCell(getBillingandShippingCell("Shipping information"));
        document.add(twoColTable.setMarginBottom(12f));

        Table twoColTable2 = new Table(twoColumnWidth);
        twoColTable2.addCell(getCell10fLeft("Tên công ty : ", true));
        twoColTable2.addCell(getCell10fLeft("Tên người mua : ", true));
        twoColTable2.addCell(getCell10fLeft("ghi teen cty : ", false));// teen 
        twoColTable2.addCell(getCell10fLeft("ghi ten nguoi mua : ", false));
        document.add(twoColTable2);
        
        
        Table twoColTable3 = new Table(twoColumnWidth);
        twoColTable3.addCell(getCell10fLeft("Name", true));
        twoColTable3.addCell(getCell10fLeft("Address", true));
        twoColTable3.addCell(getCell10fLeft("Hoàng văn Tám", false));
        twoColTable3.addCell(getCell10fLeft("Thái bình", false));
        document.add(twoColTable3);
        
        float oneColoumnWidth[] = {twoCol150};
        
        Table oneColTable1 = new Table(oneColoumnWidth);
        oneColTable1.addCell(getCell10fLeft("Address", true));
        oneColTable1.addCell(getCell10fLeft("Hà nội", false));
        oneColTable1.addCell(getCell10fLeft("Email", true));
        oneColTable1.addCell(getCell10fLeft("tam@gmail.com",false));
        document.add(oneColTable1.setMarginBottom(10f));
        
        Table tableDivider2 = new Table(fullWidth);
        Border dgb = new DashedBorder(Color.GRAY,0.5f);
        document.add(tableDivider2.setBorder(dgb));
        
        Paragraph productParam = new Paragraph("Products");
        
        document.add(productParam.setBold());
        Table threeColTable1 = new Table(threeColumnWidth);
        threeColTable1.setBackgroundColor(Color.BLACK,0.7f);
        
        threeColTable1.addCell(new Cell().add("Product Name").setBold().setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
        threeColTable1.addCell(new Cell().add("Quantity").setBold().setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
        threeColTable1.addCell(new Cell().add("Price").setBold().setFontColor(Color.WHITE).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER).setMarginRight(15f));
        document.add(threeColTable1);
        // cột header
        Table threTable2 = new Table(threeColumnWidth);
//        for(){
//            threTable2.addCell(new Cell().add(table).setBorder(Border.NO_BORDER).setMarginLeft(10f));
//            threTable2.addCell(new Cell().add(table).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
//            threTable2.addCell(new Cell().add(table).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER).setMarginRight(15f));
//        }
        document.add(threTable2.setMarginBottom(20f));
        float oneTwo[] = {threecol + 125f ,threecol * 2};
        Table threeColTable4 = new Table(oneTwo);
        threeColTable1.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        threeColTable4.addCell(tableDivider2).setBorder(Border.NO_BORDER);
        document.add(threeColTable4);
        
        Table threeColTable3 = new Table(threeColumnWidth);
        threeColTable3.addCell(new Cell().add("").setBorder(Border.NO_BORDER).setMarginLeft(10f));
        threeColTable3.addCell(new Cell().add("Total:").setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER).setMarginLeft(10f));
        threeColTable3.addCell(new Cell().add("").setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER).setMarginRight(15f));
        
        document.add(threeColTable3);
        document.add(tableDivider2);
        document.add(new Paragraph("\n"));
        document.add(divider.setBorder(new SolidBorder(Color.GRAY,1)).setMarginBottom(15f));
        Table tb = new Table(fullWidth);
        tb.addCell(new Cell().add("Cảm ơn bạn đã lựa chọn dịch vụ của chúng tôi ").setBold().setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
      
        document.add(tb);
        document.close();

    }

    static Cell getHeaderTextCell(String textValue) {
        return new Cell().add(textValue).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT);
    }

    static Cell getHeaderTextCellValue(String textValue) {
        return new Cell().add(textValue).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }

    static Cell getBillingandShippingCell(String textValue) {
        return new Cell().add(textValue).setFontSize(12f).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }

    static Cell getCell10fLeft(String textValue, Boolean isBold) {
        Cell myCell = new Cell().add(textValue).setFontSize(10f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
        return isBold ? myCell.setBold() : myCell;
    }

}
