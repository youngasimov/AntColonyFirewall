/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antcolonyfirewall.input;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author camilovera
 */
public class ExcelWriter {
    
    
    private List<List<String>> data;
    private String algoritm;
    
    public ExcelWriter(String algoritm){
        this.algoritm = algoritm;
        data = new ArrayList<List<String>>();
    }
    
    public void addRow(List<String> row){
        data.add(row);
    }
    
    public void write(){
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        String file = "fs_"+this.algoritm+"_"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DAY_OF_MONTH)+"_"+cal.get(Calendar.HOUR_OF_DAY)+"-"+cal.get(Calendar.MINUTE)+"-"+cal.get(Calendar.SECOND)+".xlsx";
        System.out.println("creando documento: "+file);
        try{
            // create a new file
            
            FileOutputStream out = new FileOutputStream(file);
            // create a new workbook
            Workbook wb = new XSSFWorkbook();
            // create a new sheet
            Sheet s = wb.createSheet();
            // declare a row object reference
            Row r = null;
            // declare a cell object reference
            Cell c = null;
            CellStyle cs = wb.createCellStyle();
            // create 2 fonts objects
            Font f = wb.createFont();
            DataFormat df = wb.createDataFormat();
            //set font 1 to 12 point type
            f.setFontHeightInPoints((short) 12);
            //make it blue
            f.setColor( (short)0xc );
            // make it bold
            //arial is the default font
            f.setBoldweight(Font.BOLDWEIGHT_BOLD);

            //set cell stlye
            cs.setFont(f);

            // set the sheet name in Unicode
            //wb.setSheetName(0, "\u0422\u0435\u0441\u0442\u043E\u0432\u0430\u044F " + 
            //                   "\u0421\u0442\u0440\u0430\u043D\u0438\u0447\u043A\u0430" );
            // in case of plain ascii
            wb.setSheetName(0, "Busqueda");
            // create a sheet with 30 rows (0-29)
            int rownum;
            for (rownum = 0; rownum < data.size(); rownum++){
                // create a row
                r = s.createRow(rownum);

                //r.setRowNum(( short ) rownum);
                // create 10 cells (0-9) (the += 2 becomes apparent later
                for (short cellnum = (short) 0; cellnum < data.get(rownum).size(); cellnum++){
                    // create a numeric cell
                    c = r.createCell(cellnum);
                    // do some goofy math to demonstrate decimals
                    c.setCellValue(data.get(rownum).get(cellnum));

                    // make this column a bit wider
                    s.setColumnWidth((short) (cellnum + 1), (short) ((50 * 8) / ((double) 1 / 20)));
                }
            }

            // write the workbook to the output stream
            // close our file (don't blow out our file handles
            wb.write(out);
            out.close();
        }catch(Exception e){
            System.out.println("arror al crear documento excel");
            e.printStackTrace();
            //log.error("error al crear un documento excel",e.fillInStackTrace());
        }finally{
            data.clear();
        }

    }
}
