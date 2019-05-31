package org.escPosConst;

public interface Enum {
    public static final int NUL = 0;
    public static final int LF = 10;
    public static final int ESC = 27;
    public static final int GS = 29;

    enum Justification {
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

    enum CutMode {
        FULL(48),
        PART(49);

        public int value;

        private CutMode(int value) {
            this.value = value;
        }
    }

    enum FontName {
        DEFAULT(48),
        FONT_B(49),
        FONT_C(50);

        public int value;

        private FontName(int value) {
            this.value = value;
        }
    }

    enum FontSize {
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
}
