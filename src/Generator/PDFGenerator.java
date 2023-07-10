/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Generator;
import Interface.OrderBillObserver;
import DTO.OrderBill_DTO;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


/**
 *
 * @author ACER
 */
public class PDFGenerator implements OrderBillObserver {
    @Override
    public void onPaymentSuccess(OrderBill_DTO orderBill) {
 try {
     
            // Prompt the user to choose the file path and name
            
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save PDF");
            // Set the default file name and extension
            String defaultFileName = "bill.pdf";
            fileChooser.setSelectedFile(new File(defaultFileName));
            // Set the file filter to restrict the file selection to only PDF files
            fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Files (*.pdf)", "pdf"));

            int userSelection = fileChooser.showSaveDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();

                // Create a new PDF document
                Document document = new Document();

                // Set the output file path and create a PdfWriter instance
                PdfWriter.getInstance(document, new FileOutputStream(filePath));

                // Open the document
                document.open();

                // Add the table name and total to the document
                String tableName = "Table: " + orderBill.getIdTable();
                String totalAmount = "Total: $" + orderBill.getTotal();

                document.add(new Paragraph(tableName));
                document.add(new Paragraph(totalAmount));

                // Close the document
                document.close();

                System.out.println("PDF file generated successfully.");
            }

        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
