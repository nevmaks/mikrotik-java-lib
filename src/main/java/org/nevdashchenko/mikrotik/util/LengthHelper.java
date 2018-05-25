package org.nevdashchenko.mikrotik.util;

import org.nevdashchenko.mikrotik.exception.LengthException;

import java.io.UnsupportedEncodingException;

/**
 * Class for word length operations.
 * @author Maksym Nevdashchenko (maksym.nevdashchenko.gmail.com)
 * @version 1.0.0 30/04/2018
 * @since 1.0.0
 */
public final class LengthHelper {
    /**
     * Build <code>int</code> length from sequence of <code>byte</code> or return
     * <class>{@link LengthException}</class> on empty sequence.
     * @param source
     *              Source <code>byte</code> sequence
     * @return
     *              Decoded length
     * @throws LengthException
     *              Exception when the sequence is empty
     */
    public static int decodeLength(final byte ... source) throws LengthException{
        int result = 0;
        if (source.length > 0) {
            switch (source.length) {
                case 2:
                    source[0] &= 0x7F;
                    break;
                case 3:
                    source[0] &= 0x3F;
                    break;
                case 4:
                    source[0] &= 0x1F;
                    break;
            }
            for (int i = source.length < 5 ? 0 : 1; i < source.length; i++) {
                result = (result << 8) | source[i] & 0xFF;
            }
        } else {
            throw new LengthException("Byte sequence will be not empty.");
        }
        return result;
    }

    /**
     * Build sequence of <code>byte</code> from <code>int</code> length.
     * @param length
     *              Source length
     * @return
     *              Encoded <code>byte</code> sequence
     */
    public static byte[] encodeLength(final int length) {
        if (length < 0x80) {
            return getActualBytes(1, intToByteArray(length));
        } else {
            if (length < 0x4000) {
                return getActualBytes(2, intToByteArray(length | 0x8000));
            } else {
                if (length < 0x200000) {
                    return getActualBytes(3, intToByteArray(length | 0xC00000));
                } else {
                    if (length < 0x10000000) {
                        return getActualBytes(4, intToByteArray(length | 0xE0000000));
                    } else {
                        return getActualBytes(5, (byte) 0xF0, intToByteArray(length));
                    }
                }
            }
        }
    }

    /**
     * Method <code>lengthSize</code> calculate lengthSize on first byte or return
     * <class>{@link LengthException}</class> on incorrect value.
     * @param firstByte
     *                  First byte of current length
     * @return
     *                  Length size
     * @throws LengthException
     *                  Exception when first <code>byte</code> is incorrect
     */
    public static int lengthSize(final byte firstByte) throws LengthException {
        if ((firstByte & 0x80) == 0) {
            return 1;
        } else {
            if ((firstByte & 0xC0) == 0x80) {
                return 2;
            } else {
                if ((firstByte & 0xE0) == 0xC0) {
                    return 3;
                } else {
                    if ((firstByte & 0xF0) == 0xE0) {
                        return 4;
                    } else {
                        if ((firstByte & 0xF8) == 0xF0) {
                            return 5;
                        } else {
                            throw new LengthException(
                                    "Incorrect first byte mask. First byte is " +
                                            Integer.toHexString(Byte.toUnsignedInt(firstByte)));
                        }
                    }
                }
            }
        }
    }

    private static byte[] intToByteArray(final int i) {
        byte[] result = new byte[4];
        for (int j = 0, number = i; j < result.length; j++, number = number >> 8) {
            result[result.length - j - 1] = (byte) number;
        }
        return result;
    }

    private static byte[] getActualBytes(final int size, final byte ... source) {
        int actualSize = (size > 0 && size <= source.length) ? size : source.length;
        byte[] result = new byte[actualSize];
        System.arraycopy(source, source.length - result.length, result, 0, result.length);
        return result;
    }

    private static byte[] getActualBytes(final int size, final byte firstByte, final byte ... source) {
        byte[] result = new byte[source.length + 1];
        result[0] = firstByte;
        System.arraycopy(source, 0, result, 1, source.length);
        return getActualBytes(size, result);
    }
}
