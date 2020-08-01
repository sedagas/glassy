package com.sgass.glassy.Util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.sgass.glassy.Model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

public class GeneratePdf {
    private static final Logger logger = LoggerFactory.getLogger(GeneratePdf.class);

    public static ByteArrayInputStream ordersReport(Order order) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try{
            Image logo = Image.getInstance("src/main/resources/static/img/ksoptik.jpg");
            logo.scaleToFit(114, 110);

            PdfPCell imageCell = new PdfPCell();

            PdfPTable headertable = new PdfPTable(3);
            headertable.setWidthPercentage(100);
            headertable.setWidths(new int[]{2, 3, 3});
            imageCell.addElement(logo);
            imageCell.setBorder(Rectangle.NO_BORDER);
            headertable.addCell(imageCell);

            Font headFont = FontFactory.getFont(FontFactory.COURIER,14);
            Font boldFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 16);

            PdfPCell adrressCell = new PdfPCell();
            Paragraph companyName = new Paragraph("Kosova Saat Optik",boldFont);
            Paragraph address = new Paragraph("Rr.Avdullah Tahiri\n+383 44 139 699",headFont);
            adrressCell.addElement(companyName);
            adrressCell.addElement(address);
            adrressCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            adrressCell.setBorder(Rectangle.NO_BORDER);
            headertable.addCell(adrressCell);

            PdfPCell dateCell;
            dateCell = new PdfPCell(new Phrase(order.getDate(), headFont));
            dateCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            dateCell.setBorder(Rectangle.NO_BORDER);
            headertable.addCell(dateCell);

            PdfPTable table = new PdfPTable(1);
            table.setWidthPercentage(100);


            PdfPCell patientCell;
            patientCell = new PdfPCell(new Phrase("Patient : "+ order.getCustomer().getCustomerName() + " " + order.getCustomer().getCustomerSurname(), headFont));
            patientCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            patientCell.setBorder(Rectangle.NO_BORDER);
            patientCell.setPadding(8);
            table.addCell(patientCell);

            PdfPTable productTable = new PdfPTable(2);
            productTable.setWidthPercentage(100);
            productTable.setWidths(new int[]{3, 1});

            PdfPCell cell;

            cell = new PdfPCell(new Phrase("Product: " + order.getProduct().getProductName()));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setPadding(8);
            productTable.addCell(cell);

            cell = new PdfPCell(new Phrase("Price: " + order.getPrice() + " €"));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setPadding(8);
            productTable.addCell(cell);


            LineSeparator separator = new LineSeparator();

            PdfPTable prescriptionTable= new PdfPTable(6);
            prescriptionTable.setWidthPercentage(100);
            prescriptionTable.addCell("");
            prescriptionTable.addCell("Spehere");
            prescriptionTable.addCell("Cyl");
            prescriptionTable.addCell("Ax");
            prescriptionTable.addCell("Add");
            prescriptionTable.addCell("Pd");
            prescriptionTable.addCell("OD");
            prescriptionTable.addCell("");
            prescriptionTable.addCell("");
            prescriptionTable.addCell("");
            prescriptionTable.addCell("");
            prescriptionTable.addCell("");
            prescriptionTable.addCell("OS");
            prescriptionTable.addCell("");
            prescriptionTable.addCell("");
            prescriptionTable.addCell("");
            prescriptionTable.addCell("");
            prescriptionTable.addCell("");

            PdfPTable glassTable = new PdfPTable(3);
            glassTable.setWidthPercentage(100);
            glassTable.addCell("Glass Index");
            glassTable.addCell("Glass Type");
            glassTable.addCell("Lens");
            glassTable.addCell("BioFocal");
            glassTable.addCell("1.71");
            glassTable.addCell("Progressive");





            PdfWriter.getInstance(document, out);
            document.open();
            document.add(headertable);
            document.add(separator);
            document.add(table);
            document.add(prescriptionTable);
            document.add(glassTable);
            document.add(productTable);

            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  new ByteArrayInputStream(out.toByteArray());
    }

}
