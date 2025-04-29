package arbitaryarithmetic;

public class AFloat {
    
    private String value;

    // Default Constructor
    public AFloat() {
        this.value = "0.0";
    }

    // Constructor from String
    public AFloat(String s) {
        this.value=s;
    }

    // Copy Constructor
    public AFloat(AFloat other) {
        this.value = other.value;
    }

    // Static parse method
    public static AFloat parse(String s) {
        return new AFloat(s);
    }

    public String getvalue(){
        return this.value;
    }

}