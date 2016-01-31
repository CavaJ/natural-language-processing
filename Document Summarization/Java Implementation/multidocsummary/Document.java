import java.util.ArrayList;
import java.util.List;

public class Document {
        
        private List<Sentence> sentences;
        private String documentName;
        
        
        public Document(String docName) {
                this.documentName = docName;
                sentences = new ArrayList<Sentence>();
        }
        
        public void addSentence(Sentence sentence) {
                sentences.add(sentence);
        }


        public String getDocumentName() {
                return documentName;
        }


        public void setDocumentName(String documentName) {
                this.documentName = documentName;
        }


        public List<Sentence> getSentences() {
                return sentences;
        }

        

}