import java.util.List;

public class Sentence {
        
        private String rawText;
        
        private int positionIndex;
        
        /**
         * Do we really need this reference???
         */
        private Document document;
        
        private List<String> words;
        
        public Sentence(String sentenceText) {
                this.rawText = sentenceText;
        }

        public List<String> getWords() {
                return words;
        }

        public void setWords(List<String> words) {
                this.words = words;
        }

        public int getPositionIndex() {
                return positionIndex;
        }

        public void setPositionIndex(int positionIndex) {
                this.positionIndex = positionIndex;
        }

        public String getRawText() {
                return rawText;
        }

        public void setRawText(String rawText) {
                this.rawText = rawText;
        }

        public Document getDocument() {
                return document;
        }

        public void setDocument(Document document) {
                this.document = document;
        }
        
        
        

}