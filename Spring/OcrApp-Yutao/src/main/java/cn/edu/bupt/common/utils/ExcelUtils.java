package cn.edu.bupt.common.utils;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.bupt.OcrApp.domain.generated.CharacterObservation;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 导入包含合并单元格的Excel文档
 *
 */
public class ExcelUtils {
 
    public static void main(String[] args) throws FileNotFoundException {
    	ExcelUtils ti = new ExcelUtils();
    	InputStream input = null;
    	Workbook workbook = null;
    	try {
    		// 获取文件流
    		input = new FileInputStream(new File("C:\\Users\\49522\\Desktop\\大白菜性状调查表（20200205）.xlsx"));
    		// 读取Excel文件工作表
    		workbook = ti.readFile(input, "大白菜性状调查表（20200205）.xlsx");
    		//取得第一个工作表
    		Sheet sheet = workbook.getSheetAt(0);
    		// 获取工作表数据
//    		List<Map<Integer,Object>> list = ti.getBody(sheet,6,20);
			List<Map<String,Object>> list = ti.getBody2(sheet,6,20);
			List<CharacterObservation> characterObservationList = new ArrayList<>();
    		for(Map<String,Object> item : list) {
//    			System.out.println(item);
				CharacterObservation characterObservation = BeanMapUtils.mapToBean(item, CharacterObservation.class);
				characterObservationList.add(characterObservation);
				//System.out.println(characterObservation);

			}
			for (CharacterObservation characterObservation : characterObservationList) {
				System.out.println(characterObservation);
			}
    	} catch (Exception e1) {
    		e1.printStackTrace();
    	} finally {
    		try {
    			if (null != workbook) {
    				workbook.close();
    			}
    			if(null != input) {
    				input.close();
    			}
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    }

	public static List<CharacterObservation> getCharacterObservationList(){
		ExcelUtils ti = new ExcelUtils();
		InputStream input = null;
		Workbook workbook = null;
		try {
			// 获取文件流
			input = new FileInputStream(new File("C:\\Users\\49522\\Desktop\\大白菜性状调查表（20200205）.xlsx"));
			// 读取Excel文件工作表
			workbook = ti.readFile(input, "大白菜性状调查表（20200205）.xlsx");
			//取得第一个工作表
			Sheet sheet = workbook.getSheetAt(0);
			// 获取工作表数据
//    		List<Map<Integer,Object>> list = ti.getBody(sheet,6,20);
			List<Map<String,Object>> list = ti.getBody2(sheet,6,20);
			List<CharacterObservation> characterObservationList = new ArrayList<>();
			for(Map<String,Object> item : list) {
//    			System.out.println(item);
				CharacterObservation characterObservation = BeanMapUtils.mapToBean(item, CharacterObservation.class);
				characterObservationList.add(characterObservation);
			}
			return  characterObservationList;
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (null != workbook) {
					workbook.close();
				}
				if(null != input) {
					input.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}


 
    /**
     * 读取Excel文件工作表
     * @param inputStream 文件流
     * @param fileName 文件名称
     * @return
     */
    public Workbook readFile(InputStream input,String fileName){
    	Workbook wb  = null;
 
    	//判断是否是excel2007格式
    	boolean isE2007 = false;
    	if(fileName.endsWith("xlsx")){
    		isE2007 = true;
    	}
 
    	try {
    		//根据文件格式(2003或者2007)来初始化
    		if(isE2007){
    			wb = new XSSFWorkbook(input);
    		} else {
    			wb = new HSSFWorkbook(input);
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return wb;
    }
 
    /**
     * 获取内部内容
	 * @param sheet 工作表
	 * @param column 有效数据列数
	 * @param headerCount 表头行数
	 * @return
	 */
    public List<Map<Integer,Object>> getBody(Sheet sheet, int column, int headerCount){
    	//总行数  
    	int count = sheet.getLastRowNum() + 1;
 
    	//sheet中所有合并的单元格信息
    	List<CellRangeAddress> cras = getCombineCell(sheet);
 
    	//存储行信息
    	List<Map<Integer,Object>> irs = new ArrayList<>();
 
		//表头只有2行
		for(int i = headerCount; i < count;i++){
			//存储列信息
			Map<Integer,Object> map = new HashMap<Integer,Object>();
 
			for(int x = 0;x < column;x++) {
				// 判断当前处理单元格是不是合并单元格
				CellRangeAddress range = isMergedRegion(cras,i,x);
 
				// 如果当前单元格属于合并单元格
				if(null != range){
					//拿到合并单元格的开始行数和开始列数
					int firstColumn = range.getFirstColumn();
					int firstRow = range.getFirstRow();
 
					//根据开始行数和开始列数获取单元格的值
					Row row = sheet.getRow(firstRow);
					map.put(x,getCellValue(row.getCell(firstColumn)));
				} else {
					//如果不是合并单元格，直接拿当前单元格的值
					Row row = sheet.getRow(i);
					map.put(x,getCellValue(row.getCell(x)));
				}
			}
			irs.add(map);
		}
		return irs;
	}

	public List<Map<String,Object>> getBody2(Sheet sheet, int column, int headerCount){
		//总行数
		int count = sheet.getLastRowNum() + 1;

		//sheet中所有合并的单元格信息
		List<CellRangeAddress> cras = getCombineCell(sheet);

		//存储行信息
		List<Map<String,Object>> irs = new ArrayList<>();

		//表头只有2行
		for(int i = headerCount; i < count;i++){
			//存储列信息
			Map<String,Object> map = new HashMap<String, Object>();

			for(int x = 0;x < column;x++) {
				// 判断当前处理单元格是不是合并单元格
				CellRangeAddress range = isMergedRegion(cras,i,x);

				// 如果当前单元格属于合并单元格
				if(null != range){
					//拿到合并单元格的开始行数和开始列数
					int firstColumn = range.getFirstColumn();
					int firstRow = range.getFirstRow();

					//根据开始行数和开始列数获取单元格的值
					Row row = sheet.getRow(firstRow);
					if(x==0){
						map.put("observationPeriod",getCellValue(row.getCell(firstColumn)));
					}else if(x==1){
						map.put("characteristics",getCellValue(row.getCell(firstColumn)));
					}else if(x==2){
						map.put("specificCharacter",getCellValue(row.getCell(firstColumn)));
					}else if(x==3){
						map.put("description",getCellValue(row.getCell(firstColumn)));
					}else if(x==4){
						map.put("measurementBasis",getCellValue(row.getCell(firstColumn)));
					}else if(x==5){
						map.put("observationMethod",getCellValue(row.getCell(firstColumn)));
					}

				} else {
					//如果不是合并单元格，直接拿当前单元格的值
					Row row = sheet.getRow(i);
					if(x==0){
						map.put("observationPeriod",getCellValue(row.getCell(x)));
					}else if(x==1){
						map.put("characteristics",getCellValue(row.getCell(x)));
					}else if(x==2){
						map.put("specificCharacter",getCellValue(row.getCell(x)));
					}else if(x==3){
						map.put("description",getCellValue(row.getCell(x)));
					}else if(x==4){
						map.put("measurementBasis",getCellValue(row.getCell(x)));
					}else if(x==5){
						map.put("observationMethod",getCellValue(row.getCell(x)));
					}
				}
			}
			irs.add(map);
		}
		return irs;
	}
 
	/**  
	 * 获取sheet中合并的单元格信息，并返回合并的单元格list
	 * @param sheet  需要导入的工作表
	 * @return List<CellRangeAddress> 合并的单元格list
	 */
	public List<CellRangeAddress> getCombineCell(Sheet sheet){
		List<CellRangeAddress> list = new ArrayList<CellRangeAddress>();
		//获得一个 sheet 中合并单元格的数量
		int sheetmergerCount = sheet.getNumMergedRegions();
		//遍历所有的合并单元格
		for(int i = 0; i<sheetmergerCount;i++)
		{
			//获得合并单元格保存进list中
			CellRangeAddress ca = sheet.getMergedRegion(i);
			list.add(ca);
		}
		return list;
	}
 
	/**   
	 * 判断指定的单元格是否是合并单元格   
	 * @param cras 合并的单元格list
	 * @param row 行下标
	 * @param column 列下标
	 * @return   
	 */                                                                                
	private CellRangeAddress isMergedRegion(List<CellRangeAddress> cras,int row ,int column) {
		for (CellRangeAddress range : cras) {
			int firstColumn = range.getFirstColumn();
			int lastColumn = range.getLastColumn();
			int firstRow = range.getFirstRow();
			int lastRow = range.getLastRow();
			if(row >= firstRow && row <= lastRow){
				if(column >= firstColumn && column <= lastColumn){
					return range;
				}
			}
		}
		return null;
	}
	
	/**
	 * 获取单元格的值
	 * @param cell
	 * @return
	 */
	public String getCellValue(Cell cell) {
		String cellValue = "";
		if (cell == null) {
			return cellValue;
		}
		// 判断数据的类型
		switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC: // 数字
				if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式
					Date date = cell.getDateCellValue(); 
					DateFormat formater = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					cellValue = formater.format(date);
				} else if (cell.getCellStyle().getDataFormat() == 0) {//处理数值格式
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cellValue = String.valueOf(cell.getRichStringCellValue().getString());
				}
				break;
			case Cell.CELL_TYPE_STRING: // 字符串
				cellValue = String.valueOf(cell.getStringCellValue());
				break;
			case Cell.CELL_TYPE_BOOLEAN: // Boolean
				cellValue = String.valueOf(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_FORMULA: // 公式
				cellValue = String.valueOf(cell.getCellFormula());
				break;
			case Cell.CELL_TYPE_BLANK: // 空值
				cellValue = "";
				break;
			case Cell.CELL_TYPE_ERROR: // 故障
				cellValue = "";
				break;
			default: // 未知类型
				cellValue = cell.toString().trim();
				break;
		}
		return cellValue;
	}
 
}