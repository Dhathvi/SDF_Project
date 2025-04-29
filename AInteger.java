package arbitaryarithmetic;

public class AInteger {
    
    private String value;

    // Default Constructor
    public AInteger() {
        this.value = "0";
    }

    // Constructor from String
    public AInteger(String s) {
        this.value=s;
    }

    // Copy Constructor
    public AInteger(AInteger other) {
        this.value = other.value;
    }

    // Static parse method
    public static AInteger parse(String s) {
        return new AInteger(s);
    }

    public String getvalue(){
        return this.value;
    }

    public int compare(AInteger val){
        String num1 = this.value;
        String num2 = val.value;

        int k=0;
        if (num1.length() > num2.length()){
            k=1;
        }
        if(num1.length() < num2.length()){
            k=-1;
        }

        if (k==0){
            for (int i=0; i < num1.length();i++) {
                int digit1 = num1.charAt(i)-'0';
                int digit2 = num2.charAt(i)-'0';
                if (digit1>digit2){
                    k=1;
                    break;
                }
                if(digit2>digit1){
                    k=-1;
                    break;
                }
            }
       }
       return k;
    }

    private static StringBuilder removeleadingzeros(StringBuilder str){
        while (str.length() > 1 && str.charAt(0) == '0') {
            str.deleteCharAt(0);
        }
        return str;
    }

    public AInteger add(AInteger val){
        String num1 = this.value;
        String num2 = val.value;

        int carry=0;
        StringBuilder result= new StringBuilder();
        int maxlength=0;

        if (num1.length()>num2.length()){
            maxlength=num1.length();
        } else {
            maxlength=num2.length();
        }

        num1 = new StringBuilder(num1).reverse().toString(); // Reverse the first number
        num2 = new StringBuilder(num2).reverse().toString(); // Reverse the second number

        for(int i=0;i<maxlength;i++){
            int digit1 = i<num1.length() ? num1.charAt(i)-'0' : 0 ;
            int digit2 = i<num2.length() ? num2.charAt(i)-'0' : 0 ;

            int sum = digit1 + digit2 + carry;

            carry=sum/10;
            result.append(sum%10);
        }
        
        if (carry != 0) {
            result.append(carry); // If there's a remaining carry, append it
        }

        return new AInteger(result.reverse().toString());
    }

    public AInteger sub(AInteger val){
        String num1 = this.value;
        String num2 = val.value;

        if (num1.equals(num2)) {
            return new AInteger("0");
        }

        int k=compare(val);

       if (k==-1){
            String tmp = num1;
            num1 = num2;
            num2 = tmp;
       }

       StringBuilder result = new StringBuilder();
       int borrow = 0;
       int i = num1.length() - 1;
       int j = num2.length() - 1;

       while (i >= 0) {
            int digit1 = num1.charAt(i) - '0';
            int digit2 = (j >= 0 ? num2.charAt(j) - '0' : 0);
            int diff = digit1 - digit2 - borrow;
            if (diff < 0) {
                diff += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            result.append(diff);
            i--; j--;
        }

        result.reverse();

        removeleadingzeros(result);

        if (k==-1) {
            result.insert(0, '-');
        }
        
       return new AInteger(result.toString());
 
    }

    public AInteger mul(AInteger val) {
        String num1 = this.value;
        String num2 = val.value;

        if (num1.equals("0") || num2.equals("0")) {
            return new AInteger("0"); // If either number is 0, return 0
        }

        int[] result = new int[num1.length() + num2.length()];

        // Perform digit-by-digit multiplication
        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
                int product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0'); // Multiply the digits
                int sum = product + result[i + j + 1]; // Add the product to the corresponding position in the result array

                result[i + j + 1] = sum % 10; // Store the current digit (sum modulo 10)
                result[i + j] += sum / 10; // Handle the carry by adding it to the previous position
            }
        }

        // Convert the result array to a string
        StringBuilder resultStr = new StringBuilder();
        for (int num : result) {
            // Skip leading zeroes
            if (!(resultStr.length() == 0 && num == 0)) {
                resultStr.append(num); // Append each non-zero digit
            }
        }

        // Return the result as a new AInteger
        return new AInteger(resultStr.length() == 0 ? "0" : resultStr.toString());
    }

    public AInteger div(AInteger val){
        String Dividend = this.value;
        String Divisor = val.value;

        if (Divisor.equals("0")) {
            throw new ArithmeticException("Division by zero");
        }
    
        StringBuilder quotient = new StringBuilder();
        String current = "";

        for (int i = 0; i < Dividend.length(); i++) {
            current += Dividend.charAt(i);

            // Remove leading zeros
            current = current.replaceFirst("^0+", "");
            if (current.equals("")) current = "0";

            int x = 0;
            AInteger curInt = new AInteger(current);
            AInteger divInt = new AInteger(Divisor);
            
            while (curInt.compare(divInt) >= 0) {
                curInt = curInt.sub(divInt);
                x++;
            }

            quotient.append(x);
            current = curInt.getvalue();
        }

        String result = removeleadingzeros(quotient).toString();
        return new AInteger(result);

    }

}
