package org.jescpos;

import org.escPosConst.Enum;

import java.io.ByteArrayOutputStream;

public class JescposConf extends Enum {
    private Justification justification;
    private FontName fontName;
    private Underline underline;
    private FontSize fontWidth;
    private FontSize fontHeight;
    private ColorMode colorMode;
    private boolean bold;
    private int lineSpacing;

    public JescposConf(JescposConf other) {
        justification = other.getJustification();
        fontName = other.getFontName();
        underline = other.getUnderline();
        setFontSize(other.getFontWidth(), other.getFontHeight());
        colorMode = other.getColorMode();
        bold = other.isBold();
        lineSpacing = other.getLineSpacing();
    }

    public JescposConf() {
        defaultStylizable();
    }

    private void defaultStylizable() {
        justification = Justification.LEFT;
        fontName = FontName.DEFAULT;
        underline = Underline.DEFAULT;
        fontWidth = FontSize.DEFAULT;
        fontHeight = FontSize.DEFAULT;
        colorMode = ColorMode.DEAFULT;
        bold = false;
        lineSpacing = 0;
    }

    public byte[] getConfigBytes() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        //font
        baos.write(ESC);
        baos.write('M');
        baos.write(fontName.value);

        //font size
        baos.write(GS);
        baos.write('!');
        baos.write(fontWidth.value << 4 | fontHeight.value);

        //bold
        baos.write(ESC);
        baos.write('E');
        baos.write(bold ? 1 : 0);

        //justification
        baos.write(ESC);
        baos.write('a');
        baos.write(justification.value);

        //underline
        baos.write(ESC);
        baos.write('-');
        baos.write(underline.value);

        //color mode
        baos.write(GS);
        baos.write('B');
        baos.write(colorMode.value);

        //line spacing
        baos.write(ESC);
        baos.write(lineSpacing != 0 ? '2' : '3');
        if (lineSpacing != 0) {
            baos.write(lineSpacing);
        }

        return baos.toByteArray();
    }

    public Justification getJustification() {
        return justification;
    }

    public void setJustification(Justification justification) {
        this.justification = justification;
    }

    public FontName getFontName() {
        return fontName;
    }

    public void setFontName(FontName fontName) {
        this.fontName = fontName;
    }

    public Underline getUnderline() {
        return underline;
    }

    public void setUnderline(Underline underline) {
        this.underline = underline;
    }

    public FontSize getFontSize() {
        return fontWidth;
    }

    public void setFontSize(FontSize fontWidth, FontSize fontHeight) {
        this.fontWidth = fontWidth;
        this.fontHeight = fontHeight;
    }

    public ColorMode getColorMode() {
        return colorMode;
    }

    public void setColorMode(ColorMode colorMode) {
        this.colorMode = colorMode;
    }

    public boolean isBold() {
        return bold;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public int getLineSpacing() {
        return lineSpacing;
    }

    public void setLineSpacing(int lineSpacing) {
        this.lineSpacing = lineSpacing;
    }

    public FontSize getFontWidth() {
        return fontWidth;
    }

    public void setFontWidth(FontSize fontWidth) {
        this.fontWidth = fontWidth;
    }

    public FontSize getFontHeight() {
        return fontHeight;
    }

    public void setFontHeight(FontSize fontHeight) {
        this.fontHeight = fontHeight;
    }
}
