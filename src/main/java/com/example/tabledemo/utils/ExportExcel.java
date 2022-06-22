package com.example.tabledemo.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import java.io.OutputStream;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.List;

public class ExportExcel {
    // 导出表的标题
    private String title;
    // 导出表的列名
    private String[] rowName;
    private String start;
    private String end;
    // 导出表的数据
    private List<Object[]> dataList = new ArrayList<Object[]>();

    // 构造函数，传入要导出的数据
    public ExportExcel(String title, String[] rowName,String start,String end, List<Object[]> dataList) {
        this.dataList = dataList;
        this.rowName = rowName;
        this.title = title;
        this.start=start;
        this.end=end;
    }

    // 导出数据
    public void export(OutputStream out) throws Exception {
        try {
            //1.创建一个workbook，一个workbook 对应一个Excel文件
            HSSFWorkbook workbook = new HSSFWorkbook();
            //2.创建 sheet excel中多个sheet组成一个excel文件 至少有一个sheet
            HSSFSheet sheet = workbook.createSheet(title);
            sheet.setColumnWidth(0,3000);
            sheet.setColumnWidth(1,8000);
            sheet.setColumnWidth(2,6000);
            sheet.setColumnWidth(3,8000);
            sheet.setColumnWidth(4,8000);
            sheet.setColumnWidth(5,8000);
            sheet.setColumnWidth(6,5000);
            sheet.setColumnWidth(7,5000);
            sheet.setColumnWidth(8,5000);
            sheet.setColumnWidth(9,5000);
            sheet.setColumnWidth(10,8000);
            sheet.setColumnWidth(11,8000);
            sheet.setColumnWidth(12,8000);
            sheet.setColumnWidth(13,5000);
            sheet.setColumnWidth(14,6000);
            sheet.setColumnWidth(15,6000);
            sheet.setColumnWidth(16,6000);
            sheet.setColumnWidth(17,6500);
            sheet.setColumnWidth(18,6000);
            sheet.setColumnWidth(19,8000);
            sheet.setColumnWidth(20,8000);
            sheet.setDefaultRowHeight((short) 500);

            //表名
            //3.在sheet中添加表头第0行
            HSSFRow rowm = sheet.createRow(0);
            //4.创建单元格
            HSSFCell cellTitle = rowm.createCell(0);
            //5.定义标题样式 和 数据样式
            HSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook);
            HSSFCellStyle style = this.getStyle(workbook);
            //合并单元格
            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (rowName.length-1)));
            cellTitle.setCellStyle(columnTopStyle);
            //6.给单元格设置值 设置标题
            if(!start.equals("")&&!start.equals("undefined")&&!end.equals("")&&!end.equals("undefined")){
                cellTitle.setCellValue(title+"("+start+" 至 "+end+")");
            }else{
                cellTitle.setCellValue(title);
            }
            //7.获取列标长度
            int columnNum = rowName.length;

            //字段名
            // 创建列 相当于一行 2代表第三行 因为上面的总标题占了两行为 0 1
            HSSFRow rowRowName = sheet.createRow(2);
            //8.将列标题设置到单元格中
            for (int n = 0; n < columnNum; n++) {
                HSSFCell cellRowName = rowRowName.createCell(n);
                cellRowName.setCellType(CellType.STRING);
                HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
                cellRowName.setCellValue(text);
                cellRowName.setCellStyle(style);
            }

            //数据
            //9.将数据设置到单元格中
            for (int i = 0; i < dataList.size(); i++) {
                Object[] obj = dataList.get(i);
                HSSFRow row = sheet.createRow(i + 3);
                for (int j = 0; j < obj.length; j++) {
                    HSSFCell cell = null;
                    cell = row.createCell(j, CellType.STRING);
                    if (!"".equals(obj[j]) && obj[j] != null) {
                        cell.setCellValue(obj[j].toString());
                    } else {
                        cell.setCellValue(" ");
                    }
                    cell.setCellStyle(style);
                }
            }
            HSSFRow row = sheet.createRow(dataList.size() + 3);
            HSSFCell cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("合计");
            cell.setCellStyle(style);
            //合计
            for(int k=6;k<19;k++){
                HSSFCell cell1=null;
                cell1 = row.createCell(k, CellType.STRING);
                Integer sum=0;
                for(int i=0;i<dataList.size();i++){
                    Object[] obj = dataList.get(i);
                    sum+=Integer.parseInt(obj[k].toString());
                }
                cell1.setCellValue(sum);
                cell1.setCellStyle(style);
            }
            //创建折叠组
            sheet.groupRow(3,dataList.size()+2);
            sheet.setRowGroupCollapsed(3,true);

            //10.输出Excel文件
            if (workbook != null) {
                try {
                    //10.将整个表格写出
                    workbook.write(out);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) { }
    }

    /**
     * 表格标题样式
     */
    public HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        // 设置字体大小
        font.setFontHeightInPoints((short) 18);
        // 设置字体颜色
        font.setColor(IndexedColors.BLACK.getIndex());
        // 字体加粗
        font.setBold(true);
        // 设置字体名字
        font.setFontName("Courier New");
        // 设置样式
        HSSFCellStyle style = workbook.createCellStyle();
//        // 设置标题背景色
//        style.setFillForegroundColor(IndexedColors.DARK_TEAL.getIndex());
//        // 设置背景颜色填充样式
//        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//        // 设置低边框
//        style.setBorderBottom(BorderStyle.THIN);
//        // 设置低边框颜色
//        style.setBottomBorderColor(IndexedColors.ROYAL_BLUE.getIndex());
//        // 设置右边框
//        style.setBorderRight(BorderStyle.THIN);
//        // 设置顶边框
//        style.setTopBorderColor(IndexedColors.ROYAL_BLUE.getIndex());
//        // 设置顶边框颜色
//        style.setTopBorderColor(IndexedColors.ROYAL_BLUE.getIndex());
        // 在样式中应用设置的字体
        style.setFont(font);
        // 设置自动换行
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐；
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    /**
     * 表格数据样式
     */
    public HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        // 设置字体大小
        font.setFontHeightInPoints((short) 12);
        // 设置字体名字
        font.setFontName("Courier New");
        // 设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置底边框;
//        style.setBorderBottom(BorderStyle.THIN);
//        // 设置底边框颜色;
//        style.setBottomBorderColor(IndexedColors.ROYAL_BLUE.getIndex());
//        // 设置左边框;
//        style.setBorderLeft(BorderStyle.THIN);
//        // 设置左边框颜色;
//        style.setLeftBorderColor(IndexedColors.ROYAL_BLUE.getIndex());
//        // 设置右边框;
//        style.setBorderRight(BorderStyle.THIN);
//        // 设置右边框颜色;
//        style.setRightBorderColor(IndexedColors.ROYAL_BLUE.getIndex());
//        // 设置顶边框;
//        style.setBorderTop(BorderStyle.THIN);
//        // 设置顶边框颜色;
//        style.setTopBorderColor(IndexedColors.ROYAL_BLUE.getIndex());
        // 在样式用应用设置的字体;
        style.setFont(font);
        // 设置自动换行;
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐;
        style.setAlignment(HorizontalAlignment.CENTER);
        // 设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }
}