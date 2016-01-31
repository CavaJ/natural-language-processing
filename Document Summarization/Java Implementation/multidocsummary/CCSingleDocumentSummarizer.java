import java.io.File;
import java.util.List;

public class CCSingleDocumentSummarizer extends BaseSummarizer{

		public static String path = null; 
		public static int sentenceCount = 0;
		
        public static void main(String[] args) {
                CCSingleDocumentSummarizer documentSummarizer = 
                        new CCSingleDocumentSummarizer();
							
				path = args[0];
				sentenceCount = Integer.parseInt(args[1]);
                documentSummarizer.execute();
        }

        @Override
        protected void executeInternal() {

                File file = new File(path);

                Application application = Application.getInstance();
                Document document = application.getDocumentFactory().
                        createDocumentFromTextFile(file);

                List<SentenceNode> list = selectSentences(document.getSentences(), sentenceCount);
				
				System.out.println("-----------------------------------------------------------");
				
                for (SentenceNode node : list) {
                        System.out.println(node.getSentence().getRawText());
                }
				
				System.out.println("-----------------------------------------------------------");
        }

}

