package com.alshmowkh.exceloperations;

import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

public class ExcelFragment extends Fragment {
    private View layoutView;
    private String filename;
    private TextView lbl;
    private Utils utils;
    private TextView mText;
    private String pathFile;

    public ExcelFragment(String filename) {
        this.filename = filename;

    }

    //    @Override
//    public void onCreate(Bundle bundle) {
//        super.onCreate(bundle);
//        setContentView(R.layout.xml_display);
//       // layoutView=View.inflate(R.layout)
//    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedBundle) {

        layoutView = inflater.inflate(R.layout.excel_data, viewGroup, false);
        lbl = layoutView.findViewById(R.id.lbl1);
        lbl.setText("يامراحيب!");
        mText = layoutView.findViewById(R.id.multiText);
        pathFile = new StringBuilder().append(Environment.getExternalStorageDirectory().getAbsolutePath()).append("/").append(filename).toString();
        utils = new Utils(layoutView.getContext());
        showExcelContent();
        return layoutView;
    }

    void showExcelContent() {

        String path = new StringBuilder().append(Environment.getExternalStorageDirectory().getAbsolutePath()).append("/").append(filename).toString();
        path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + filename;
//       Workbook wb;
//        Sheet sheet;


        XSSFWorkbook xwb = null;


        try {
            xwb = new XSSFWorkbook(path);

        } catch (IOException e) {
            //utils.message(new StringBuilder().append("Error in read file").append(path).append(e.getMessage()).toString());
            utils.message("Error in read file" + path + e.getMessage());
            e.printStackTrace();
        }
        if (xwb == null) {
            return;
        }
        Sheet sheet = xwb.getSheetAt(1);
        String temp;
        Row row = sheet.getRow(2);
        temp = row.getCell(5).getStringCellValue() + "";
        for (Row row1 : sheet) {
            temp += "\n";
            for (Cell cell : row1) {

                if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                    temp += "\t" + cell.getStringCellValue();
                } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    temp += "\t" + cell.getNumericCellValue();
                } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
                    temp += "\t" + "       ";
                }

            }

        }
        mText.setText(temp);

    }

    //        void ini() throws IOException, WriteException, InterruptedException {


}
