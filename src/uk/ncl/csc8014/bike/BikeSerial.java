package uk.ncl.csc8014.bike;

class BikeSerial {
    private final String uniqueNumber;
    private final String serialNumberTwoLetters;
    private final String serialNumberDigits;

    BikeSerial(String twoLetters, int threeDigitNumber){
        if (!String.valueOf(twoLetters).matches("[a-zA-Z][a-zA-Z]")) {
            throw new IllegalArgumentException("That is not two letters long");
        }
        twoLetters.toLowerCase();
        if (!String.valueOf(threeDigitNumber).matches("\\d\\d\\d")) {
            throw new IllegalArgumentException("That is not a three digit number");
        }
        String s = twoLetters + threeDigitNumber;
        serialNumberTwoLetters = twoLetters;
        serialNumberDigits = String.valueOf(threeDigitNumber);
        uniqueNumber = s;
    }


    /**
     * Method to return the first two letters of the serial number.
     * @return first two letters of the serial number.
     */
    public String getSerialNumberTwoLetters() {
        return serialNumberTwoLetters;
    }

    /**
     * Method to return the 3 digits of the serial number.
     * @return 3 digits of the serial number.
     */
    public String getSerialNumberDigits() {
        return serialNumberDigits;
    }

}
