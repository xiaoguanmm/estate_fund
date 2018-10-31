package com.upjf.fund.web.utils.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * excel工具类
 * @company upjf.com
 * @author guantong
 *
 */
public class ExcelUtils {
	
	/**Excel 2003版*/
	private static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
	
	/**Excel 2007版*/
	private static final String OFFICE_EXCEL_2007_POSTFIX = "xlsx";
	
	/**忽略校验字段*/
	public static String[] IGNORE_FILEDS = {"pid","createId","createDate","updateDate","updateId","status"};
	
	/**
	 * 获取文件类型
	 * @param fileName
	 * @return
	 */
	public static String getPostfix(String fileName){
		if(StringUtils.isEmpty(fileName)){
			return null;
		}
		return fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
	}
	
	/**
	 * 判断是否为Excel文件
	 * @param fileName
	 * @return
	 */
	public static boolean isExcelFile(String fileName){
		String postfix = getPostfix(fileName);
		return OFFICE_EXCEL_2003_POSTFIX.equals(postfix)||OFFICE_EXCEL_2007_POSTFIX.equals(postfix);
		
	}
	
	/**
	 * 根据自定义标题导出数据到Excel
	 * @param data 数据
	 * @param title 标题
	 * @param filedNames 导出数据的列名(与data中map的键对应)
	 * @param fileName
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public static void exportDataToExcel(List<Map<String,Object>> data,String[] title,String[] filedNames,String fileName, HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		if(title == null){
			throw new Exception("文件标题不能为空");
		}
		if(filedNames == null){
			throw new Exception("文件列名不能为空");
		}
		if(title.length != filedNames.length){
			throw new Exception("文件标题与列名长度不一致");
		}
		if(data == null){
			throw new Exception("数据为空");
		}
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		/*创建一个sheet*/
		HSSFSheet sheet = workbook.createSheet();
		
		/*创建首行 并设置首行文字样式*/
		HSSFRow headRow = sheet.createRow(0);
		HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); 
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short)12);
        font.setBold(true);
        style.setFont(font);
        
        HSSFCell cell = null;
        for(int i=0;i<title.length;i++){
            cell = headRow.createCell(i);
            sheet.setColumnWidth(i, (title[i].length()*2+4)*256);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }
        int rowNum = 1;
        for(Map<String,Object> map: data){
        	Set<String> keys = map.keySet();
        	HSSFRow row = sheet.createRow(rowNum);
            for(int i = 0;i<filedNames.length;i++){
            	cell=row.createCell(i);
            	for(String key:keys){
            		if(filedNames[i].equals(key)){
            			cell.setCellValue(convertDataToString(map.get(key)));
            			keys.remove(key);
            			break;
            		}
            	}
            }
            rowNum++;
        }
        /*冻结首行*/
        sheet.createFreezePane( 0, 1, 0, 1 );
        fileName = new String(fileName.getBytes(),"ISO8859-1");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
        response.addHeader("Pargam", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
        OutputStream os = response.getOutputStream();
        workbook.write(os);
        workbook.close();
        os.flush();
        os.close();
	}
	
	/**
	 * 根据模板导出数据到Excel
	 * @param data
	 * @param template
	 * @param request
	 * @param response
	 */
	public static void exportDataToExcel(List<Map<String,Object>> data,File template, HttpServletRequest request,HttpServletResponse response){
		
		
		
		
		
	}
	

	/**
	 * 读取Excel
	 * @param file
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> readExcel2Object(MultipartFile file,Class<T> t) throws Exception{
		Field[] fields = t.getDeclaredFields();
		List<String> fieldsNames = new ArrayList<String>();
		for(Field field:fields){
			if(!Modifier.isStatic(field.getModifiers())){
				fieldsNames.add(field.getName());
			}
		}
		fieldsNames.removeAll(Arrays.asList(IGNORE_FILEDS));
		Sheet sheet = getFirstSheet(file);
		Row row = sheet.getRow(0);
		if(!canTransform(row,fieldsNames)){
			throw new Exception(String.format("can't transform %s to %s", file.getName(),t.getName()));
		}
		/*解析Excel到t*/
		return transformData2Object(sheet,t);
	}
	
	/**
	 * 转换Excel到Object
	 * @param sheet
	 * @param t
	 */
	private static <T> List<T> transformData2Object(Sheet sheet, Class<T> t) {
		List<T> data = new ArrayList<T>();
		int end = sheet.getLastRowNum();
		Row headRow = getHeadRow(sheet);
		for(int i=1;i<=end;i++){
			Row currentRow = sheet.getRow(i);
			Cell cell = null;//单元格
			try {
				T obj = t.newInstance();
				for(int j=headRow.getFirstCellNum();j<headRow.getLastCellNum();j++){
					cell = currentRow.getCell(j);
					Field field = t.getDeclaredField(sheet.getRow(0).getCell(j).getStringCellValue());
					String type = field.getType().getName();
					Object value = parseValue(cell,type);
					field.setAccessible(true);
					field.set(obj, value);
				}
				data.add(obj); 
			} catch (InstantiationException e) {
				
				e.printStackTrace();
				
			} catch (IllegalAccessException e) {
				
				e.printStackTrace();
				
			} catch (NoSuchFieldException e) {
				
				e.printStackTrace();
				
			} catch (SecurityException e) {
				
				e.printStackTrace();
				
			}
		}
		return data;
	}
	
	public static List<Map<String,String>> readExcel(MultipartFile file){
		Sheet sheet = getFirstSheet(file);
		return transformData(sheet);
	}
	
	/**
	 * 将Excel转换为map数据
	 * @param sheet
	 * @return
	 */
	private static List<Map<String,String>> transformData(Sheet sheet){
		List<Map<String,String>> data = new ArrayList<Map<String,String>>();
		int end = sheet.getLastRowNum();
		Row headRow = getHeadRow(sheet);
		for(int i=1;i<=end;i++){
			Row currentRow = sheet.getRow(i);
			Cell cell = null;
			Map<String,String> rowData = new HashMap<String,String>();
			for(int j=headRow.getFirstCellNum();j<headRow.getLastCellNum();j++){
				cell = currentRow.getCell(j);
				String key = headRow.getCell(j).getStringCellValue();
				Object value = parseValue(cell,java.lang.String.class.getName());
				rowData.put(key, value.toString());
			}
			data.add(rowData);
		}
		
		return data;
	}
	
	private static Row getHeadRow(Sheet sheet){
		return sheet.getRow(0);
	}
	
	private static Sheet getFirstSheet(MultipartFile file){
		Sheet sheet = null;
		try {
			InputStream is = file.getInputStream();
			Workbook workbook = WorkbookFactory.create(is);
			//获取第一个Sheet(一般只操作第一个sheet)
			/*如果要解析所有sheet可以使用 workbook.getNumberOfSheets()*/
			sheet = workbook.getSheetAt(0);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sheet;
	}
	
	/**
	 * 转换数据为String 类型
	 * @param data
	 * @return
	 */
	private static String convertDataToString(Object data){
		
		if(data instanceof java.math.BigDecimal){
			return ((BigDecimal)data).toString();
		}
		if(data instanceof java.lang.Integer){
			return ((Integer)data).toString();
		}
		if(data instanceof java.lang.Double){
			return ((Double)data).toString();
		}
		if(data instanceof java.lang.Short){
			return ((Short)data).toString();
		}
		if(data instanceof java.lang.Character){
			return data.toString();
		}
		if(data instanceof java.util.Date){
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(data);
		}
		if(data instanceof java.lang.String){
			return (String)data;
		}
		return null;
	}

	/**
	 * 
	 * @param cell
	 * @param type
	 * @return
	 */
	private static Object parseValue(Cell cell, String type) {
			if(cell==null){
				return null;
			}
			/*将类型设置为String便于处理*/
			cell.setCellType(CellType.STRING);
			if(java.lang.String.class.getName().equals(type)){
				return cell.getStringCellValue();
			}
			if(java.lang.Integer.class.getName().equals(type)){
				return StringUtils.isEmpty(cell.getStringCellValue())?null:Integer.valueOf(cell.getStringCellValue());
			}
			if(java.lang.Short.class.getName().equals(type)){
				return StringUtils.isEmpty(cell.getStringCellValue())?null:Short.valueOf(cell.getStringCellValue());
			}
			if(java.lang.Long.class.getName().equals(type)){
				return StringUtils.isEmpty(cell.getStringCellValue())?null:Long.valueOf(cell.getStringCellValue());
			}
			if(java.util.Date.class.getName().equals(type)){
				return cell.getDateCellValue();
			}
			if(java.lang.Double.class.getName().equals(type)){
				return cell.getNumericCellValue();
			}
		
		return null;
	}

	/**
	 * 校验是否能转换为指定实体类
	 * @param row
	 * @param fieldsNames
	 * @return
	 */
	private static boolean canTransform(Row row, List<String> fieldsNames) {
		Cell cell = null;//单元格
		List<String> headFields = new ArrayList<String>();
		for(int i=row.getFirstCellNum();i<row.getLastCellNum();i++){
			cell = row.getCell(i);
			String value =cell.getStringCellValue();
			headFields.add(value);
		}
		return fieldsNames.containsAll(headFields)&&headFields.containsAll(fieldsNames);
	}
}
