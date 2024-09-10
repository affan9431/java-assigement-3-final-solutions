/***
 * Question: String Manipulation.
 * Owner Name: Affan Sayeed
 * Date: 10-9-2024
 */

import java.math.BigInteger;
import java.util.Scanner;

class StringManipulate {
    // Swap elements at index i and j in the array
    public void swap(char[] array, int start, int end) {
        char temporary = array[start];
        array[start] = array[end];
        array[end] = temporary;
    }

    // Generate all permutations of the given character array
    public void generateCombinations(char[] inputChar, int start, int length) {
        printCombination(inputChar, start);
        for (int i = start; i < length; i++) {
            swap(inputChar, i, start);
            generateCombinations(inputChar, start + 1, length);
            swap(inputChar, i, start);
        }
    }

    // Print the current permutation up to the specified end index
    public void printCombination(char[] chars, int end) {
        for (int i = 0; i < end; i++) {
            System.out.print(chars[i]);
        }
        System.out.println();
    }

    // Function to calculate the sum of digits
    public BigInteger sumOfDigits(BigInteger number) {
        BigInteger sum = BigInteger.ZERO;
        while (number.compareTo(BigInteger.ZERO) > 0) {
            sum = sum.add(number.mod(BigInteger.TEN));
            number = number.divide(BigInteger.TEN);
        }
        return sum;
    }

    // Function to reduce a number to a single digit and print intermediate results
    public void reduceToSingleDigit(BigInteger number) {
        System.out.println(Constants.INITIAL_NUMBER + number);
        while (number.compareTo(BigInteger.TEN) >= 0) {
            number = sumOfDigits(number);
            System.out.println(Constants.INTERMEDIATE_RESULT + number);
        }

        System.out.println(Constants.SINGLE_DIGIT_NUMBER + number);
    }

    // Function to read input and process the number
    public void processNumber() {
        Scanner input = new Scanner(System.in);
        System.out.println(Constants.ENTER_POSITIVE_NUMBER);
        if (!input.hasNextInt()) {
            System.out.println(Constants.VALID_POSITIVE_INTEGER);
        }
        else {
            String inputString = input.nextLine();
            int stringToNumber = Integer.parseInt(inputString);
            if (stringToNumber < 0) {
                System.out.println(Constants.NEGATIVE_NUMBER_CHECK);
            } else {
                try {
                    BigInteger numberInput = new BigInteger(inputString);

                    // Check if the input is a positive number
                    if (numberInput.compareTo(BigInteger.ZERO) <= 0) {
                        System.out.println(Constants.ENTER_POSITIVE_NUMBER);
                    } else {
                        // Reduce the number to a single digit and print intermediate results
                        reduceToSingleDigit(numberInput);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(Constants.INVALID_INPUT_CHECK);
                }
            }
        }
    }

    public  void findConsecutiveSums(int number) {
        boolean hasConsecutiveSum = false;

        for (int start = 1; start <= number / 2; start++) {
            int sum = 0;
            StringBuilder combination = new StringBuilder();
            for (int i = start; i <= number; i++) {
                sum += i;
                combination.append(i).append(" + ");
                if (sum == number) {
                    combination.delete(combination.length() - 3, combination.length());
                    System.out.println(combination.toString());
                    hasConsecutiveSum = true;
                    break;
                } else if (sum > number) {
                    break;
                }
            }
        }

        if (!hasConsecutiveSum) {
            System.out.println(number + Constants.CONSECUTIVE_WARN);
        }
    }

    // Function to encrypt a string using Caesar Cipher with a pattern
    public String encryptString(String input, int[] pattern) {
        StringBuilder encryptedString = new StringBuilder();
        int patternLength = pattern.length;
        int patternIndex = 0;

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (Character.isLetter(currentChar)) {
                int shift = pattern[patternIndex % patternLength];
                char base = Character.isUpperCase(currentChar) ? 'A' : 'a';
                char encryptedChar = (char) ((currentChar - base + shift) % 26 + base);
                encryptedString.append(encryptedChar);
                patternIndex++;
            } else {
                encryptedString.append(currentChar);
            }
        }
        return encryptedString.toString();
    }

    // find the maximum number from the array according to the series
    public  int[] findMaximum(int[] array, int series) {
        String seriesString = Integer.toString(series);
        int[] maxElements = new int[seriesString.length()];

        for (int s = 0; s < seriesString.length(); s++) {
            int nthMax = Character.getNumericValue(seriesString.charAt(s));
            int[] tempArray = array.clone();

            for (int n = 1; n <= nthMax; n++) {
                int max = Integer.MIN_VALUE;
                int maxIndex = -1;

                // Find the nth maximum element
                for (int i = 0; i < tempArray.length; i++) {
                    if (tempArray[i] > max) {
                        max = tempArray[i];
                        maxIndex = i;
                    }
                }

                if (maxIndex != -1) {
                    tempArray[maxIndex] = Integer.MIN_VALUE;
                }

                if (n == nthMax) {
                    maxElements[s] = max;
                }
            }
        }

        return maxElements;
    }

    // Function to encode numbers and find ASCII values of the max elements
    public  void encodingNumberToAsciiValue() {
        Scanner input = new Scanner(System.in);
        int number;
        int series;

        System.out.println(Constants.ENTER_NUMBER);
        if (!input.hasNextInt()) {
            System.out.println(Constants.INTEGER_WARNING);
        }
        else {
            number = input.nextInt();
            System.out.println(Constants.ENTER_SERIES);
            series = input.nextInt();
            String seriesString = Integer.toString(series);
            if (series > number) {
                System.out.println(Constants.ENTER_CORRECT_SERIES);
            } else {
                String numberToString = Integer.toString(number);
                int StringToNumber;
                int[] outputArray = new int[numberToString.length()];
                int index = 0;

                for (int i = 0; i < numberToString.length(); i++) {
                    char character = numberToString.charAt(i);
                    StringToNumber = Integer.parseInt(String.valueOf(character));
                    outputArray[index] = StringToNumber;
                    index++;
                }

                int[] max = findMaximum(outputArray, series);

                String asciiValues = "";

                for (int i = 0; i < max.length; i++) {
                    char character = (char) (max[i] + '0');
                    int asciiValue = (int) character;
                    asciiValues += asciiValue;
                }

                System.out.println(asciiValues);
            }
        }
    }

    // Function to display the main menu
    public void showMenu() {
        System.out.println(Constants.OPTION_1);
        System.out.println(Constants.OPTION_2);
        System.out.println(Constants.OPTION_3);
        System.out.println(Constants.OPTION_4);
        System.out.println(Constants.OPTION_5);
        System.out.println(Constants.OPTION_6);
    }

    // Function to ask user if they want to continue
    public boolean askToContinue(String action) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to " + action + " again? (yes/no)");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals(Constants.YES);
    }
}

public class StringManipulation {
    public static void main(String[] args) {
        StringManipulate manipulator = new StringManipulate();
        Scanner scanner = new Scanner(System.in);
        boolean run = true;

        while (run) {
            manipulator.showMenu();
            System.out.println(Constants.ENTER_CHOICE);
            if(!scanner.hasNextInt()) {
                System.out.println(Constants.ENTER_VALID_NUMBER);
            }
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    do {
                        System.out.println(Constants.ENTER_TO_GENERATE_PERMUTATION);
                        String input = scanner.nextLine();
                        manipulator.generateCombinations(input.toCharArray(), 0, input.length());
                    } while (manipulator.askToContinue(Constants.GENERATED_PERMUTATION));
                    break;

                case 2:
                    do {
                        manipulator.processNumber();
                    } while (manipulator.askToContinue(Constants.REDUCE_NUMBER));
                    break;

                case 3:
                    do {
                        System.out.println(Constants.ENTER_TO_FIND_CONSECUTIVE_SUM);
                        if (!scanner.hasNextInt()) {
                            System.out.println(Constants.ENTER_VALID_NUMBER);
                        }
                        int number = scanner.nextInt();
                        scanner.nextLine();
                        manipulator.findConsecutiveSums(number);

                    } while (manipulator.askToContinue(Constants.FIND_CONSECUTIVE_SUM));
                    break;

                case 4:
                    do {
                        System.out.println(Constants.ENTER_INPUT_STRING);
                        String inputString = scanner.nextLine();
                        System.out.println(Constants.ENTER_SHIFT_PATTERN);
                        String[] patternStr = scanner.nextLine().split(" ");
                        int[] pattern = new int[patternStr.length];
                        for (int i = 0; i < patternStr.length; i++) {
                            pattern[i] = Integer.parseInt(patternStr[i]);
                        }
                        String encryptedString = manipulator.encryptString(inputString, pattern);
                        System.out.println(Constants.ENCRYPTED_STRING + encryptedString);
                    } while (manipulator.askToContinue(Constants.CAESAR_CIPHER));
                    break;

                case 5:
                    do {
                        manipulator.encodingNumberToAsciiValue();
                    } while (manipulator.askToContinue(Constants.ENCODING_NUMBER));
                    break;
                case 6:
                    run = false;
                    break;

                default:
                    System.out.println(Constants.INVALID_CHOICE);
            }
        }

        System.out.println(Constants.PROGRAM_TERMINATED);
    }
}
