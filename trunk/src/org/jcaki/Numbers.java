/*
 *
 * Copyright (c) 2008, All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Portions of the code may be copied from Google Collections
 * or Apache Commons projects.
 */

package org.jcaki;

import static org.jcaki.Preconditions.checkArgument;
import static org.jcaki.Preconditions.checkNotNull;

public class Numbers {

    private Numbers() {
    }

    /**
     * converts a byte to a hexadecimal string with special xx formatting. it always return two characters
     * <pre>
     * <p>for 0 , returns "00"
     * <p>for 1..15 returns "00".."0f"
     * </pre>
     *
     * @param b byte
     * @return hex string.
     */
    public static String toHexWithZeros(byte b) {
        if (b == 0)
            return "00";
        String s = toHex(b);
        if (s.length() == 1)
            return "0" + s;
        else
            return s;
    }

    /**
     * converts a byte to a hexadecimal string. it eliminates left zeros.
     * <pre>
     * <p>for 0 , returns "0"
     * <p>for 1 to 15 returns "0".."f"
     * </pre>
     *
     * @param b byte
     * @return hex string.
     */
    public static String toHex(byte b) {
        return String.format("%x", b);
    }

    /**
     * converts byte array to a hexadecimal string. it ignores the zeros on the left side.
     * <pre>
     * <p>{0x00, 0x0c, 0x11, 0x01, 0x00} -> "c110100"
     * </pre>
     *
     * @param bytes byte array, should be non-null, and not empty.
     * @return a String representation of the number represented by the byte array.
     *         empty string is byte array is empty.
     * @throws NullPointerException if byte array is null
     */
    public static String toHex(byte[] bytes) {
        checkNotNull(bytes, "byte array cannot be null.");
        if (bytes.length == 0) return Strings.EMPTY_STRING;
        StringBuilder builder = new StringBuilder(bytes.length * 2);
        boolean nonZeroFound = false;
        for (byte b : bytes) {
            if (!nonZeroFound) {
                if (b != 0) {
                    builder.append(toHex(b));
                    nonZeroFound = true;
                }
                continue;
            }
            builder.append(toHexWithZeros(b));
        }
        //if all bytes are zero, loop above produces nothing. so we return "0"
        if (builder.length() == 0 && bytes.length > 0)
            return "0";
        else
            return builder.toString();
    }

    /**
     * converts a byte array to a hexadecimal string with special xx formatting. it does not ignore the left zeros.
     * <pre>
     * <p>{0x00, 0x0c, 0x11, 0x00} -> "000c1100"
     * <pre>
     *
     * @param bytes byte array
     * @return hex string.  empty string is byte array is empty.
     * @throws NullPointerException if byte array is null
     */
    public static String toHexWithZeros(byte[] bytes) {
        checkNotNull(bytes, "byte array cannot be null.");
        if (bytes.length == 0) return Strings.EMPTY_STRING;
        StringBuilder builder = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            builder.append(toHexWithZeros(b));
        }
        return builder.toString();
    }

    /**
     * Generates a single check digit for a String representation of a number.
     * if number is null, negative
     *
     * @param number string representation of the number.
     * @return int
     */
    int generateCheckDigit(String number) {
        checkNotNull(number, "number string cannot be null");
        checkArgument(Strings.hasText(number), "number must have content.");
        int sum = 0;
        for (int i = 0; i < number.length(); i++) {
            char currentChar = number.charAt(number.length() - i - 1);
            int digit = currentChar - 48;
            int weight;
            if (i % 2 == 0) {
                weight = (2 * digit) - digit / 5 * 9;
            } else {
                weight = digit;
            }
            sum += weight;
        }
        sum = Math.abs(sum) + 10;
        return (10 - (sum % 10)) % 10;
    }

    /**
     * Name: hasValidCheckDigit
     * Description: Validate Check Digit
     *
     * @param number string representation of the number with checkdigit.
     * @return int
     */
    boolean hasValidCheckDigit(String number) {
        if (!Strings.hasText(number) || number.trim().length() < 2)
            return false;
        int calculatedCheckDigit = generateCheckDigit(number.substring(0, number.length() - 1));
        char checkDigit = number.charAt(number.length() - 1);
        int intCheckDigit = checkDigit - 48;
        return calculatedCheckDigit == intCheckDigit;
    }

    /**
     * returnds the digit count for a number 0->1, 12->2, 1345->4 (-12)->2
     * @param number  a long number
     * @return decimal digit count.
     */
    public static int digitCount(long number) {
        int st = 0;
        do {
            number = number / 10;
            st++;
        } while (number > 0);
        return st;
    }


}
