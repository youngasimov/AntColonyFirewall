package antcolonyfirewall.input;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author Asimov
 */
public class ExcelReader implements Reader {

    private String file;
    private ExcelListener listener;

    public ExcelReader(String file){
        this.file = file;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public ExcelListener getListener() {
        return listener;
    }

    public void setListener(ExcelListener listener) {
        this.listener = listener;
    }

    public void read() {
        List<String> data;
        try{
            FileInputStream in = new FileInputStream(file);
            Workbook wb = WorkbookFactory.create(in);
            for (int k = 0; k < wb.getNumberOfSheets(); k++) {
                Sheet sheet = wb.getSheetAt(k);
                int rows = sheet.getPhysicalNumberOfRows();
                //System.out.println("Sheet " + k + " \"" + wb.getSheetName(k) + "\" has " + rows + " row(s).");
                for (int r = 0; r < rows; r++) {
                    Row row = sheet.getRow(r);
                    if (row == null) {
                            continue;
                    }
                    int cells = row.getPhysicalNumberOfCells();
                    data = new ArrayList<String>(cells);
                    //System.out.println("\nROW " + row.getRowNum() + " has " + cells + " cell(s).");
                    for (int c = 0; c < cells; c++) {
                        Cell cell = row.getCell(c);
                        String value = null;
                        if(cell == null){
                            continue;
                        }
                        listener.onReadCell(c, r, cell.toString());
                        data.add(cell.toString());
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_FORMULA:
                                    value = "FORMULA value=" + cell.getCellFormula();
                                    break;

                            case Cell.CELL_TYPE_NUMERIC:
                                    value = "NUMERIC value=" + cell.getNumericCellValue();
                                    break;

                            case Cell.CELL_TYPE_STRING:
                                    value = "STRING value=" + cell.getStringCellValue();
                                    break;

                            default:
                        }
                        //System.out.println("CELL col=" + cell.getColumnIndex() + " VALUE=" + value);
                    }
                    listener.onReadRow(r, data);
                }
                listener.SheetReaded(k);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}