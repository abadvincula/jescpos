/*
 * Copyright 2019 Anderson Silva
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the main file or LICENSE file.
 */
package org.jescposImage;
import java.io.ByteArrayOutputStream;

public class ImageConfig {

    static void imageConf(ByteArrayOutputStream bytes, JescposImage image, int horizontalBits){
        //  bits in horizontal direction for the bit image
        int xL = horizontalBits & 0xFF;
        int xH = (horizontalBits & 0xFF00) >> 8 ;
        //
        //  bits in vertical direction for the bit image
        int verticalBits = image.getHeightOfImageInBits();
        // getting first and second bytes separatted
        int yL = verticalBits & 0xFF;
        int yH = (verticalBits & 0xFF00) >> 8 ;

        bytes.write(xL);
        bytes.write(xH);
        bytes.write(yL);
        bytes.write(yH);
        // write bytes
        byte [] rasterBytes = image.getRasterBytes().toByteArray();
        bytes.write(rasterBytes,0,rasterBytes.length);
    }
}
