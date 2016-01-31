import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseSummarizer {
        
        protected CoverCoeficientCalculator coeficientCalculator;
        
        
        public void execute() {
                initializeApplication();
                executeInternal();
                
        }
        
        private void initializeApplication() {
                Application application = Application.getInstance();
                this.coeficientCalculator = application.
                        getCoeficientCalculator();
        }

        protected abstract void executeInternal();
        
        public List<SentenceNode> selectSentencesColumwise(List<Sentence> allSentences,int summaryLength) {
                List<SentenceNode> sortedCoefficients = findDiagonalCoeficients(allSentences);
                
                Collections.sort(sortedCoefficients);
                
                List<SentenceNode> selectedSentences = new ArrayList<SentenceNode>();
                        
                double ccMatrix[][]=computeAllCoefficients(allSentences);
                double ccColumnAvg[]=computeAllCoefficientColumnAverages(ccMatrix);
                int i=0;
                // TODO: Stop criteria
                while(selectedSentences.size()<summaryLength && i<sortedCoefficients.size()){
                        SentenceNode candidateSentenceNode = sortedCoefficients.get(i);
                        if(!isAlreadyCoveredColumwise(candidateSentenceNode, selectedSentences,ccColumnAvg,ccMatrix)){
                                selectedSentences.add(candidateSentenceNode);
                        }
                        i++;
                }
                
                return selectedSentences;
        }
        
        public List<SentenceNode> selectSentences(List<Sentence> allSentences,int summaryLength) {
                List<SentenceNode> sortedCoefficients = findDiagonalCoeficients(allSentences);

                Collections.sort(sortedCoefficients);
                
                List<SentenceNode> selectedSentences = new ArrayList<SentenceNode>();
                        
                int i=0;
                // TODO: Stop criteria

                while(selectedSentences.size()<summaryLength && i<sortedCoefficients.size()){
                        SentenceNode candidateSentenceNode = sortedCoefficients.get(i);
                        if(!isAlreadyCovered(candidateSentenceNode, selectedSentences,sortedCoefficients.size())){
                                selectedSentences.add(candidateSentenceNode);
                        }
                        i++;
                }
                
                return selectedSentences;
        }
        private double [] computeAllCoefficientColumnAverages(double ccMatrix[][]) {
                double ccArray[];
                int arraySize=0;                
                arraySize = ccMatrix.length;
                ccArray = new double[arraySize];
                for (int i=0;i<arraySize;i++){
                        ccArray[i]=0;
                        for (int j=0;j<arraySize;j++){          
                                if (i!=j)
                                        ccArray[i]=ccArray[i]+ccMatrix[j][i];                           
                        }
                        ccArray[i]=ccArray[i]/(arraySize-1);
                        System.out.println("Averages:  "+i+" -->"+ccArray[i]+" ");
                }
                return ccArray;
        }
        private double [][] computeAllCoefficients(List<Sentence> allSentences) {
                double ccMatrix[][];
                int docSize=0;          
                docSize = allSentences.size();
                ccMatrix = new double[docSize][docSize];
                for (int i=0;i<docSize;i++)
                        for (int j=0;j<docSize;j++){                    
                                ccMatrix[i][j]=this.coeficientCalculator.computeCoeficient(allSentences.get(i), allSentences.get(j));
                                System.out.println("ccArray[i][j] "+i+" "+j+"-->"+ccMatrix[i][j]);
                }
                return ccMatrix;
        }

        private boolean isAlreadyCovered(SentenceNode candidateSentenceNode,
                        List<SentenceNode> selectedSentences, int numberOfSentences) {
                for (SentenceNode selectedSentence : selectedSentences) {
                        double averageCoverage = (1-selectedSentence.
                                        getCoeficient()) / (numberOfSentences-1);
                        double coverage = coeficientCalculator.computeCoeficient(candidateSentenceNode.getSentence(), selectedSentence.getSentence());
                                
                        if(coverage > averageCoverage) return true;
                }
                
                return false;
        }
        private boolean isAlreadyCoveredColumwise(SentenceNode candidateSentenceNode,List<SentenceNode> selectedSentences,double ccAvgArray[],double ccMatrix[][]) {
                int a=0,b=0;
                for(int i=0;i<selectedSentences.size();i++){
                        SentenceNode selectedSentence=selectedSentences.get(i);
                        a=selectedSentence.getSentence().getPositionIndex();
                        b=candidateSentenceNode.getSentence().getPositionIndex();
                        double averageCoverage = ccAvgArray[a];
                        double coverage = ccMatrix[a][b];
                                
                        if(coverage > averageCoverage) return true;
                }
                
                return false;
        }
        private List<SentenceNode> findDiagonalCoeficients(List<Sentence> allSentences) {
                List<SentenceNode> sortedCoeficients = new ArrayList<SentenceNode>(); 
                for (Sentence sentence : allSentences) {
                        double coeficient = coeficientCalculator.
                                computeCoeficient(sentence, sentence);
                        sortedCoeficients.add(new SentenceNode(sentence, coeficient));
                }
                return sortedCoeficients;
        }
        
        public void saveForRougeEvaluation() {
                
        }
        
        

}