package com.devsancabo.www;

/**
 * Pseudo Random Number Generator. Implements a Lineal Congruential Generator
 */
class PRNG {
    private static final long MODULUS =  Long.parseUnsignedLong("100000000000000000000000000000000000000000000000",2);;
    private static final long MULTIPLIER =  25214903917L;
    private static final long INCREMENT =  11L;
    private static final long BIT_MASK = Long.parseUnsignedLong("11111111111111111111111111111110000000000000000",2);
    private static final long MAX_VALUE = Integer.parseUnsignedInt("1111111111111111111111111111111",2);
    private long previousValue;

    protected PRNG(){
        this.previousValue = System.currentTimeMillis();
    }

    /**
     * Gets a random positive integer between 0 and a max value.
     * @param max maximum possible value of the number generated.
     * @return the generated number.
     */
    protected int getValue(int max) {
        if(max < 0) throw new IllegalArgumentException("Illegal value of: " + max + ". Max value cannot be negative");
        return new Double(getValue() * max).intValue();
    }

    //get random between 0 and 1
    private double getValue() {
        long newValue = ((MULTIPLIER * previousValue) + INCREMENT) % MODULUS;
        this.previousValue = newValue;
        int maskedNewValue = (int)((newValue & BIT_MASK) >> 16); // bits 48 to 16. 32 in total
        return (double) maskedNewValue / MAX_VALUE;
    }
}
