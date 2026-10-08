package common.testng;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class DynamicTestNG {

	public static void search(final String pattern, final File folder, List<String> result) {
		for (final File f : folder.listFiles()) {

			if (f.isDirectory()) {
				search(pattern, f, result);
			}

			if (f.isFile()) {
				if (f.getName().matches(pattern)) {
					result.add(f.getName());
				}
			}

		}
	}

	public static void main(String[] args) throws IOException {
		try {
			File file = new File(".//");
			String mainfolder = file.getCanonicalPath();
			String[] tName = args[0].split(Pattern.quote("."));
			String classFilePath = null;

			List<String> result = new ArrayList<>();

			Boolean osci = System.getProperty("osci").equals("true") ? true : false;

			if (tName.length > 2) {
				classFilePath = mainfolder + "\\vertex.cloud\\" + tName[0].toString() + "\\" + tName[1].toString();
				for (int i = 2; i < tName.length; i++) {
					result.add(tName[i].toString());
				}
			} else if (tName.length <= 2) {
				classFilePath = mainfolder + "\\vertex.cloud\\" + tName[0].toString() + "\\" + tName[1].toString();
				final File folder = new File(classFilePath);
				search(".*\\.java", folder, result);
			} else {
				classFilePath = mainfolder + "\\vertex.cloud\\" + tName[0].toString() + "\\" + tName[1].toString();
				result.add(tName[2].toString());
			}

			String[] className = null;
			XmlSuite VcSuite = new XmlSuite();
			if (tName.length > 2) {
				VcSuite.setName(tName[0].toString() + "." + tName[1].toString() + " TestSuite");
			} else if (tName.length <= 2) {
				VcSuite.setName(args[0] + " TestSuite");
			} else {
				VcSuite.setName(args[0] + " TestSuite");
			}
			VcSuite.setParallel(XmlSuite.ParallelMode.NONE);
			VcSuite.setVerbose(2);
			VcSuite.setPreserveOrder(true);
			VcSuite.setThreadCount(3);
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("env", args[1]);
			VcSuite.setParameters(parameters);
			parameters.put("Browser", args[2]);
			VcSuite.setParameters(parameters);

			List<String> listener = new ArrayList<String>();
			listener.add("common.listener.Listener");
			VcSuite.setListeners(listener);

			List<XmlTest> VcTests = new ArrayList<XmlTest>();

			List<String> includeGroups = new ArrayList<String>();
			if(osci){
				includeGroups.add("osci");
			}

			for (String s : result) {
				XmlClass VcClasses = new XmlClass();
				XmlTest VcTest = new XmlTest(VcSuite);
				className = null;
				if(osci){
					VcTest.setIncludedGroups(includeGroups);
				}
				className = s.split(Pattern.quote("."));
				VcTest.setName(className[0].toString() + " Test");
				if (tName.length > 2) {
					VcClasses.setName(tName[0].toString() + "." + tName[1].toString() + "." + className[0].toString());
				} else if (tName.length <= 2) {
					VcClasses.setName(args[0] + "." + className[0].toString());
				} else {
					VcClasses.setName(tName[0].toString() + "." + tName[1].toString() + "." + className[0].toString());
				}
				VcTest.setXmlClasses(Arrays.asList(new XmlClass[] { (XmlClass) VcClasses }));
				VcTests.add(VcTest);
			}

			VcSuite.setTests(VcTests);

			List<XmlSuite> VcSuites = new ArrayList<XmlSuite>();
			VcSuites.add(VcSuite);
			FileWriter writer = null;
			if (tName.length > 2) {
				writer = new FileWriter(new File(
						mainfolder + "\\testNG_XML\\" + tName[0].toString() + "." + tName[1].toString() + ".xml"));
			} else if (tName.length <= 2) {
				writer = new FileWriter(new File(mainfolder + "\\testNG_XML\\" + args[0] + ".xml"));
			} else {
				writer = new FileWriter(new File(mainfolder + "\\testNG_XML\\" + tName[0].toString() + "."
						+ tName[1].toString() + "." + tName[2].toString() + ".xml"));
			}
			writer.write(VcSuite.toXml());
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}