package App;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;
import java.util.Properties;


public class GeneratePDF {
    public static SortedList<UserMaster> sortedList;
    public static final String name = "midget.pdf";

    @FXML
    public static void generate()
    {
        ReadSettings readSettings = ReadSettings.getInstance();
        String url = readSettings.getConnectedString();
        Properties info = new Properties();
        info.put("user", readSettings.getUsername());
        info.put("password", readSettings.getPassword());
        OutputStream file = null;
        try {
            Connection connection = DriverManager.getConnection(url,info);
            Statement statement = connection.createStatement();
            Document my_pdf_report = new Document();
            PdfWriter.getInstance(my_pdf_report, new FileOutputStream("report.pdf"));
            my_pdf_report.open();
            PdfPTable my_report_table = new PdfPTable(6); //określam ilość kolumn na "sztywno"
            PdfPCell table_cell; //tworzę obiekt komórki tablicy
            ResultSet resultSet = statement.executeQuery("SELECT * FROM uz.Users");
            int i=0;
            while(resultSet.next())
            {


                String first_name = resultSet.getString("first_name");
                table_cell=new PdfPCell(new Phrase(first_name));
                my_report_table.addCell(table_cell);
                String last_name = resultSet.getString("last_name");
                table_cell=new PdfPCell(new Phrase(last_name));
                my_report_table.addCell(table_cell);
                String login = resultSet.getString("login");
                table_cell=new PdfPCell(new Phrase(login));
                my_report_table.addCell(table_cell);
                String gender = resultSet.getString("gender");
                table_cell=new PdfPCell(new Phrase(gender));
                my_report_table.addCell(table_cell);
                String isActive = resultSet.getString("isActive");
                table_cell=new PdfPCell(new Phrase(isActive));
                my_report_table.addCell(table_cell);
                String role = resultSet.getString("role");
                table_cell=new PdfPCell(new Phrase(role));
                my_report_table.addCell(table_cell);
                i++;
            }
            my_pdf_report.add(my_report_table);
            my_pdf_report.close();
        } catch (IOException | DocumentException | SQLException e) {
            e.printStackTrace();
        }finally {
            System.out.println("Your creation is done");
        }

    }

}
