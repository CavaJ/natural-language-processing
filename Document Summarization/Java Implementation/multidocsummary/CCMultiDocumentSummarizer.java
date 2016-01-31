import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CCMultiDocumentSummarizer extends BaseSummarizer {

        private String corpusDirName;
        private String outputFileName;
        private Stemmer stemmer;
		private static int sentenceCount = 0;
        
        public static void main(String[] args) {
                if (args.length < 3 || args.length > 4) {
                        System.err.println("Error: Invalid usage.");
                        System.exit(1);
                }
                
                CCMultiDocumentSummarizer documentSummarizer;
                
                // "corpus\DUC_2002\docs\d062j" "corpus\DUC_2002\summaries\d062ja\ccs.txt" -es
                // args[0] : The path of the directory contains documents
                // args[1] : The path + name of the summary file (It is created)
                // args[2] : -es means use English stemmer and -ts means use Turkish stemmer
                //           If not speccified, no stemmer is used. 
                
                if (args.length == 4)
                {
                        documentSummarizer = new CCMultiDocumentSummarizer(args[0], args[1], args[2]);
						sentenceCount = Integer.parseInt(args[3]);
                }
                else
                {
                        documentSummarizer = new CCMultiDocumentSummarizer(args[0], args[1]);
						sentenceCount = Integer.parseInt(args[2]);
                }
                
                documentSummarizer.execute();
        }
        
        private CCMultiDocumentSummarizer(String corpusDirName, String outputFileName) {
                this.corpusDirName = corpusDirName;
                this.outputFileName = outputFileName;
        }

        public CCMultiDocumentSummarizer(String corpusDirName, String outputFileName, String stemmerId) {
                // TODO Auto-generated constructor stub
                this(corpusDirName, outputFileName);
                stemmer = StemmerFactory.getStemmer(stemmerId);
        }

        @Override
        protected void executeInternal() {
                File corpusDir = new File(corpusDirName);
                Application application = Application.getInstance();
                DocumentFactory factory = application.getDocumentFactory();
                factory.setStemmer(stemmer);
                
                List<Sentence> sentenceList = new ArrayList<Sentence>();
                File[] corpusFiles = corpusDir.listFiles();
                for (File file : corpusFiles) {
                        Document doc = factory.createDocumentFromTextFile(file);
                        // Add sentences from the current document to the global sentence list
                        sentenceList.addAll(doc.getSentences());
                }
                // Get a summary of five sentences from all the documents
				
				List<SentenceNode> summarySentences;
				
				if (sentenceCount <= 0)
				{
					summarySentences = selectSentences(sentenceList, 5);
				} // if
				else
				{
					summarySentences = selectSentences(sentenceList, sentenceCount);
				} // else
                
                try {
                        PrintWriter writer = new PrintWriter(outputFileName);
                        for (SentenceNode node : summarySentences) {
                                writer.println(node.getSentence().getRawText());
                        }
                        writer.close();
                } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
        }
}