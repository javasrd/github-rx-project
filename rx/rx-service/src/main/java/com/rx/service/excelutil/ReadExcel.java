package com.rx.service.excelutil;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSON;
import com.rx.entity.Drug;
import com.rx.entity.User;

/**
 * @author srd
 * @created 2016-11-07
 */
public class ReadExcel {
    
	private static final Log log = LogFactory.getLog(ReadExcel.class);
	
    /**
     * read the Excel file
     * @param reqMap 
     * 		excelPath:the reqMap of the Excel file
     * @return
     * @throws IOException
     */
    public static Map<String, Object> readExcel(Map<String, Object> reqMap) throws IOException {
    	Map<String, Object> respMap = new HashMap<String, Object>();
    	String path = reqMap.get(Common.EXCEL_PATH).toString();
    	if (path == null || Common.EMPTY.equals(path)) {
        	respMap.put("result_code", "fail");
			respMap.put("result_err_msg", "上传excel文件URL为空");
			return respMap;
        } else {
            String postfix = Util.getPostfix(path);
            if (!Common.EMPTY.equals(postfix)) {
                if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
                    return readXls(reqMap);
                } else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
                    return readXlsx(reqMap);
                }
            } else {
                log.error(path + Common.NOT_EXCEL_FILE);
            }
        }
        respMap.put("result_code", "fail");
		respMap.put("result_err_msg", "解析excel文件异常");
        return respMap;
    }

    /**
     * Read the Excel 2010
     * @param path the path of the excel file
     * @return
     * @throws IOException
     */
    @SuppressWarnings("resource")
	public static Map<String, Object> readXlsx(Map<String, Object> reqMap) throws IOException {
        log.info(Common.PROCESSING + reqMap.get(Common.EXCEL_PATH).toString());
        InputStream is = new FileInputStream(reqMap.get(Common.EXCEL_PATH).toString());
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        try {
        	return parseExcelInfoXlsx(xssfWorkbook);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("解析excel异常", e);
		}
        	
    	Map<String, Object> respMap = new HashMap<String, Object>();
        respMap.put("result_code", "fail");
		respMap.put("result_err_msg", "解析excel异常");
		return respMap;
        
    }
    
    /**
     * 方法功能：解析扩展名为xlsx的文件信息excel文件
     * @param xssfWorkbook
     * @return
     * <hr>
     * <b>描述：</b>
     * <p>Description:方法功能详细说明</p> 
     * <p>Version: 1.0</p>
     * <p>Author: srd </p>
     * <p>Date: 2016年11月21日 下午5:09:17</p>
     */
    private static Map<String, Object> parseExcelInfoXlsx(XSSFWorkbook xssfWorkbook){
    	Map<String, Object> respMap = new HashMap<String, Object>();
        
        Drug drug = null;
        List<Drug> drugList = new ArrayList<Drug>();
        
        int sheetSize = xssfWorkbook.getNumberOfSheets();
        log.info("Excel sheet num : "+sheetSize);
        // Read the Sheet
        for (int numSheet = 0; numSheet < sheetSize; numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            int rowSize = xssfSheet.getLastRowNum();
            log.info("Excel sheet row num : "+rowSize);
            // Read the Row
            for (int rowNum = 1; rowNum <= rowSize; rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
                	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                	//xssfRow.getLastCellNum();
                	try {
                		drug = new Drug();
                		String wareid = getValue(xssfRow.getCell(0));//商品ID
                		String barcode = getValue(xssfRow.getCell(1));//条形码
                		String abc = getValue(xssfRow.getCell(2));//助记码
                		String warename = getValue(xssfRow.getCell(3));//药品名称
                		String waresimname = getValue(xssfRow.getCell(4));//通用名
                		String warespec = getValue(xssfRow.getCell(5));//规格
                		String prodAddr = getValue(xssfRow.getCell(6));//产地
                		String producer = getValue(xssfRow.getCell(7));//生产厂家
                		String wareunit = getValue(xssfRow.getCell(8));//单位
                		String status = getValue(xssfRow.getCell(9));//在售/停售状态  1：在售； 2：停售
                		String saleprice = getValue(xssfRow.getCell(10));//售价
                		drug.setWareid(wareid);
                		drug.setBarcode(barcode);
                		drug.setAbc(abc);
                		drug.setWarename(warename);
                		drug.setWaresimname(waresimname);
                		drug.setWarespec(warespec);
                		drug.setWareunit(wareunit);
                		drug.setProdAddr(prodAddr);
                		drug.setProducer(producer);
                		drug.setStatus((byte)Integer.valueOf(status).intValue());
                		drug.setSaleprice(new BigDecimal(saleprice));
                    	drugList.add(drug);
                    	
					} catch (Exception e) {
						e.printStackTrace();
						log.error("解析excel异常，请求检查 "+xssfWorkbook.getSheetName(numSheet)+" 工作表，第 "+(rowNum+1)+" 行数据。", e);
						respMap.put("result_code", "fail");
						respMap.put("result_err_msg", "解析excel异常，请求检查 "+xssfWorkbook.getSheetName(numSheet)+" 工作表，第 "+(rowNum+1)+" 行数据。");
						return respMap;
					}
                }
            }
        }
        respMap.put("result_code", "success");
        respMap.put("result_msg", "解析入住信息excel文件成功");
        log.info("drugList: "+JSON.toJSONString(drugList));
        respMap.put("drugList", JSON.toJSONString(drugList));
        return respMap;
    }
    
    
    /**
     * Read the Excel 2003-2007
     * @param path the path of the Excel
     * @return
     * @throws IOException
     */
    @SuppressWarnings("resource")
	public static Map<String, Object> readXls(Map<String, Object> reqMap) throws IOException {
        log.info(Common.PROCESSING + reqMap.get(Common.EXCEL_PATH).toString());
        InputStream is = new FileInputStream(reqMap.get(Common.EXCEL_PATH).toString());
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        try {
        	return parseExcelInfoXls(hssfWorkbook);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("解析excel异常", e);
		}
        	
        Map<String, Object> respMap = new HashMap<String, Object>();
        respMap.put("result_code", "fail");
		respMap.put("result_err_msg", "解析excel异常");
		return respMap;
        
    }

    /**
     * 方法功能：解析扩展名为xls的入住信息excel文件
     * @param hssfWorkbook
     * @return
     * <hr>
     * <b>描述：</b>
     * <p>Description:方法功能详细说明</p> 
     * <p>Version: 1.0</p>
     * <p>Author: srd </p>
     * <p>Date: 2016年11月21日 下午5:05:53</p>
     */
    private static Map<String, Object> parseExcelInfoXls(HSSFWorkbook hssfWorkbook){
    	Map<String, Object> respMap = new HashMap<String, Object>();
        
    	Drug drug = null;
        List<Drug> drugList = new ArrayList<Drug>();
        
        int sheetSize = hssfWorkbook.getNumberOfSheets();
        log.info("Excel sheet num : "+sheetSize);
        // Read the Sheet
        for (int numSheet = 0; numSheet < sheetSize; numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            int rowSize = hssfSheet.getLastRowNum();
            log.info("Excel sheet row num : "+rowSize);
            // Read the Row
            for (int rowNum = 1; rowNum <= rowSize; rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                	
                	try {
                		drug = new Drug();
                		String wareid = getValue(hssfRow.getCell(0));//商品ID
                		String barcode = getValue(hssfRow.getCell(1));//条形码
                		String abc = getValue(hssfRow.getCell(2));//助记码
                		String warename = getValue(hssfRow.getCell(3));//药品名称
                		String waresimname = getValue(hssfRow.getCell(4));//通用名
                		String warespec = getValue(hssfRow.getCell(5));//规格
                		String prodAddr = getValue(hssfRow.getCell(6));//产地
                		String producer = getValue(hssfRow.getCell(7));//生产厂家
                		String wareunit = getValue(hssfRow.getCell(8));//单位
                		String status = getValue(hssfRow.getCell(9));//在售/停售状态  1：在售； 2：停售
                		String saleprice = getValue(hssfRow.getCell(10));//售价
                		drug.setWareid(wareid);
                		drug.setBarcode(barcode);
                		drug.setAbc(abc);
                		drug.setWarename(warename);
                		drug.setWaresimname(waresimname);
                		drug.setWarespec(warespec);
                		drug.setWareunit(wareunit);
                		drug.setProdAddr(prodAddr);
                		drug.setProducer(producer);
                		drug.setStatus((byte)Integer.valueOf(status).intValue());
                		drug.setSaleprice(new BigDecimal(saleprice));
                    	drugList.add(drug);
                    	
					} catch (Exception e) {
						e.printStackTrace();
						log.error("解析excel异常，请求检查 "+hssfSheet.getSheetName()+" 工作表，第 "+(rowNum+1)+" 行数据。", e);
						respMap.put("result_code", "fail");
						respMap.put("result_err_msg", "解析excel异常，请求检查 "+hssfSheet.getSheetName()+" 工作表，第 "+(rowNum+1)+" 行数据。");
						return respMap;
					}
                }
            }
        }
        respMap.put("result_code", "success");
        respMap.put("result_msg", "解析入住信息excel文件成功");
        log.info("drugList: "+JSON.toJSONString(drugList));
        respMap.put("drugList", JSON.toJSONString(drugList));
        return respMap;
    }
    
    /**
     * 判断并获取EXCEL单元格内容
     * 
     * @param xssfRow
     * @return
     */
    @SuppressWarnings("static-access")
    private static String getValue(XSSFCell xssfRow) {
    	try {
    		DecimalFormat df = new DecimalFormat("#");
            if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
                return df.format(xssfRow.getBooleanCellValue());
            } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
            	if(DateUtil.isCellInternalDateFormatted(xssfRow)){
            		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            		return String.valueOf(sdf.format(xssfRow.getDateCellValue()));
            	}
            	return new BigDecimal(xssfRow.getNumericCellValue()).toPlainString();
            } else {
                return String.valueOf(xssfRow.getStringCellValue());
            }
		} catch (Exception e) {
			log.info("判断并获取EXCEL单元格内容出错 或单元格内容为空", e);
		}
    	
    	return null;
    	
    }

    /**
     * 判断并获取EXCEL单元格内容
     * 
     * @param hssfCell
     * @return
     */
    @SuppressWarnings("static-access")
    private static String getValue(HSSFCell hssfCell) {
    	try {
    		DecimalFormat df = new DecimalFormat("#");
            if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
                return df.format(hssfCell.getBooleanCellValue());
            } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            	if(DateUtil.isCellInternalDateFormatted(hssfCell)){
            		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            		return String.valueOf(sdf.format(hssfCell.getDateCellValue()));
            	}
            	/*String temp = String.valueOf(hssfCell.getStringCellValue());
            	if(temp.indexOf(".")>0){
            		return new BigDecimal(hssfCell.getNumericCellValue()).toPlainString();
            	}else{
            		return Integer.valueOf(temp);
            	}*/
            	return new BigDecimal(hssfCell.getNumericCellValue()).toPlainString();
            } else {
                return String.valueOf(hssfCell.getStringCellValue());
            }
		} catch (Exception e) {
			log.info("判断并获取EXCEL单元格内容出错", e);
		}
    	return null;
    	
    }
    
    /**
     * main方法
     * @param args
     */
    public static void main(String[] args) {
    	//System.out.println(HSSFCell.CELL_TYPE_STRING);
    	//System.out.println(Double.valueOf("8.0").intValue());
    	String s1 = "10.0";
    	String s2 = "房间1";
    	
    	String s3 = null;
    	try {
    		s3 = String.valueOf(Double.valueOf(s1).intValue());
    		System.out.println("try");
		} catch (Exception e) {
			s3 = String.valueOf(Double.valueOf(s1).toString());
			System.out.println("catch");
		}
    	
    	System.out.println(s3);
    	
    	DecimalFormat df = new DecimalFormat("0");  
    	  
    	String whatYourWant = df.format(Double.valueOf("10.0")); 
    	System.out.println(whatYourWant);
    	
    	/*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currdate = null;
		try {
			currdate = format.parse("2016-10-09 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
        System.out.println("现在的日期是：" + format.format(currdate));
        Calendar ca = Calendar.getInstance();
        ca.setTime(currdate);
        ca.add(Calendar.MONTH, 2);// num为增加的天数，可以改变的
        currdate = ca.getTime();
        String enddate = format.format(currdate);
        System.out.println("增加天数以后的日期：" + enddate);*/
	}
}
