package arbitaryarithmetic;

public class AInteger {
    
    private long value;

    // 1.Default constructor
    public AInteger() {
        this.value = 0;
    }

    // 2. Constructor that takes a String
    public AInteger(String s) {
        this.value = Long.parseLong(s);
    }

    // 3. Copy constructor
    public AInteger(AInteger other) {
        this.value = other.value;
    }
}
