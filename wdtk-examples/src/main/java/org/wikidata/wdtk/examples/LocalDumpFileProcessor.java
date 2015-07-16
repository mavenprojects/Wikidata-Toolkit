package org.wikidata.wdtk.examples;

import java.io.IOException;

import org.wikidata.wdtk.datamodel.interfaces.EntityDocumentProcessor;
import org.wikidata.wdtk.dumpfiles.DumpContentType;
import org.wikidata.wdtk.dumpfiles.DumpProcessingController;
import org.wikidata.wdtk.dumpfiles.MwDumpFile;
import org.wikidata.wdtk.dumpfiles.MwLocalDumpFile;

/**
 * This class illustrates how to process local dumpfiles
 * 
 * @author Markus Damm
 *
 */

public class LocalDumpFileProcessor {

	final static String COMPRESS_BZ2 = ".bz2";
	final static String COMPRESS_GZIP = ".gz";
	final static String COMPRESS_NONE = "";

	private static MwDumpFile mwDumpFile;

	/**
	 * Name string of the dump file that is locally saved and should be
	 * processed
	 */
	private final static String FILE_NAME = "20150713.json.gz";

	/**
	 * Directory of the dump that should be processed
	 */
	private final static String DUMP_DIRECTORY = System
			.getProperty("user.dir");

	private final static DumpContentType DUMP_CONTENT_TYPE = DumpContentType.JSON;

	private static EntityDocumentProcessor entityDocumentProcessor = new GenderRatioProcessor();

	public static void main(String[] args) {
		ExampleHelpers.configureLogging();
		LocalDumpFileProcessor.printDocumentation();
		try {
			mwDumpFile = new MwLocalDumpFile(DUMP_DIRECTORY,
					DUMP_CONTENT_TYPE, FILE_NAME);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DumpProcessingController dumpProcessingController = new DumpProcessingController(
				"wikidata_wiki");
		dumpProcessingController.setOfflineMode(true);
		dumpProcessingController.registerEntityDocumentProcessor(
				entityDocumentProcessor, null, false);
		dumpProcessingController.processDump(mwDumpFile);
	}

	/**
	 * Prints some basic documentation about this program.
	 */
	public static void printDocumentation() {
		System.out.println("********************************************************************");
		System.out.println("*** Wikidata Toolkit: ProcessLocalDumpFile");
		System.out.println("*** ");
		System.out.println("*** Description.");
		System.out.println("********************************************************************");
	}
}