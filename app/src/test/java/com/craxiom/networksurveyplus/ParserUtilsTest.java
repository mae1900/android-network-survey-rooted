package com.craxiom.networksurveyplus;

import com.craxiom.networksurveyplus.messages.ParserUtils;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the parser utils class.
 *
 * @since 0.1.0
 */
public class ParserUtilsTest
{
    @Test
    public void testCrc16CcittCalculation()
    {
        final int expectedCrc = 0x29b1;
        final byte[] inputBytes = "123456789".getBytes(StandardCharsets.US_ASCII);

        final int crc = ParserUtils.calculateCrc16Ccitt(inputBytes);
        assertEquals(expectedCrc, crc);
    }

    @Test
    public void testCrc16X25Calculation()
    {
        final int expectedCrc = (short) 0x906e;
        final byte[] inputBytes = "123456789".getBytes(StandardCharsets.US_ASCII);

        final int crc = ParserUtils.calculateCrc16X25(inputBytes, inputBytes.length);
        assertEquals(expectedCrc, crc);
    }

    /**
     * Tests the original Diag Command that is used to activate the LTE RRC messages from the rrc_filter_diag.cfg.
     */
    @Test
    public void testCrc16X25LteDiagCommandCalculation()
    {
        final int expectedCrc = (short) 0xA1FA;
        final byte[] diagCommandBytes = {(byte) 0x73, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x03, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x0B, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x9B, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0x03, (byte) 0x0F, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x07, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00};

        final int crc = ParserUtils.calculateCrc16X25(diagCommandBytes, diagCommandBytes.length);
        assertEquals(expectedCrc, crc);
    }

    /**
     * Tests the modified Diag Command that is used to activate the LTE NAS messages in addition to the LTE RRC messages
     * as part of the rrc_filter_diag_edit.cfg.
     */
    @Test
    public void testCrc16X25LteDiagCommandModificationCalculation()
    {
        final int expectedCrc = (short) 0xD606;
        final byte[] diagCommandBytes = {(byte) 0x73, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x03, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x0B, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x9B, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0x03, (byte) 0x0F, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x07, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x0F, (byte) 0x3C, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00};

        final int crc = ParserUtils.calculateCrc16X25(diagCommandBytes, diagCommandBytes.length);
        assertEquals(expectedCrc, crc);
    }

    @Test
    public void testCrcQcdmMessage()
    {
        final short expectedCrc = 0x2d53;
        final byte[] qcdmBytes = {(byte) 0x60, (byte) 0x4c, (byte) 0x00, (byte) 0x32,
                (byte) 0x6b, (byte) 0xe5, (byte) 0xb9, (byte) 0xa4, (byte) 0xfb, (byte) 0x75, (byte) 0xdd, (byte) 0xee, (byte) 0x00, (byte) 0x13, (byte) 0x06, (byte) 0x6d, (byte) 0x73, (byte) 0x6d, (byte) 0x2f, (byte) 0x6d,
                (byte) 0x6f, (byte) 0x64, (byte) 0x65, (byte) 0x6d, (byte) 0x2f, (byte) 0x72, (byte) 0x6f, (byte) 0x6f, (byte) 0x74, (byte) 0x5f, (byte) 0x70, (byte) 0x64, (byte) 0x00, (byte) 0x31, (byte) 0xeb, (byte) 0xb9,
                (byte) 0xa4, (byte) 0x11, (byte) 0x06, (byte) 0x70, (byte) 0xe0, (byte) 0x8c, (byte) 0x67, (byte) 0x85, (byte) 0x25, (byte) 0x4d, (byte) 0x93, (byte) 0x8e, (byte) 0xf9, (byte) 0x11, (byte) 0xc4, (byte) 0xf1,
                (byte) 0x98, (byte) 0xdc, (byte) 0x4c, (byte) 0x32, (byte) 0xeb, (byte) 0xb9, (byte) 0xa4, (byte) 0x13, (byte) 0x07, (byte) 0x6d, (byte) 0x73, (byte) 0x6d, (byte) 0x2f, (byte) 0x6d, (byte) 0x6f, (byte) 0x64,
                (byte) 0x65, (byte) 0x6d, (byte) 0x2f, (byte) 0x77, (byte) 0x6c, (byte) 0x61, (byte) 0x6e, (byte) 0x5f, (byte) 0x70, (byte) 0x64, (byte) 0x00};

        final short crc = ParserUtils.calculateCrc16X25(qcdmBytes, qcdmBytes.length);
        assertEquals(expectedCrc, crc);
    }

    @Test
    public void testQcdmMessageEscaped7e()
    {
        final byte[] expectedResult = {(byte) 0x98, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x60, (byte) 0x2b, (byte) 0x00, (byte) 0x92, (byte) 0x47, (byte) 0xb4, (byte) 0x0c, (byte) 0x64, (byte) 0x0b, (byte) 0x76, (byte) 0xfb, (byte) 0xee, (byte) 0x00, (byte) 0x06, (byte) 0x01, (byte) 0x86, (byte) 0xca, (byte) 0x0c, (byte) 0x64, (byte) 0x14, (byte) 0x00, (byte) 0x7e, (byte) 0xea, (byte) 0x0c, (byte) 0x64, (byte) 0x04, (byte) 0xff, (byte) 0xff, (byte) 0x7c, (byte) 0xc6, (byte) 0x85, (byte) 0xaa, (byte) 0x0c, (byte) 0x64, (byte) 0x09, (byte) 0x85, (byte) 0x2a, (byte) 0x28, (byte) 0x5a, (byte) 0xac, (byte) 0x0b, (byte) 0x76, (byte) 0xfb, (byte) 0xee, (byte) 0x00, (byte) 0x09, (byte) 0xd8, (byte) 0x3a};

        final byte[] qcdmMessage = {(byte) 0x98, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x60, (byte) 0x2b, (byte) 0x00, (byte) 0x92, (byte) 0x47, (byte) 0xb4, (byte) 0x0c, (byte) 0x64, (byte) 0x0b, (byte) 0x76, (byte) 0xfb, (byte) 0xee, (byte) 0x00, (byte) 0x06, (byte) 0x01, (byte) 0x86, (byte) 0xca, (byte) 0x0c, (byte) 0x64, (byte) 0x14, (byte) 0x00, (byte) 0x7d, (byte) 0x5e, (byte) 0xea, (byte) 0x0c, (byte) 0x64, (byte) 0x04, (byte) 0xff, (byte) 0xff, (byte) 0x7c, (byte) 0xc6, (byte) 0x85, (byte) 0xaa, (byte) 0x0c, (byte) 0x64, (byte) 0x09, (byte) 0x85, (byte) 0x2a, (byte) 0x28, (byte) 0x5a, (byte) 0xac, (byte) 0x0b, (byte) 0x76, (byte) 0xfb, (byte) 0xee, (byte) 0x00, (byte) 0x09, (byte) 0xd8, (byte) 0x3a, (byte) 0x7e};

        final byte[] unwrappedQcdmMessage = ParserUtils.getNextDiagMessageBytes(new ByteArrayInputStream(qcdmMessage));

        assertArrayEquals(expectedResult, unwrappedQcdmMessage);
    }

    @Test
    public void testQcdmMessageEscaped7eAnd7d()
    {
        final byte[] expectedResult = {(byte) 60, (byte) 0x3e, (byte) 0x00, (byte) 0x2d, (byte) 0x6b, (byte) 0x32, (byte) 0x92, (byte) 0x9f, (byte) 0x6d, (byte) 0x30, (byte) 0x1c, (byte) 0xef, (byte) 0x00, (byte) 0x03, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x9f, (byte) 0xe7, (byte) 0x92, (byte) 0x9f, (byte) 0x0f, (byte) 0x00, (byte) 0xb9, (byte) 0x6b, (byte) 0x9b, (byte) 0x24, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x4a, (byte) 0x08, (byte) 0x19, (byte) 0xa7, (byte) 0x7e, (byte) 0x13, (byte) 0xc3, (byte) 0x68, (byte) 0x8f, (byte) 0x7d, (byte) 0xb0, (byte) 0x6d, (byte) 0x30, (byte) 0x1c, (byte) 0xef, (byte) 0x00, (byte) 0x03, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0xc3, (byte) 0x68, (byte) 0x59, (byte) 0x9c, (byte) 0xb2, (byte) 0x6d, (byte) 0x30, (byte) 0x1c, (byte) 0xef, (byte) 0x00, (byte) 0x03, (byte) 0x02, (byte) 0x00, (byte) 0x00, (byte) 0x11, (byte) 0x1b};

        final byte[] qcdmMessage = {(byte) 60, (byte) 0x3e, (byte) 0x00, (byte) 0x2d, (byte) 0x6b, (byte) 0x32, (byte) 0x92, (byte) 0x9f, (byte) 0x6d, (byte) 0x30, (byte) 0x1c, (byte) 0xef, (byte) 0x00, (byte) 0x03, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x9f, (byte) 0xe7, (byte) 0x92, (byte) 0x9f, (byte) 0x0f, (byte) 0x00, (byte) 0xb9, (byte) 0x6b, (byte) 0x9b, (byte) 0x24, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x4a, (byte) 0x08, (byte) 0x19, (byte) 0xa7, (byte) 0x7d, (byte) 0x5e, (byte) 0x13, (byte) 0xc3, (byte) 0x68, (byte) 0x8f, (byte) 0x7d, (byte) 0x5d, (byte) 0xb0, (byte) 0x6d, (byte) 0x30, (byte) 0x1c, (byte) 0xef, (byte) 0x00, (byte) 0x03, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0xc3, (byte) 0x68, (byte) 0x59, (byte) 0x9c, (byte) 0xb2, (byte) 0x6d, (byte) 0x30, (byte) 0x1c, (byte) 0xef, (byte) 0x00, (byte) 0x03, (byte) 0x02, (byte) 0x00, (byte) 0x00, (byte) 0x11, (byte) 0x1b, (byte) 0x7e};

        final byte[] unwrappedQcdmMessage = ParserUtils.getNextDiagMessageBytes(new ByteArrayInputStream(qcdmMessage));

        assertArrayEquals(expectedResult, unwrappedQcdmMessage);
    }
}
