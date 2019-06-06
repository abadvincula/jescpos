/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */
package com.github.andersonsrocha.jescposImage;

import com.github.andersonsrocha.jescposConst.JescposEnum;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Supply ESC/POS Bit Image commands.<p>
 * using <code>ESC '*'</code>
 */
public class BitImageWrapper extends JescposEnum implements ImageWrapperInterface {

    private Justification justification;
    private BitImageMode mode;

    public BitImageWrapper() {
        justification = Justification.LEFT;
        mode = BitImageMode._24DEFAULT;

    }

    /**
     * Set horizontal justification of bar-code
     *
     * @param justification left, center or right
     * @return this object
     */
    public BitImageWrapper setJustification(Justification justification) {
        this.justification = justification;
        return this;
    }

    /**
     * Select bit-image mode. <p>
     *
     * @param mode mode to be used on command ESC *
     * @return this object
     * @see #getBytes(JescposImage)
     */
    public BitImageWrapper setMode(BitImageMode mode) {
        this.mode = mode;
        return this;
    }

    /**
     * Bit Image commands Assembly into ESC/POS bytes. <p>
     * <p>
     * Select justification <p>
     * ASCII ESC a n <p>
     * <p>
     * Set lineSpace in bytes <p>
     * ASCII ESC '3' n <p>
     * <p>
     * write all rows of the raster image <p>
     * ASCII ESC ✻ m nL nH d1 ... dk <p>
     *
     * @param image to be printed
     * @return bytes of ESC/POS
     * @see JescposImage
     */

    @Override
    public byte[] getBytes(JescposImage image) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        //justification
        bytes.write(ESC);
        bytes.write('a');
        bytes.write(justification.value);

        //line feed
        bytes.write(ESC);
        bytes.write('3');
        bytes.write(16);

        // getting first and second bytes separatted
        int nL = image.getWidthOfImageInBits() & 0xFF;
        int nH = (image.getWidthOfImageInBits() & 0xFF00) >> 8;

        List<ByteArrayOutputStream> RasterColumns = image.getRasterRows(mode.bitsForVerticalData);
        for (ByteArrayOutputStream rol : RasterColumns) {
            //write one rol to print
            bytes.write(ESC);
            bytes.write('*');
            bytes.write(mode.value);
            bytes.write(nL);
            bytes.write(nH);
            bytes.write(rol.toByteArray(), 0, rol.size());

            bytes.write(LF);
        }

        return bytes.toByteArray();
    }

}
