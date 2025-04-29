package arbitaryarithmetic;

public class AInteger {
    
    private String value;
    private Boolean isNegative;

    // Default Constructor
    public AInteger() {
        this.value = "0";
        this.isNegative = false;
    }

    // Constructor from String
    public AInteger(String s) {
        if (s == null || s.isEmpty()) {
            value = "0";
            isNegative = false;
            return;
        }
    
        if (s.charAt(0) == '-') {
            isNegative = true;
            value = s.substring(1);
        } else if (s.charAt(0) == '+') {
            isNegative = false;
            value = s.substring(1);
        } else {
            isNegative = false;
            value = s;
        }
    
        value = removeLeadingZeros(new StringBuilder(value)).toString();
    
        if (value.equals("0")) {
            isNegative = false; // normalize zero to be always non-negative
        }
    }
    

    // Copy Constructor
    public AInteger(AInteger other) {
        this.value = other.value;
        this.isNegative = other.isNegative;
    }

    // Static parse method
    public static AInteger parse(String s) {
        return new AInteger(s);
    }

    public String getValue(){
        return (isNegative ? "-" : "") + value;
    }

    private static int compareUnsigned(AInteger a, AInteger b){
        String num1 = a.value;
        String num2 = b.value;

        if (num1.length() > num2.length()){
            return 1;
        }
        if(num1.length() < num2.length()){
            return -1;
        }

        for (int i=0; i < num1.length();i++) {
            int digit1 = num1.charAt(i)-'0';
            int digit2 = num2.charAt(i)-'0';
            if (digit1>digit2){
                return 1;
            }
            if(digit2>digit1){
                return -1;
            }
        }
       return 0;
    }

    public int compare(AInteger val) {
        if (this.isNegative && !val.isNegative) return -1;
        if (!this.isNegative && val.isNegative) return 1;
    
        int cmp = compareUnsigned(this, val);
        return this.isNegative ? -cmp : cmp;
    }

    private static StringBuilder removeLeadingZeros(StringBuilder str){
        while (str.length() > 1 && str.charAt(0) == '0') {
            str.deleteCharAt(0);
        }
        return str;
    }

    private static AInteger addUnsigned(AInteger a, AInteger b){
        String num1 = a.value;
        String num2 = b.value;

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

    private static AInteger subUnsigned(AInteger a, AInteger b) {
        String num1 = a.value;
        String num2 = b.value;
    
        StringBuilder result = new StringBuilder();
        int borrow = 0;
    
        int i = num1.length() - 1;
        int j = num2.length() - 1;
    
        while (i >= 0) {
            int digit1 = num1.charAt(i) - '0';
            int digit2 = (j >= 0) ? num2.charAt(j) - '0' : 0;
    
            int diff = digit1 - digit2 - borrow;
            if (diff < 0) {
                diff += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
    
            result.append(diff);
            i--;
            j--;
        }
    
        result.reverse();
        removeLeadingZeros(result);
    
        return new AInteger(result.toString());
    }

    private static AInteger mulUnsigned(AInteger a, AInteger b) {
        String num1 = a.value;
        String num2 = b.value;

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

    private static AInteger divUnsigned(AInteger a, AInteger b){
        String dividend = a.value;
        String divisor = b.value;

        if (divisor.equals("0")) {
            throw new ArithmeticException("Division by zero");
        }
    
        StringBuilder quotient = new StringBuilder();
        String current = "";

        for (int i = 0; i < dividend.length(); i++) {
            current += dividend.charAt(i);

            // Remove leading zeros
            current = current.replaceFirst("^0+", "");
            if (current.equals("")) current = "0";

            int x = 0;
            AInteger curInt = new AInteger(current);
            AInteger divInt = new AInteger(divisor);
            
            while (compareUnsigned(curInt, divInt) >= 0) {
                curInt = subUnsigned(curInt, divInt);
                x++;
            }

            quotient.append(x);
            current = curInt.getValue();
        }

        String result = removeLeadingZeros(quotient).toString();
        return new AInteger(result);

    }

    public AInteger add(AInteger val) {
        if (this.isNegative == val.isNegative) {
            // Same signs: perform addition and preserve the sign
            AInteger result = addUnsigned(this, val);
            result.isNegative = this.isNegative;
            return result;
        } else {
            // Opposite signs: perform subtraction
            if (compareUnsigned(this, val) >= 0) {
                AInteger result = subUnsigned(this, val);
                result.isNegative = this.isNegative;
                return result;
            } else {
                AInteger result = subUnsigned(val, this);
                result.isNegative = val.isNegative;
                return result;
            }
        }
    }

    public AInteger sub(AInteger val) {
        AInteger negVal = new AInteger(val);
        negVal.isNegative = !val.isNegative;
        return this.add(negVal);
    }

    public AInteger mul(AInteger val) {
        AInteger result = mulUnsigned(this, val);
        result.isNegative = this.isNegative != val.isNegative;
        return result;
    }

    public AInteger div(AInteger val) {
        if (val.value.equals("0")) {
            throw new ArithmeticException("Division by zero");
        }
    
        AInteger result = divUnsigned(this, val);
        result.isNegative = this.isNegative != val.isNegative;
        return result;
    }    

}
