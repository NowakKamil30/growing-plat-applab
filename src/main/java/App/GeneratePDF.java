package App;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.fxml.FXML;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;


import javax.print.PrintServiceLookup;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import javax.print.PrintService;
import java.awt.print.PrinterJob;
import java.io.*;
import java.sql.*;
import java.util.Properties;


public class GeneratePDF {
    public static final String name = "midget.pdf";



    public void sendToPrint2() throws IOException, PrinterException {
        FileInputStream bis = new FileInputStream("report2.pdf");
        PDDocument document = PDDocument.load(bis);

        PrintService printService = findPrintService("Adobe PDF");

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPageable(new PDFPageable(document));
        job.setPrintService(printService);
        job.print();
    }

    private static PrintService findPrintService(String printerName) {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService printService : printServices) {
            if (printService.getName().trim().equals(printerName)) {
                return printService;
            }
        }
        return null;
    }


    public void sendToPrint() throws SQLException, IOException, DocumentException {
/*
        ReadSettings readSettings = ReadSettings.getInstance();
        String url = readSettings.getConnectedString();
        Properties info = new Properties();
        info.put("user", readSettings.getUsername());
        info.put("password", readSettings.getPassword());
        OutputStream file = null;
        Connection connection = DriverManager.getConnection(url, info);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM uz.Users");
        Document my_pdf_report2 = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        my_pdf_report2.open();
        PdfPTable my_report_table = new PdfPTable(6); //określam ilość kolumn na "sztywno"
        PdfPCell table_cell; //tworzę obiekt komórki tablicy


            while (resultSet.next()) {
                String first_name = resultSet.getString("first_name");
                table_cell = new PdfPCell(new Phrase(first_name));
                my_report_table.addCell(table_cell);
                String last_name = resultSet.getString("last_name");
                table_cell = new PdfPCell(new Phrase(last_name));
                my_report_table.addCell(table_cell);
                String login = resultSet.getString("login");
                table_cell = new PdfPCell(new Phrase(login));
                my_report_table.addCell(table_cell);
                String gender = resultSet.getString("gender");
                table_cell = new PdfPCell(new Phrase(gender));
                my_report_table.addCell(table_cell);
                String isActive = resultSet.getString("isActive");
                table_cell = new PdfPCell(new Phrase(isActive));
                my_report_table.addCell(table_cell);
                String role = resultSet.getString("role");
                table_cell = new PdfPCell(new Phrase(role));
                my_report_table.addCell(table_cell);
            }

            my_pdf_report2.add(my_report_table);
            my_pdf_report2.close();
            PdfWriter.getInstance(my_pdf_report2, baos);

            DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;

            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
            PrintService[] printService = PrintServiceLookup.lookupPrintServices(flavor, aset);


            byte[] pdfbyte = baos.toByteArray();

            if(printService.length != 0) {
                for (int i = 0; i < printService.length; i++) {
                    System.out.println(printService[i]);
                    if(printService[i].getName().equals("Adobe PDF"))
                    {
                       // FileInputStream bis = new FileInputStream("report2.pdf");
                        DocPrintJob printjob = printService[i].createPrintJob();
                        Doc doc = new SimpleDoc(pdfbyte, flavor, null);
                        printjob.print(doc, null);
                       // bis.close();
                    }
                }
            }else
            {
                System.out.println("blad blad blad blad");
            }
*/
}




    @FXML
    public void generate() {
        ReadSettings readSettings = ReadSettings.getInstance();
        String url = readSettings.getConnectedString();
        Properties info = new Properties();
        info.put("user", readSettings.getUsername());
        info.put("password", readSettings.getPassword());
        OutputStream file = null;
        try {
            Connection connection = DriverManager.getConnection(url, info);
            Statement statement = connection.createStatement();
            Document my_pdf_report = new Document();
            PdfWriter.getInstance(my_pdf_report, new FileOutputStream("report.pdf"));
            my_pdf_report.open();
            PdfPTable my_report_table = new PdfPTable(6); //określam ilość kolumn na "sztywno"
            PdfPCell table_cell; //tworzę obiekt komórki tablicy
            ResultSet resultSet = statement.executeQuery("SELECT * FROM uz.Users");

            while (resultSet.next()) {
                String first_name = resultSet.getString("first_name");
                table_cell = new PdfPCell(new Phrase(first_name));
                my_report_table.addCell(table_cell);
                String last_name = resultSet.getString("last_name");
                table_cell = new PdfPCell(new Phrase(last_name));
                my_report_table.addCell(table_cell);
                String login = resultSet.getString("login");
                table_cell = new PdfPCell(new Phrase(login));
                my_report_table.addCell(table_cell);
                String gender = resultSet.getString("gender");
                table_cell = new PdfPCell(new Phrase(gender));
                my_report_table.addCell(table_cell);
                String isActive = resultSet.getString("isActive");
                table_cell = new PdfPCell(new Phrase(isActive));
                my_report_table.addCell(table_cell);
                String role = resultSet.getString("role");
                table_cell = new PdfPCell(new Phrase(role));
                my_report_table.addCell(table_cell);

            }

            my_pdf_report.add(my_report_table);
            my_pdf_report.close();

        } catch (IOException | DocumentException | SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Your creation is done");
        }

    }
}
