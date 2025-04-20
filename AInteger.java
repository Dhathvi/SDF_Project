package arbitaryarithmetic;

public class AInteger {
    
    private long value;

    // 1.Default constructor
    public AInteger() {
        this.value = 0;
    }

    // 2. Constructor that takes a String
    public AInteger(String s) {
        try {
            this.value = Long.parseLong(s);
        } catch (NumberFormatException e) {
                System.out.println("Invalid input: '" + s + "' is not a valid long. Setting value to 0.");
            this.value = 0; // default fallback
        }
    }

    // 3. Copy constructor
    public AInteger(AInteger val) {
        this.value = val.value;
    }

    // static function that returns an instance of AInteger class.
    public static AInteger parse(String s) {
        return new AInteger(s);
    }

    public AInteger add (AInteger val) {
        re
    }

}
