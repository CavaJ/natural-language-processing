import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class IndexManager implements 
        WordOccurenceListener, 
        SentenceOccurenceListener {
        
        private DocumentLevelIndexer documentLevelIndexer;
        private SentenceLevelIndexer sentenceLevelIndexer;
        
        private Map<String, Integer> termFrequencies = 
                new HashMap<String, Integer>();
        

        /**
         * Because of these two variables this class is not threadSafe
         * Multiple threads will mess this up. Consider using ThreadLocal
         */
        private Document workingDocument;
        private Sentence workingSentence;

        public void wordOccured(String word) {
                documentLevelIndexer.addToIndex(workingDocument, word);
                sentenceLevelIndexer.addToIndex(workingSentence, word);
                incrementTermCount(word);
        }

        private void incrementTermCount(String word) {
                int count = 0;
                Integer c;
                if((c=termFrequencies.get(word))!=null) {
                        count = c.intValue();
                }
                count++;
                termFrequencies.put(word, count);               
        }

        public void sentenceOccured(Sentence sentence) {
                this.workingSentence = sentence;
        }

        public Document getWorkingDocument() {
                return workingDocument;
        }

        public void setWorkingDocument(Document workingDocument) {
                this.workingDocument = workingDocument;
        }

        
        public void setDocumentLevelIndexer(DocumentLevelIndexer documentLevelIndexer) {
                this.documentLevelIndexer = documentLevelIndexer;
        }

        public void setSentenceLevelIndexer(SentenceLevelIndexer sentenceLevelIndexer) {
                this.sentenceLevelIndexer = sentenceLevelIndexer;
        }

        public Collection<String> getSentenceTerms(Sentence sentenceStart) {
                return sentenceLevelIndexer.getTermsInKey(sentenceStart);
        }

        public int getSentenceTermWeight(Sentence sentenceStart, String term) {
                return sentenceLevelIndexer.getCount(term, sentenceStart);
        }

        public int getNumberOfTermOccurence(String term) {
                return termFrequencies.get(term);
        }

}