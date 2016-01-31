import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SentenceTokenizer extends RegExpTokenizer {

        public SentenceTokenizer()
        {
//              _regExp = "([\"A-Za-z0-9 ,\\\\(\\)\\n,;:-_%#']+)([\\.?!])";
                _regExp = "([^\\n]+)([\\n])";
        }
        
        public void tokenize() {

                Pattern pattern = Pattern.compile(this._regExp, Pattern.DOTALL);
                Matcher matcher = pattern.matcher(this._originalText);
                
                while (matcher.find())
                {
                        if(matcher.group(0).length() > 5)
                        {
                                tokens.add(matcher.group(0).replace("\r\n", " "));
                        }
                }
                
        }

        
        
        

        
        
}