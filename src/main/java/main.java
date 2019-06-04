
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.print.PrintService;

import org.jescposConst.JescposEnum;
import org.jescpos.Jescpos;
import org.jescpos.JescposConf;
import org.jescposImage.BitonalThreshold;
import org.jescposImage.JescposImage;
import org.jescposImage.RasterBitImageWrapper;
import org.jescposStream.PrinterOutputStream;

/*
 * The MIT License
 *
 * Copyright 2019 development.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

/**
 * @author development
 */
public class main {
    public static void main(String[] args) throws IOException {
//        PrintService printService = PrinterOutputStream.getServicePrinterByName("Argox");
        PrintService printService = PrinterOutputStream.getDefaultPrintService();
        PrinterOutputStream printerOutputStream = new PrinterOutputStream(printService);
        Jescpos jescpos = new Jescpos(printerOutputStream);
        JescposConf jescposConf = new JescposConf();
        jescposConf.setBold(true);
        jescposConf.setJustification(JescposEnum.Justification.CENTER);
        jescposConf.setFontSize(JescposEnum.FontSize.SIZE_2, JescposEnum.FontSize.SIZE_2);
        jescpos.initialize();
        jescpos.printf(jescposConf, "HYPERMENO");
        jescpos.printf(jescposConf, "CUPOM NAO FISCAL");

//        RasterBitImageWrapper imageWrapper = new RasterBitImageWrapper();
//        BufferedImage bufferedImage = ImageIO.read(new File("/home/development/Documents/image.jpg"));
//        JescposImage escposImage = new JescposImage(bufferedImage, new BitonalThreshold());
//        // print smile image...
//        jescpos.image(imageWrapper, escposImage);

        jescpos.cut(JescposEnum.CutMode.PART);
        jescpos.close();
    }
}
