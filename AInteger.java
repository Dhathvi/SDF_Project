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
}
