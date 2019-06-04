/*
 * Copyright 2019 Anderson Silva
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the main file or LICENSE file.
 */
package org.jescposConst;

public class JescposEnum {
    protected static final int NUL = 0;
    protected static final int LF = 10;
    protected static final int ESC = 27;
    protected static final int GS = 29;

    public enum Justification {
        LEFT(48),
        RIGHT(50),
        CENTER(49);

        public int value;

        Justification(int value) {
            this.value = value;
        }
    }

    public enum CutMode {
        FULL(48),
        PART(49);

        public int value;

        CutMode(int value) {
            this.value = value;
        }
    }

    public enum FontName {
        DEFAULT(48),
        FONT_B(49),
        FONT_C(50);

        public int value;

        FontName(int value) {
            this.value = value;
        }
    }

    public enum FontSize {
        DEFAULT(0),
        SIZE_2(1),
        SIZE_3(2),
        SIZE_4(3),
        SIZE_5(4),
        SIZE_6(5),
        SIZE_7(6),
        SIZE_8(7);

        public int value;

        FontSize(int value) {
            this.value = value;
        }
    }

    public enum Underline {
        DEFAULT(48),
        ONEDOTTHICK(49),
        TWODOTTHICK(50);

        public int value;

        Underline(int value) {
            this.value = value;
        }
    }

    public enum ColorMode {
        DEAFULT(0),
        WHITEONBLACK(1);

        public int value;

        ColorMode(int value) {
            this.value = value;
        }
    }

    public enum BitImageMode{
        _8DOTSINGLEDENSITY(0,8),
        _8DOTDOUBLEDENSITY(1,8),
        _24DOTSINGLEDENSITY(32,24),
        _24DEFAULT(33,24);

        public int value;
        public int bitsForVerticalData;

        BitImageMode(int value, int bitsPerSlice){
            this.value = value;
            this.bitsForVerticalData = bitsPerSlice;
        }
    }

    public enum RasterBitImageMode{
        DEFAULT(0),
        DOUBLEwIDTH(1),
        DOUBLEHEIGHT(2),
        QUADRUPLE(3);

        public int value;

        RasterBitImageMode(int value){
            this.value = value;
        }
    }

    public enum GraphicsImageBxBy{
        DEFAULT(1,1),
        DOUBLEWIDTH(2,1),
        DoubleHEIGHT(1,2),
        QUADRUPLE(2, 2);

        public int bx;
        public int by;

        private GraphicsImageBxBy(int bx, int by){
            this.bx = bx;
            this.by = by;
        }
    }
}
