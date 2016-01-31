import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DocumentFactory {
        
        private SentenceTokenizer sentenceTokenizer;
        private WordTokenizer wordTokenizer;
        
        private List<SentenceOccurenceListener> sentenceListeners;
        private List<WordOccurenceListener> wordListeners;
        
        private Stemmer stemmer;
        
        {
                sentenceListeners = new ArrayList<SentenceOccurenceListener>();
                wordListeners = new ArrayList<WordOccurenceListener>();
                sentenceTokenizer = new SentenceTokenizer();
                wordTokenizer = new WordTokenizer();
        }
        
        public Document createDocumentFromTextFile(File textFile) {
                String text =readFile(textFile);
                return createDocument(text, textFile.getAbsolutePath());
        }
        
        public void registerSentenceListener(SentenceOccurenceListener listener) {
                this.sentenceListeners.add(listener);
        }
        
        public void registerWordListener(WordOccurenceListener listener) {
                this.wordListeners.add(listener);
        }

        /**
         * TODO: long term improvement, to be able to process
         * long documents (too large for memory) modify this method and the 
         * <code>SentenceTokenizer</code> to work with inputStream. 
         * 
         * @param text
         */
        private Document createDocument(String text, String name) {
                Document createdDocument;
                createdDocument = new Document(name);
                sentenceTokenizer.setText(text);
                sentenceTokenizer.tokenize();
                
                int sentenceIndex=1;
                while(sentenceTokenizer.hasNext()) {
                        String sentenceText = sentenceTokenizer.getNextToken();
                        Sentence sentence=new Sentence(sentenceText);
                        sentence.setPositionIndex(sentenceIndex);
                        sentence.setDocument(createdDocument);
                        sentenceIndex++;
                        
                        callSentenceListeners(sentence);
                        
                        wordTokenizer.setText(sentenceText);
                        wordTokenizer.tokenize();
                        List<String> wordsInSentence = new ArrayList<String>();
                        
                        while (wordTokenizer.hasNext()) {
                                String word = wordTokenizer.
                                        getNextToken();

                                // Lower case
                                word = word.toLowerCase();
                                if (stemmer != null) {
                                        word = stemmer.stem(word);
                                }
                                        
                                wordsInSentence.add(word);
                                callWordListeners(word);
                        }
                        sentence.setWords(wordsInSentence);
                        createdDocument.addSentence(sentence);
                }
                return createdDocument;
        }
        
        private void callSentenceListeners(Sentence sentence) {
                for (SentenceOccurenceListener listener : this.sentenceListeners) {
                        listener.sentenceOccured(sentence);
                }
        }

        private void callWordListeners(String word) {
                for (WordOccurenceListener listener : this.wordListeners) {
                        listener.wordOccured(word);
                }
        }

        public Document createDocumentFromTextFile(String textFile) {
                File file = new File(textFile);
                return createDocumentFromTextFile(file);
        }


        private String readFile(File textFile) {
                StringBuffer stringBuffer = new StringBuffer();
                
                try {
                        FileReader fileReader = new FileReader(textFile);
                        char[] buffer = new char[1024];
                        int read=0;
                        
                        
                        while((read = fileReader.read(buffer)) > 0) {
                                stringBuffer.append(buffer, 0, read);
                        }
                } catch (IOException e) {
                        //e.printStackTrace();
                        //throw new RuntimeException("Error While Reading the file:" 
                        //              +textFile.getAbsolutePath());
                        System.out.println("Error While Reading the file: " + textFile.getAbsolutePath());
                }
                
                return stringBuffer.toString();
        }

        public Stemmer getStemmer() {
                return stemmer;
        }

        public void setStemmer(Stemmer stemmer) {
                this.stemmer = stemmer;
        }

}