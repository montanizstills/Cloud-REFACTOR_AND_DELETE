package common.utilities;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * 
 * @author akhan
 *
 */
public class CloudData extends UtilityScript {

	public static Sheet sheet;
	public static HashMap<String, Integer> map;
	public static String[] Data;
	public static int start_row = 0;
	public static int end_row = 0;
	public static int Data_Row = 0;

	/**
	 * 
	 * @param cloumnname
	 * @return Cell Value of Particular Row
	 * @throws BiffException
	 * @throws IOException
	 */
	public static String get_data(String cloumnname) throws BiffException, IOException {
		return sheet.getCell(map.get(cloumnname), Data_Row).getContents();
	}

	/**
	 * Maps all Header Columns List Values to respective Cell Numbers
	 */
	public static void set_coulmns_list() {
		map = new HashMap<String, Integer>();
		int clo = sheet.getColumns();
		int i = 0;
		while (i < clo) {
			if (sheet.getCell(i, 0).getContents().isEmpty())
				break;
			map.put(sheet.getCell(i, 0).getContents().trim(), i);
			i++;

		}
	}

	/**
	 * 
	 * @param value : Method Name; Sheet Name ; Row Numbers
	 * @throws Exception
	 */
	public static void load_data(String TestPath,String value) throws Exception {
		clean_data();
		Data = value.split(";");
		Workbook WorkBook = Workbook.getWorkbook(new File(TestPath));
		sheet = WorkBook.getSheet(Data[1]);
		set_coulmns_list();
		set_rows();
	}

	/**
	 * Clean Up all the Variables Once Data.load() method is called, so that no
	 * discrepancies happen with map
	 */
	public static void clean_data() {
		if (map != null & sheet != null & Data != null) {
			sheet = null;
			map.clear();
			Data_Row = 0;
			Data = null;
			start_row = 0;
			end_row = 0;
			map = null;
		}
	}

	/**
	 * Sets Start and End Row
	 */
	public static void set_rows() {
		if (Data[2].contains(":")) {
			String[] stdata = Data[2].split(":");
			start_row = Integer.parseInt(stdata[0]);
			end_row = Integer.parseInt(stdata[1]);
		} else {
			start_row = 2;
			end_row = Data.length - 1;
			Data_Row = Integer.parseInt(Data[2]);
		}
	}

	/**
	 * 
	 * @param i : Row Value(Should be given within the Loop so the Data Row Changes
	 *          as per Loop
	 */
	public static void set_data_row(int i) {
		if (CloudData.Data[2].contains(":")) {
			Data_Row = i;
		} else {
			Data_Row = Integer.parseInt(Data[i]);
		}
	}

	/**
	 * 
	 * @param value : Method Name; Sheet Name ; Row Numbers
	 * @throws Exception
	 */
	public static void dataload(String TestPath, String value) throws Exception {
		clean_data();
		Data = value.split(";");
		Workbook WorkBook = Workbook.getWorkbook(new File(TestPath));
		sheet = WorkBook.getSheet(Data[0]);
		set_coulmns_list();
		rowsset();
	}

	/**
	 * Sets Start and End Row
	 */
	public static void rowsset() {
		if (Data[1].contains(":")) {
			String[] stdata = Data[1].split(":");
			start_row = Integer.parseInt(stdata[0]);
			end_row = Integer.parseInt(stdata[1]);
		} else {
			start_row = 1;
			end_row = Data.length - 1;
			Data_Row = Integer.parseInt(Data[1]);
		}
	}

	/**
	 * 
	 * @param i : Row Value(Should be given within the Loop so the Data Row Changes
	 *          as per Loop
	 */
	public static void setrowdata(int i) {
		if (CloudData.Data[1].contains(":")) {
			Data_Row = i;
		} else {
			Data_Row = Integer.parseInt(Data[i]);
		}
	}
}
