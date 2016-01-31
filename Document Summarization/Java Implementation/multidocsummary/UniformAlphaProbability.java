public class UniformAlphaProbability implements AlphaProbability{
        
        private IndexManager indexManager;

        public IndexManager getIndexManager() {
                return indexManager;
        }

        public void setIndexManager(IndexManager indexManager) {
                this.indexManager = indexManager;
        }

        public double getAlphaProbability(String term, Sentence sentenceStart) {
                int termWeightStart = indexManager.getSentenceTermWeight(sentenceStart, term);
                int numberOfTermsInSentence = sentenceStart.getWords().size();
                return termWeightStart/(double)numberOfTermsInSentence;
        }

        

}