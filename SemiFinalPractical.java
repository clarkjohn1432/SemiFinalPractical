import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class SemiFinalPractical {

   public static void main(String[] args) {
        checkExpressions("expressions.txt");
    }
    
    public static boolean isWellDefined(String expression) {
        Stack<Character> stack = new Stack<>();
        
        for (char ch : expression.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            }
            else if (ch == ')' || ch == '}' || ch == ']') {
                if (stack.isEmpty()) {
                    return false;  
                }             
                char top = stack.pop();                
                if ((ch == ')' && top != '(') || 
                    (ch == '}' && top != '{') || 
                    (ch == ']' && top != '[')) {
                    return false;                }
            }
        }
        
        return stack.isEmpty();
    }

    public static void checkExpressions(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int expressionCount = 1;

            while ((line = br.readLine()) != null) {
                line = line.trim(); 
                boolean isWellDefined = isWellDefined(line);
                
                if (isWellDefined) {
                    System.out.println("Expression " + expressionCount + ": Well Defined");
                } else {
                    System.out.println("Expression " + expressionCount + ": Not Well Defined");
                }
                
                expressionCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
