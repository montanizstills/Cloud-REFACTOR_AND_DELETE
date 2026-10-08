package common.libraries.miscellaneous;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import common.utilities.UtilityScript;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class HVR {

	private static List<CSVRecord> sourcerecords;
	private static List<CSVRecord> destinationecords;
	private static HashMap<String, Integer> sourceColumns;
	private static HashMap<String, Integer> destinationColumns;
	public static HashMap<String, Integer> sourheadermap = new HashMap<String, Integer>();
	public static HashMap<String, Integer> destheadermap = new HashMap<String, Integer>();
	public static int ROW = 1;
	public static WritableWorkbook resultWritebook;
	public static WritableSheet resultWriteSheet;
	public static WritableSheet recordsreport;
	public static WritableSheet Columnsheet;
	public static ArrayList<String> HVRFile = new ArrayList<String>();
	public static ArrayList<String> OseriesFiles = new ArrayList<String>();
	public static int souSerachCloumnNo;
	public static int desSerachCloumnNo;
	public static String status = "";

	public static void main(String args[]) throws Exception {
		HVRComaprison();
	}

	public static void HVRComaprison() throws Exception {
		File directory = new File(".\\");
		ReadDir(directory.getCanonicalPath() + "\\resources\\testdata\\actual");
		for (int i = 0; i < HVRFile.size(); i++) {
			for (int j = 0; j < OseriesFiles.size(); j++) {
				if ((HVRFile.get(i).contains("transDetail") && OseriesFiles.get(j).contains("TransactionDetails"))
						|| (HVRFile.get(i).contains("transSummary")
								&& OseriesFiles.get(j).contains("TransactionSummary"))
						|| (HVRFile.get(i).contains("documentSummaryByJur")
								&& OseriesFiles.get(j).contains("DocumentSummaryByJurisdiction"))
						|| (HVRFile.get(i).contains("transAbbreviated")
								&& OseriesFiles.get(j).contains("TransactionAbbreviated"))
						|| (HVRFile.get(i).contains("jurisdictionSummary")
								&& OseriesFiles.get(j).contains("JurisdictionSummary"))) {
					destinationecords = readcsv(HVRFile.get(i));
					sourcerecords = readcsv(OseriesFiles.get(j));
					getcolumnvaluessour();
					getcolumnvaluesdest();
					createresultfile(HVRFile.get(i));
					souSerachCloumnNo = SearchwithCloumn(OseriesFiles.get(j), sourheadermap);
					desSerachCloumnNo = SearchwithCloumn(HVRFile.get(i), destheadermap);
					compareHeader();
					VerifyTotalRecords();
					comparecellvalues(HVRFile.get(i));
					flush();
				}
			}
		}
		/*
		 * File directory1 = new File (".//"); String strConsltFilePath =
		 * directory1.getCanonicalPath()+"\\resources\\testdata\\actual"; String dir =
		 * strConsltFilePath; String ZiPDir =
		 * dir+"\\"+"DownloadFiles_"+UtilityScript.RND()+".zip";
		 * CreateZipFolder.compressDirectory(dir, ZiPDir); Thread.sleep(3000); File
		 * fileDirectory = new File(dir); DeleteFolder.deleteFile(fileDirectory);
		 */
	}

	public static void compareHeader() throws RowsExceededException, WriteException {
		String MatchFound = "";
		ArrayList<String> MisCollist = new ArrayList<String>();
		columnsreportHeader();
		sourceColumns = headermappings(sourcerecords);
		destinationColumns = headermappings(destinationecords);
		for (Entry<String, Integer> entry : sourceColumns.entrySet()) {
			MatchFound = "";
			for (Entry<String, Integer> entry1 : destinationColumns.entrySet()) {
				entry1.getKey();
				if (entry.getKey().equalsIgnoreCase(entry1.getKey())) {
					MatchFound = "Pass";
					break;
				}
			}
			if (MatchFound.isEmpty()) {
				MisCollist.add(entry.getKey());
				status = "Fail";
				// writecolumnsreport(entry.getKey(), EXpClo, entry.getKey(),Row);

			}
		}
		writecolumnsreport(sourceColumns.size(), destinationColumns.size(), MisCollist);
	}

	@SuppressWarnings("unused")
	public static void VerifyTotalRecords() throws RowsExceededException, WriteException {
		int Row = 1;
		ArrayList<String> SitusTaxID = new ArrayList<String>();
		ArrayList<String> DestinationTID = new ArrayList<String>();
		ArrayList<String> SitusTaxID1 = new ArrayList<String>();
		for (int i = 1; i < sourcerecords.size(); i++) {
			SitusTaxID.clear();
			DestinationTID.clear();
			for (int j = 1; j < sourcerecords.size(); j++) {
				if (sourcerecords.get(i).get(souSerachCloumnNo)
						.equalsIgnoreCase(sourcerecords.get(j).get(souSerachCloumnNo))) {
					try {
						if (!(SitusTaxID1.get(0)
								.equalsIgnoreCase(sourcerecords.get(i).get(sourceColumns.get("Situs Tax Area ID"))))) {
							SitusTaxID.add(sourcerecords.get(i).get(sourceColumns.get("Situs Tax Area ID")));
						}
					} catch (Exception e) {
						SitusTaxID.add(sourcerecords.get(i).get(souSerachCloumnNo));
					}
				}
			}
			Destination: for (int j = 0; j < SitusTaxID.size(); j++) {
				SitusTaxID1.clear();
				for (int j2 = 1; j2 < destinationecords.size(); j2++) {
					if (destinationecords.get(j2).get(desSerachCloumnNo).equalsIgnoreCase(SitusTaxID.get(0))) {
						SitusTaxID1.add(destinationecords.get(j2).get(desSerachCloumnNo));
					}
				}
				if (SitusTaxID.size() != SitusTaxID1.size()) {
					status = "Fail";
					recordsreport.addCell(new Label(0, Row, SitusTaxID.get(0)));
					recordsreport.addCell(new Label(1, Row, "" + SitusTaxID.size() + ""));
					recordsreport.addCell(new Label(2, Row, "" + SitusTaxID1.size() + ""));
				}
				break Destination;
			}
		}
	}

	@SuppressWarnings("unused")
	public static void comparecellvalues(String Filename) throws IOException, RowsExceededException, WriteException {
		String allcolumnsmatched = "";
		ArrayList<Integer> k1 = new ArrayList<Integer>();
		ArrayList<Integer> j1 = new ArrayList<Integer>();
		int k = 0, j;
		Detination: for (int i = 1; i < destinationecords.size(); i++) {
			allcolumnsmatched = "";
			Source: for (j = 1; j < sourcerecords.size(); j++) {
				if (Filename.contains("transSummary") || Filename.contains("transDetail")) {
					if (ConvertCellData(destinationecords.get(i).get(desSerachCloumnNo))
							.equalsIgnoreCase(ConvertCellData(sourcerecords.get(j).get(souSerachCloumnNo)))) {
						Coulmns: for (k = 0; k < destinationecords.get(i).size(); k++) {

							if (k != sourheadermap.get("Partition ID")) { // TODO Need to skip Situs City Name
								if ((ConvertCellData(destinationecords.get(i).get(k)))
										.equalsIgnoreCase((ConvertCellData(sourcerecords.get(j).get(k))))) {
									// j1.clear();
									if (k == destinationecords.get(i).size() - 1) {
										allcolumnsmatched = "Pass";
										j1.clear();
										break Source;
									}
								} else {
									k1.add(k);
									j1.add(j);
									break Coulmns;
								}
							}
						}
					}
				} else {
					if (ConvertCellData(destinationecords.get(i).get(desSerachCloumnNo))
							.equalsIgnoreCase(ConvertCellData(sourcerecords.get(j).get(souSerachCloumnNo)))) {
						Coulmns: for (k = 0; k < destinationecords.get(i).size(); k++) {
							j1.add(j);
							if ((ConvertCellData(destinationecords.get(i).get(k)))
									.equalsIgnoreCase((ConvertCellData(sourcerecords.get(j).get(k))))) {
								j1.clear();
								if (k == destinationecords.get(i).size() - 1) {
									allcolumnsmatched = "Pass";
									j1.clear();
									break Source;
								}
							} else {
								k1.add(k);
								break Coulmns;
							}
						}
					}
				}
			}
			if (allcolumnsmatched.isEmpty()) {
				status = "Fail";
				Collections.sort(k1);
				Collections.sort(j1);
				int k2 = k1.get(k1.size() - 1);
				wrirteresultsdetailreport(ROW, destinationecords.get(i).get(desSerachCloumnNo), i + 1,
						destinationecords.get(0).get(k2), destinationecords.get(i).get(k2),
						sourcerecords.get(j1.get(0)).get(k2));
				k1.clear();
				j1.clear();
				ROW++;
			}
		}
	}

	public static void getcolumnvaluessour() {
		for (int i = 0; i < sourcerecords.get(0).size(); i++) {
			sourheadermap.put(sourcerecords.get(0).get(i), i);
		}
	}

	public static void getcolumnvaluesdest() {
		for (int i = 0; i < destinationecords.get(0).size(); i++) {
			destheadermap.put(destinationecords.get(0).get(i), i);
		}
	}

	public static String ConvertCellData(String value) throws IOException {
		BigDecimal b3;
		String Convertedvalue = "", VAL = "";
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Convertedvalue = dateFormat.format(dateFormat.parse(value.trim()));
			// dateFormat.parse(value.trim());
		} catch (ParseException pe) {
			try {
				value.toCharArray();
				if (Character.isDigit(value.charAt(0)) && !value.contains("-")) {
					if (value.contains(",")) {
						String[] a = value.split(",");
						for (int i = 0; i < a.length; i++) {
							VAL = VAL + a[i];
						}
					} else {
						VAL = value;
					}
					Double doubl = Double.parseDouble(VAL);
					BigDecimal bg = BigDecimal.valueOf(doubl);
					b3 = bg.movePointLeft(0);
					b3 = b3.setScale(2, BigDecimal.ROUND_HALF_UP);
					double value1 = Double.parseDouble(b3.toString());
					Convertedvalue = String.valueOf(value1);
				} else if (value.isEmpty()) {
					Convertedvalue = value;
				} else {
					Convertedvalue = value.replaceAll("\\s", "");
				}
			} catch (Exception e) {
				Convertedvalue = value;
			}
		}
		return Convertedvalue;
	}

	public static void wrirteresultsdetailreport(int Row, String TaxAreaID, int RowNumber, String CellName,
			String Actual, String Expected) throws IOException, RowsExceededException, WriteException {
		resultWriteSheet.addCell(new Label(0, Row, TaxAreaID));
		resultWriteSheet.addCell(new Label(1, Row, "" + RowNumber + ""));
		resultWriteSheet.addCell(new Label(2, Row, CellName));
		resultWriteSheet.addCell(new Label(3, Row, Actual));
		resultWriteSheet.addCell(new Label(4, Row, Expected));
	}

	public static void recordsReport(String FileName) throws RowsExceededException, WriteException {
		recordsreport = resultWritebook.createSheet("TotalRecordsResults", 0);
		if (FileName.contains("transDetail") || FileName.contains("transSummary")) {
			recordsreport.addCell(new Label(0, 0, "Situs Tax Area ID"));
		}
		if (FileName.contains("transAbbreviated") || FileName.contains("documentSummaryByJur")) {
			recordsreport.addCell(new Label(0, 0, "Document Number"));
		}
		if (FileName.contains("jurisdictionSummary")) {
			recordsreport.addCell(new Label(0, 0, "Jurisdiction Name"));
		}

		recordsreport.addCell(new Label(1, 0, "Number Records in Oseries File"));
		recordsreport.addCell(new Label(2, 0, "Number Records in HVR File"));
	}

	public static void columnsreportHeader() throws RowsExceededException, WriteException {
		Columnsheet = resultWritebook.createSheet("Columns Match", 0);
		Columnsheet.addCell(new Label(0, 0, "Total No Col in Oseries"));
		Columnsheet.addCell(new Label(1, 0, "Total No Col in HVR"));
		Columnsheet.addCell(new Label(2, 0, "Columns not found in HVR"));
	}

	public static void writecolumnsreport(int ColumnName, int Actual, ArrayList<String> MisCol)
			throws RowsExceededException, WriteException {
		// Columnsheet = resultWritebook.createSheet("Columns Match",0);
		Columnsheet.addCell(new Label(0, 1, "" + ColumnName + ""));
		Columnsheet.addCell(new Label(1, 1, "" + Actual + ""));
		if (MisCol.isEmpty()) {
			Columnsheet.addCell(new Label(2, 1, ""));
		} else {
			Columnsheet.addCell(new Label(2, 1, MisCol.toString()));
		}
	}

	public static void DetailReportHeader(String FileName) throws RowsExceededException, WriteException {
		resultWriteSheet = resultWritebook.createSheet("DetailReports", 1);
		if (FileName.contains("transDetail") || FileName.contains("transSummary")) {
			resultWriteSheet.addCell(new Label(0, 0, "Situs Tax Area ID"));
		}
		if (FileName.contains("transAbbreviated") || FileName.contains("documentSummaryByJur")) {
			resultWriteSheet.addCell(new Label(0, 0, "Document Number"));
		}
		if (FileName.contains("jurisdictionSummary")) {
			resultWriteSheet.addCell(new Label(0, 0, "Jurisdiction Name"));
		}
		resultWriteSheet.addCell(new Label(1, 0, "Row Number"));
		resultWriteSheet.addCell(new Label(2, 0, "Cell Name"));
		resultWriteSheet.addCell(new Label(3, 0, "Expected"));
		resultWriteSheet.addCell(new Label(4, 0, "Actual"));
	}

	public static void createresultfile(String FileName) throws Exception {
		String filename = "";
		if (FileName.contains("transDetail")) {
			filename = "TransactionDetails" + UtilityScript.xGetDateTimeSel();
		}
		if (FileName.contains("transSummary")) {
			filename = "TransactionSummary" + UtilityScript.xGetDateTimeSel();
		}
		if (FileName.contains("transAbbreviated")) {
			filename = "TransAbbreviated" + UtilityScript.xGetDateTimeSel();
		}
		if (FileName.contains("documentSummaryByJur")) {
			filename = "DocumentSummaryByJur" + UtilityScript.xGetDateTimeSel();
		}
		if (FileName.contains("jurisdictionSummary")) {
			filename = "JurisdictionSummary" + UtilityScript.xGetDateTimeSel();
		}

		File directory = new File(".//");
		File f = new File("HVRResults");
		f.mkdir();
		File f1 = new File("HVRResults\\HVRComparisonReports");
		f1.mkdir();
		resultWritebook = Workbook.createWorkbook(new File(
				directory.getCanonicalPath() + "\\HVRResults\\HVRComparisonReports\\" + "" + filename + ".xls"));
		recordsReport(FileName);
		DetailReportHeader(FileName);
	}

	public static void ReadDir(String Path) {
		File Dir = new File(Path);
		if (Dir.isDirectory()) {
			for (File Folders : Dir.listFiles()) {
				if (!Folders.getName().contains(".zip")) {
					for (File subFolders : Folders.listFiles()) {
						if (subFolders.isDirectory()) {
							String name = subFolders.getAbsolutePath()
									.substring(subFolders.getAbsolutePath().lastIndexOf("\\") + 1);
							GetFilePaths(name, subFolders);
						}
						for (File SF : subFolders.listFiles()) {
							if (SF.isDirectory()) {
								String name = SF.getAbsolutePath()
										.substring(SF.getAbsolutePath().lastIndexOf("\\") + 1);
								GetFilePaths(name, SF);
							}
						}
					}
				}
			}
		}
	}

	public static void GetFilePaths(String name, File Folder) {
		switch (name) {
		case "HVR":
			for (File subFolders : Folder.listFiles()) {
				if (subFolders.isFile()) {
					HVRFile.add(subFolders.toString());
				}
			}
			break;
		case "Oseries":
			for (File subFolders : Folder.listFiles()) {
				if (subFolders.isFile()) {
					OseriesFiles.add(subFolders.toString());
				}
			}
			break;
		}
	}

	public static int SearchwithCloumn(String FileName, HashMap<String, Integer> sourheadermap) {
		int SerachCloumnNo = 0;
		if (FileName.contains("ReturnsExport")) {
			SerachCloumnNo = 0;
		} else if (FileName.contains("TransactionAbbreviated") || FileName.contains("transAbbreviated")
				|| FileName.contains("documentSummaryByJur") || FileName.contains("DocumentSummaryByJurisdiction")) {
			SerachCloumnNo = sourheadermap.get("Document Number");
		} else if (FileName.contains("JurisdictionSummary") || FileName.contains("jurisdictionSummary")) {
			SerachCloumnNo = sourheadermap.get("Jurisdiction Name");
		} else {
			SerachCloumnNo = sourheadermap.get("Situs Tax Area ID");
		}
		return SerachCloumnNo;
	}

	public static List<CSVRecord> readcsv(String Filename) throws IOException {
		List<CSVRecord> records = null;
		try {
			/*
			 * List<String> alist=Files.lines(Paths.get(Filename)).filter( line->
			 * line.contains(" ")).collect(Collectors.toList()); System.out.println(alist);
			 * File file = null; LineIterator it = FileUtils.lineIterator(file,"UFT-8");
			 */
			Reader in = new FileReader(Filename);
			org.apache.commons.csv.CSVParser pasrse = CSVFormat.DEFAULT.parse(in);
			records = pasrse.getRecords();
			// System.out.println(records);
		} catch (Exception e) {
			System.out.println(e);
		}
		return records;
	}

	public static HashMap<String, Integer> headermappings(Iterable<CSVRecord> sourcerecords) {
		HashMap<String, Integer> Columns = new HashMap<String, Integer>();
		Records: for (CSVRecord read : sourcerecords) {
			for (int i = 0; i < read.size(); i++) {
				Columns.put(read.get(i), i);
			}
			break Records;
		}
		return Columns;
	}

	public static void flush() throws Exception {
		resultWritebook.write();
		resultWritebook.close();
		destinationecords.clear();
		sourcerecords.clear();
		sourheadermap.clear();
		sourceColumns.clear();
		destinationColumns.clear();
		ROW = 1;

	}

}
