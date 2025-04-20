package arbitaryarithmetic;

import java.math.BigInteger;

public class AInteger {
    
    private BigInteger value;

    // Default Constructor
    public AInteger() {
        this.value = BigInteger.ZERO;
    }

    // Constructor from String
    public AInteger(String s) {
        try {
            this.value = new BigInteger(s.trim());
        } catch (NumberFormatException e) {
            System.err.println("Invalid BigInteger format: " + s);
            this.value = BigInteger.ZERO;
        }
    }

    // Copy Constructor
    public AInteger(AInteger other) {
        this.value = other.value;
    }

    // Static parse method
    public static AInteger parse(String s) {
        return new AInteger(s);
    }

    public AInteger add (AInteger val) {
        return new AInteger(Integer.toString(this.value + val.value));
    }

}
