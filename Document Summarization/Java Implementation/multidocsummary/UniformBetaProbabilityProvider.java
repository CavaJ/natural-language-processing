public class UniformBetaProbabilityProvider implements BetaProbabilityProvider {

        private IndexManager indexManager;
        
        public double getProbability(Sentence sentenceStart, String term) {
                int numberOfTerms = indexManager.getNumberOfTermOccurence(term);
                int termWeightStart = indexManager.getSentenceTermWeight(sentenceStart, term);
                return termWeightStart /(double)numberOfTerms;
        }

        public IndexManager getIndexManager() {
                return indexManager;
        }

        public void setIndexManager(IndexManager indexManager) {
                this.indexManager = indexManager;
        }

}

