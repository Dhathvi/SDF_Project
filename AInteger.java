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
        
    }
}
