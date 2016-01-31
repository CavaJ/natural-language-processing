import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * @author gonenc
 * 
 * Tokenize a String to words. Word boundaries are determined by escape characters like white 
 * spaces and punctuations.
 *
 */

public class WordTokenizer extends RegExpTokenizer {

        public WordTokenizer()
        {
                _regExp = "([a-zA-Z0-9'üðiþçöýÜÐÞÇÖÝ])+"; 
        }
        
        public void tokenize() {
                
                Pattern pattern = Pattern.compile(this._regExp);
                Matcher matcher = pattern.matcher(this._originalText);
                
                while (matcher.find())
                {
                        tokens.add(matcher.group());
                }
        }

        public Collection getAllTokens() {
                return tokens;
        }

        

        
}