/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */
package org.jescposImage;

import org.jescposConst.JescposEnum;

import java.io.ByteArrayOutputStream;

/**
 * Supply ESC/POS Raster bit Image commands.<p>
 * using <code>GS 'v' '0'</code>
 */
public class RasterBitImageWrapper extends JescposEnum implements ImageWrapperInterface{
    
    private Justification justification;
    RasterBitImageMode rasterBitImageMode;

    public RasterBitImageWrapper(){
        justification = Justification.LEFT;
        rasterBitImageMode = RasterBitImageMode.DEFAULT;
    }
    
    /**
     * Set horizontal justification of bar-code
     * @param justification left, center or right
     * @return this object
     */
    public RasterBitImageWrapper setJustification(Justification justification) {
        this.justification = justification;
        return this;
    }
    
    /**
     * Set the mode of Raster Bit Image.<p>
     * 
     * @param rasterBitImageMode mode to be used with GS v 0
     * @return this object
     * @see #getBytes(JescposImage)
     */
    public RasterBitImageWrapper setRasterBitImageMode(RasterBitImageMode rasterBitImageMode) {
        this.rasterBitImageMode = rasterBitImageMode;
        return this;
    }
    
    /**
     * Bit Image commands Assembly into ESC/POS bytes. <p>
     *  
     * Select justification <p>
     * ASCII ESC a n <p>
     *  
     * Print raster bit image <p>
     * ASCII GS v 0 m xL xH yL yH d1...dk <p>
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
        
        //render image
        bytes.write(GS);
        bytes.write('v');
        bytes.write('0');
        bytes.write(rasterBitImageMode.value);

        //config image direction
        ImageConfig.imageConf(bytes, image, image.getHorizontalBytesOfRaster());

        return bytes.toByteArray();
    }
    
}
