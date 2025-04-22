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

        StringBuilder result= new StringBuilder();

        String num1 = new StringBuilder(Onum1).reverse().toString(); // Reverse the first number
        String num2 = new StringBuilder(Onum2).reverse().toString(); // Reverse the second number

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

        if (k==1){
             // To store the result of subtraction
            int borrow = 0; // Borrow value for subtraction
            int maxLength = num1.length(); // Max length of num1 (since num1 >= num2, no need to check length of num2)

        // Loop over the digits, performing subtraction with borrow
            for (int i = 0; i < maxLength; i++) {
                // Get the digits from both numbers, considering borrow
                int digit1 = num1.charAt(maxLength - 1 - i) - '0';
                int digit2 = i < num2.length() ? num2.charAt(num2.length() - 1 - i) - '0' : 0;

            // Perform subtraction, considering the borrow from the previous step
                int diff = digit1 - digit2 - borrow;
                if (diff < 0) {
                    diff += 10; // If the result is negative, adjust by adding 10
                    borrow = 1; // Set borrow to 1 for the next digit
                } else {
                    borrow = 0; // Reset borrow if subtraction was successful
                }

                result.append(diff); // Append the result of the subtraction
            }
            return new AInteger(result.reverse().toString());
        }

        if (k==-1){
            // To store the result of subtraction
           int borrow = 0; // Borrow value for subtraction
           int maxLength = num2.length(); // Max length of num1 (since num1 >= num2, no need to check length of num2)

       // Loop over the digits, performing subtraction with borrow
           for (int i = 0; i < maxLength; i++) {
               // Get the digits from both numbers, considering borrow
               int digit1 = num2.charAt(maxLength - 1 - i) - '0';
               int digit2 = i < num1.length() ? num1.charAt(num1.length() - 1 - i) - '0' : 0;

           // Perform subtraction, considering the borrow from the previous step
               int diff = digit1 - digit2 - borrow;
               if (diff < 0) {
                   diff += 10; // If the result is negative, adjust by adding 10
                   borrow = 1; // Set borrow to 1 for the next digit
               } else {
                   borrow = 0; // Reset borrow if subtraction was successful
               }

               result.append(diff); // Append the result of the subtraction
           }
           
           return new AInteger(result.reverse().toString());
       }

        return new AInteger("0");
 
    }

}
