package algorithm.luhn;

/**
 * Luhn算法
 */
public class LuhnAlgorithm {

    /**
     * Validates a number string using the Luhn formula.
     */
    public static boolean validate(String cardNumber) {
        int sum = 0;
        boolean alternate = false;

        // Iterate from right to left
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));

            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n -= 9;
                }
            }

            sum += n;
            alternate = !alternate;
        }

        return (sum % 10 == 0);
    }

    /**
     * Generates the check digit (the 16th digit for a 15-digit card number).
     * Formula: (Sum * 9) % 10
     */
    public static int generateCheckDigit(String numberWithoutCheckDigit) {
        int sum = 0;
        boolean alternate = true; // Start true because we are effectively at index length-1

        for (int i = numberWithoutCheckDigit.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(numberWithoutCheckDigit.substring(i, i + 1));

            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n -= 9;
                }
            }

            sum += n;
            alternate = !alternate;
        }

        System.out.println("total:" + sum);
        //(52 * 9) % 10
        //(10 - (52 % 10)) % 10
        return (10 - sum % 10) % 10;//这种方式 more “readable”
//        return (sum * 9) % 10;//这种方式 high performance
    }

    public static void main(String[] args) {
        String testCard = "890746352"; // Example number

        // 1. Generate Check Digit
        int checkDigit = generateCheckDigit(testCard);
        String fullNumber = testCard + checkDigit;
        System.out.println("Payload: " + testCard);
        System.out.println("Generated Check Digit: " + checkDigit);
        System.out.println("Full Number: " + fullNumber);

        // 2. Validate
        boolean isValid = validate(fullNumber);
        System.out.println("Is the full number valid? " + isValid);
    }
}
