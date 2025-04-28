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
        String Onum1 = this.value;
        String Onum2 = val.value;

        if (Onum1.equals(Onum2)) {
            return new AInteger("0");
        }


        String num1 = Onum1;
        String num2 = Onum2;

        int k=0;
        if (num1.length() > num2.length()){
            k=1;
        }
        if(num1.length() < num2.length()){
            k=-1;
        }

        if (k==0){
            for (int i=0; i < num1.length();i++) {
                int digit1 = num1.charAt(i);
                int digit2 = num2.charAt(i);
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

        // Remove leading zeros in the reversed result
        while (result.length() > 1 && result.charAt(result.length() - 1) == '0') {
            result.setLength(result.length() - 1);
        }

        if (k==-1) {
            result.append('-');
        }
        
       return new AInteger(result.reverse().toString());
 
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

        
    }

}
