package org.escPosConst;

public class Enum {
    private static  final int NUL = 0;
    private static  final int LF = 10;
    private static  final int ESC = 27;
    private static  final int GS = 29;

    public enum Justification {
        LEFT(48),
        RIGHT(50),
        CENTER(49);

        public int value;

        public int getValue() {
            return value;
        }

        private Justification(int value) {
            this.value = value;
        }
    }

    public enum CutMode {
        FULL(48),
        PART(49);

        public int value;

        private CutMode(int value) {
            this.value = value;
        }
    }

    public enum FontName {
        DEFAULT(48),
        FONT_B(49),
        FONT_C(50);

        public int value;

        private FontName(int value) {
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

        private FontSize(int value) {
            this.value = value;
        }
    }

    public enum Underline {
        DEFAULT(48),
        ONEDOTTHICK(49),
        TWODOTTHICK(50);

        public int value;

        private Underline(int value) {
            this.value = value;
        }
    }

    public enum ColorMode {
        DEAFULT(0),
        WHITEONBLACK(1);
        public int value;

        private ColorMode(int value) {
            this.value = value;
        }
    }
}
