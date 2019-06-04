/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */
package org.jescposImage;

import org.jescposConst.JescposEnum;
import java.io.ByteArrayOutputStream;

/**
 * Supply ESC/POS Graphics print Image commands.<p>
 * using <code>GS(L</code>
 */
public class GraphicsImageWrapper extends JescposEnum implements ImageWrapperInterface{

    private Justification justification;
    GraphicsImageBxBy graphicsImageBxBy;


    public GraphicsImageWrapper(){
        justification = Justification.LEFT;
        graphicsImageBxBy = GraphicsImageBxBy.DEFAULT;
    }
    
    /**
     * Set horizontal justification of bar-code
     * @param justification left, center or right
     * @return this object
     */
    public GraphicsImageWrapper setJustification(Justification justification) {
        this.justification = justification;
        return this;
    }
    
    /**
     * set values of Bx and By referring to the image size. <p> 
     * @param graphicsImageBxBy values used on function 112
     * @return this object
     * @see #getBytes(JescposImage)
     */
    public GraphicsImageWrapper setGraphicsImageBxBy(GraphicsImageBxBy graphicsImageBxBy) {
        this.graphicsImageBxBy = graphicsImageBxBy;
        return this;
    }
    
    
    /**
     * Bit Image commands Assembly into ESC/POS bytes. <p>
     *  
     * Select justification <p>
     * ASCII ESC a n <p>
     *  
     * function 112 Store the graphics data in the print buffer  <p>
     * GS(L pL pH m fn a bx by c xL xH yL yH d1...dk  <p>
     * 
     * function 050 Prints the buffered graphics data <p>
     * GS ( L pL pH m fn  <p>
     * 
     * @param image to be printed
     * @return bytes of ESC/POS
     * @see JescposImage#getRasterBytes()
     * @see JescposImage#getRasterSizeInBytes()
     */ 
    @Override
    public byte[] getBytes(JescposImage image) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        //justification
        bytes.write(ESC);
        bytes.write('a');
        bytes.write(justification.value);

        int paramSize = image.getRasterSizeInBytes() + 10;
        int pL = paramSize & 0xFF;
        int pH = (paramSize & 0xFF00) >> 8 ;

        bytes.write(GS);
        bytes.write('(');
        bytes.write('L');
        bytes.write(pL); // pl
        bytes.write(pH); // ph
        bytes.write(48); // m
        bytes.write(112); //fn
        bytes.write(48); // a
        bytes.write(graphicsImageBxBy.bx); // bx
        bytes.write(graphicsImageBxBy.by); // by
        bytes.write(49); // c

        //config image direction
        ImageConfig.imageConf(bytes, image, image.getWidthOfImageInBits());
        
        //function 050
        bytes.write(GS);
        bytes.write('(');
        bytes.write('L');
        bytes.write(2); // pl
        bytes.write(0); // ph
        bytes.write(48); //m
        bytes.write(50); //fn

        return bytes.toByteArray();
    }
    
}
