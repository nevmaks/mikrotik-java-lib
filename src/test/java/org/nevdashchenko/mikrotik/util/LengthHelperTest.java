package org.nevdashchenko.mikrotik.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nevdashchenko.mikrotik.exception.LengthException;

import static org.assertj.core.api.Assertions.*;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class LengthHelperTest {

    @Test
    public void decodeLengthWillReturnCorrectLengthForFourBytes() throws LengthException {
        assertThat(LengthHelper.decodeLength((byte)0xE0,(byte)0x20,(byte)0x00,(byte)0x00)).isEqualTo(0x0200000);
        assertThat(LengthHelper.decodeLength((byte)0xE0,(byte)0x40,(byte)0x00,(byte)0x00)).isEqualTo(0x0400000);
        assertThat(LengthHelper.decodeLength((byte)0xE0,(byte)0x80,(byte)0x00,(byte)0x00)).isEqualTo(0x0800000);
        assertThat(LengthHelper.decodeLength((byte)0xE1,(byte)0x00,(byte)0x00,(byte)0x00)).isEqualTo(0x1000000);
        assertThat(LengthHelper.decodeLength((byte)0xE2,(byte)0x00,(byte)0x00,(byte)0x00)).isEqualTo(0x2000000);
        assertThat(LengthHelper.decodeLength((byte)0xE4,(byte)0x00,(byte)0x00,(byte)0x00)).isEqualTo(0x4000000);
        assertThat(LengthHelper.decodeLength((byte)0xE8,(byte)0x00,(byte)0x00,(byte)0x00)).isEqualTo(0x8000000);
        assertThat(LengthHelper.decodeLength((byte)0xEF,(byte)0xFF,(byte)0xFF,(byte)0xFF)).isEqualTo(0xFFFFFFF);
    }

    @Test
    public void decodeLengthWillReturnCorrectLengthForFiveBytes() throws LengthException {
        assertThat(LengthHelper.decodeLength((byte)0xF0,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00)).isEqualTo(0x01000000);
        assertThat(LengthHelper.decodeLength((byte)0xF0,(byte)0x02,(byte)0x00,(byte)0x00,(byte)0x00)).isEqualTo(0x02000000);
        assertThat(LengthHelper.decodeLength((byte)0xF0,(byte)0x04,(byte)0x00,(byte)0x00,(byte)0x00)).isEqualTo(0x04000000);
        assertThat(LengthHelper.decodeLength((byte)0xF0,(byte)0x08,(byte)0x00,(byte)0x00,(byte)0x00)).isEqualTo(0x08000000);
        assertThat(LengthHelper.decodeLength((byte)0xF0,(byte)0x10,(byte)0x00,(byte)0x00,(byte)0x00)).isEqualTo(0x10000000);
        assertThat(LengthHelper.decodeLength((byte)0xF0,(byte)0x20,(byte)0x00,(byte)0x00,(byte)0x00)).isEqualTo(0x20000000);
        assertThat(LengthHelper.decodeLength((byte)0xF0,(byte)0x40,(byte)0x00,(byte)0x00,(byte)0x00)).isEqualTo(0x40000000);
        assertThat(LengthHelper.decodeLength((byte)0xF0,(byte)0x7F,(byte)0xFF,(byte)0xFF,(byte)0xFF)).isEqualTo(0x7FFFFFFF);
    }

    @Test
    public void decodeLengthWillReturnCorrectLengthForOneByte() throws LengthException {
        assertThat(LengthHelper.decodeLength((byte)0x00)).isEqualTo(0x00);
        assertThat(LengthHelper.decodeLength((byte)0x01)).isEqualTo(0x01);
        assertThat(LengthHelper.decodeLength((byte)0x02)).isEqualTo(0x02);
        assertThat(LengthHelper.decodeLength((byte)0x04)).isEqualTo(0x04);
        assertThat(LengthHelper.decodeLength((byte)0x08)).isEqualTo(0x08);
        assertThat(LengthHelper.decodeLength((byte)0x10)).isEqualTo(0x10);
        assertThat(LengthHelper.decodeLength((byte)0x20)).isEqualTo(0x20);
        assertThat(LengthHelper.decodeLength((byte)0x40)).isEqualTo(0x40);
        assertThat(LengthHelper.decodeLength((byte)0x7F)).isEqualTo(0x7F);
    }

    @Test
    public void decodeLengthWillReturnCorrectLengthForThreeBytes() throws LengthException {
        assertThat(LengthHelper.decodeLength((byte)0xC0,(byte)0x40,(byte)0x00)).isEqualTo(0x004000);
        assertThat(LengthHelper.decodeLength((byte)0xC0,(byte)0x80,(byte)0x00)).isEqualTo(0x008000);
        assertThat(LengthHelper.decodeLength((byte)0xC1,(byte)0x00,(byte)0x00)).isEqualTo(0x010000);
        assertThat(LengthHelper.decodeLength((byte)0xC2,(byte)0x00,(byte)0x00)).isEqualTo(0x020000);
        assertThat(LengthHelper.decodeLength((byte)0xC4,(byte)0x00,(byte)0x00)).isEqualTo(0x040000);
        assertThat(LengthHelper.decodeLength((byte)0xC8,(byte)0x00,(byte)0x00)).isEqualTo(0x080000);
        assertThat(LengthHelper.decodeLength((byte)0xD0,(byte)0x00,(byte)0x00)).isEqualTo(0x100000);
        assertThat(LengthHelper.decodeLength((byte)0xDF,(byte)0xFF,(byte)0xFF)).isEqualTo(0x1FFFFF);
    }

    @Test
    public void decodeLengthWillReturnCorrectLengthForTwoBytes() throws LengthException {
        assertThat(LengthHelper.decodeLength((byte)0x80,(byte)0x80)).isEqualTo(0x0080);
        assertThat(LengthHelper.decodeLength((byte)0x81,(byte)0x00)).isEqualTo(0x0100);
        assertThat(LengthHelper.decodeLength((byte)0x82,(byte)0x00)).isEqualTo(0x0200);
        assertThat(LengthHelper.decodeLength((byte)0x84,(byte)0x00)).isEqualTo(0x0400);
        assertThat(LengthHelper.decodeLength((byte)0x88,(byte)0x00)).isEqualTo(0x0800);
        assertThat(LengthHelper.decodeLength((byte)0x90,(byte)0x00)).isEqualTo(0x1000);
        assertThat(LengthHelper.decodeLength((byte)0xA0,(byte)0x00)).isEqualTo(0x2000);
        assertThat(LengthHelper.decodeLength((byte)0xBF,(byte)0xFF)).isEqualTo(0x3FFF);
    }

    @Test
    public void decodeLengthWillReturnException() {
        assertThatExceptionOfType(LengthException.class).
                isThrownBy(LengthHelper::decodeLength).
                withMessage("Byte sequence will be not empty.");
    }

    @Test
    public void encodeLengthWillReturnCorrectSequenceWithFiveBytes(){
        assertThat(LengthHelper.encodeLength(0x10000000)).containsSequence((byte) 0xF0,(byte) 0x10,(byte) 0x00,(byte) 0x00,(byte) 0x00);
        assertThat(LengthHelper.encodeLength(0x20000000)).containsSequence((byte) 0xF0,(byte) 0x20,(byte) 0x00,(byte) 0x00,(byte) 0x00);
        assertThat(LengthHelper.encodeLength(0x40000000)).containsSequence((byte) 0xF0,(byte) 0x40,(byte) 0x00,(byte) 0x00,(byte) 0x00);
        assertThat(LengthHelper.encodeLength(0x7FFFFFFF)).containsSequence((byte) 0xF0,(byte) 0x7F,(byte) 0xFF,(byte) 0xFF,(byte) 0xFF);
    }

    @Test
    public void encodeLengthWillReturnCorrectSequenceWithFourBytes() {
        assertThat(LengthHelper.encodeLength(0x00200000)).containsSequence((byte) 0xE0,(byte) 0x20,(byte) 0x00,(byte) 0x00);
        assertThat(LengthHelper.encodeLength(0x00400000)).containsSequence((byte) 0xE0,(byte) 0x40,(byte) 0x00,(byte) 0x00);
        assertThat(LengthHelper.encodeLength(0x00800000)).containsSequence((byte) 0xE0,(byte) 0x80,(byte) 0x00,(byte) 0x00);
        assertThat(LengthHelper.encodeLength(0x01000000)).containsSequence((byte) 0xE1,(byte) 0x00,(byte) 0x00,(byte) 0x00);
        assertThat(LengthHelper.encodeLength(0x02000000)).containsSequence((byte) 0xE2,(byte) 0x00,(byte) 0x00,(byte) 0x00);
        assertThat(LengthHelper.encodeLength(0x04000000)).containsSequence((byte) 0xE4,(byte) 0x00,(byte) 0x00,(byte) 0x00);
        assertThat(LengthHelper.encodeLength(0x08000000)).containsSequence((byte) 0xE8,(byte) 0x00,(byte) 0x00,(byte) 0x00);
        assertThat(LengthHelper.encodeLength(0x0FFFFFFF)).containsSequence((byte) 0xEF,(byte) 0xFF,(byte) 0xFF,(byte) 0xFF);
    }

    @Test
    public void encodeLengthWillReturnCorrectSequenceWithOneByte() {
        assertThat(LengthHelper.encodeLength(0x00)).containsSequence((byte) 0x00);
        assertThat(LengthHelper.encodeLength(0x01)).containsSequence((byte) 0x01);
        assertThat(LengthHelper.encodeLength(0x02)).containsSequence((byte) 0x02);
        assertThat(LengthHelper.encodeLength(0x04)).containsSequence((byte) 0x04);
        assertThat(LengthHelper.encodeLength(0x08)).containsSequence((byte) 0x08);
        assertThat(LengthHelper.encodeLength(0x10)).containsSequence((byte) 0x10);
        assertThat(LengthHelper.encodeLength(0x20)).containsSequence((byte) 0x20);
        assertThat(LengthHelper.encodeLength(0x40)).containsSequence((byte) 0x40);
        assertThat(LengthHelper.encodeLength(0x7F)).containsSequence((byte) 0x7F);
    }

    @Test
    public void encodeLengthWillReturnCorrectSequenceWithThreeBytes() {
        assertThat(LengthHelper.encodeLength(0x004000)).containsSequence((byte) 0xC0,(byte) 0x40,(byte) 0x00);
        assertThat(LengthHelper.encodeLength(0x008000)).containsSequence((byte) 0xC0,(byte) 0x80,(byte) 0x00);
        assertThat(LengthHelper.encodeLength(0x010000)).containsSequence((byte) 0xC1,(byte) 0x00,(byte) 0x00);
        assertThat(LengthHelper.encodeLength(0x020000)).containsSequence((byte) 0xC2,(byte) 0x00,(byte) 0x00);
        assertThat(LengthHelper.encodeLength(0x040000)).containsSequence((byte) 0xC4,(byte) 0x00,(byte) 0x00);
        assertThat(LengthHelper.encodeLength(0x080000)).containsSequence((byte) 0xC8,(byte) 0x00,(byte) 0x00);
        assertThat(LengthHelper.encodeLength(0x100000)).containsSequence((byte) 0xD0,(byte) 0x00,(byte) 0x00);
        assertThat(LengthHelper.encodeLength(0x1FFFFF)).containsSequence((byte) 0xDF,(byte) 0xFF,(byte) 0xFF);
    }

    @Test
    public void encodeLengthWillReturnCorrectSequenceWithTwoBytes() {
        assertThat(LengthHelper.encodeLength(0x0080)).containsSequence((byte) 0x80,(byte) 0x80);
        assertThat(LengthHelper.encodeLength(0x0100)).containsSequence((byte) 0x81,(byte) 0x00);
        assertThat(LengthHelper.encodeLength(0x0200)).containsSequence((byte) 0x82,(byte) 0x00);
        assertThat(LengthHelper.encodeLength(0x0400)).containsSequence((byte) 0x84,(byte) 0x00);
        assertThat(LengthHelper.encodeLength(0x0800)).containsSequence((byte) 0x88,(byte) 0x00);
        assertThat(LengthHelper.encodeLength(0x1000)).containsSequence((byte) 0x90,(byte) 0x00);
        assertThat(LengthHelper.encodeLength(0x2000)).containsSequence((byte) 0xA0,(byte) 0x00);
        assertThat(LengthHelper.encodeLength(0x3FFF)).containsSequence((byte) 0xBF,(byte) 0xFF);
    }

    @Test
    public void lengthSizeWillReturnException()  {
        assertThatExceptionOfType(LengthException.class).
                isThrownBy(() -> LengthHelper.lengthSize((byte)0xF8)).
                withMessage("Incorrect first byte mask. First byte is f8");
        assertThatExceptionOfType(LengthException.class).
                isThrownBy(() -> LengthHelper.lengthSize((byte)0xF9)).
                withMessage("Incorrect first byte mask. First byte is f9");
        assertThatExceptionOfType(LengthException.class).
                isThrownBy(() -> LengthHelper.lengthSize((byte)0xFB)).
                withMessage("Incorrect first byte mask. First byte is fb");
        assertThatExceptionOfType(LengthException.class).
                isThrownBy(() -> LengthHelper.lengthSize((byte)0xFC)).
                withMessage("Incorrect first byte mask. First byte is fc");
    }

    @Test
    public void lengthSizeWillReturnFiveBytes() throws LengthException  {
        assertThat(LengthHelper.lengthSize((byte)0xF0)).isEqualTo(5);
        assertThat(LengthHelper.lengthSize((byte)0xF1)).isEqualTo(5);
        assertThat(LengthHelper.lengthSize((byte)0xF2)).isEqualTo(5);
        assertThat(LengthHelper.lengthSize((byte)0xF4)).isEqualTo(5);
    }

    @Test
    public void lengthSizeWillReturnFourBytes() throws LengthException  {
        assertThat(LengthHelper.lengthSize((byte)0xE0)).isEqualTo(4);
        assertThat(LengthHelper.lengthSize((byte)0xE1)).isEqualTo(4);
        assertThat(LengthHelper.lengthSize((byte)0xE2)).isEqualTo(4);
        assertThat(LengthHelper.lengthSize((byte)0xE4)).isEqualTo(4);
        assertThat(LengthHelper.lengthSize((byte)0xE8)).isEqualTo(4);
    }

    @Test
    public void lengthSizeWillReturnOneByte() throws LengthException  {
        assertThat(LengthHelper.lengthSize((byte)0x00)).isEqualTo(1);
        assertThat(LengthHelper.lengthSize((byte)0x01)).isEqualTo(1);
        assertThat(LengthHelper.lengthSize((byte)0x02)).isEqualTo(1);
        assertThat(LengthHelper.lengthSize((byte)0x04)).isEqualTo(1);
        assertThat(LengthHelper.lengthSize((byte)0x08)).isEqualTo(1);
        assertThat(LengthHelper.lengthSize((byte)0x10)).isEqualTo(1);
        assertThat(LengthHelper.lengthSize((byte)0x20)).isEqualTo(1);
        assertThat(LengthHelper.lengthSize((byte)0x40)).isEqualTo(1);
    }

    @Test
    public void lengthSizeWillReturnThreeBytes() throws LengthException  {
        assertThat(LengthHelper.lengthSize((byte)0xC0)).isEqualTo(3);
        assertThat(LengthHelper.lengthSize((byte)0xC1)).isEqualTo(3);
        assertThat(LengthHelper.lengthSize((byte)0xC2)).isEqualTo(3);
        assertThat(LengthHelper.lengthSize((byte)0xC4)).isEqualTo(3);
        assertThat(LengthHelper.lengthSize((byte)0xC8)).isEqualTo(3);
        assertThat(LengthHelper.lengthSize((byte)0xD0)).isEqualTo(3);
    }

    @Test
    public void lengthSizeWillReturnTwoBytes() throws LengthException {
        assertThat(LengthHelper.lengthSize((byte)0x80)).isEqualTo(2);
        assertThat(LengthHelper.lengthSize((byte)0x81)).isEqualTo(2);
        assertThat(LengthHelper.lengthSize((byte)0x82)).isEqualTo(2);
        assertThat(LengthHelper.lengthSize((byte)0x84)).isEqualTo(2);
        assertThat(LengthHelper.lengthSize((byte)0x88)).isEqualTo(2);
        assertThat(LengthHelper.lengthSize((byte)0x90)).isEqualTo(2);
        assertThat(LengthHelper.lengthSize((byte)0xA0)).isEqualTo(2);
    }
}
