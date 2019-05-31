package org.jescpos;

import org.escPosConst.Enum.*;

public class Stylizable {
    private Justification justification;
    private FontName fontName;
    private Underline underline;
    private FontSize fontWidth;
    private FontSize fontHeight;
    private ColorMode colorMode;
    private boolean Bold;
    private int lineSpacing;
    private boolean defaultLineSpacing;

    public Stylizable(Stylizable other){
        setJustification(other.getJustification());
        setFontName(other.getFontName());
        setUnderline(other.getUnderline());
        setFontSize(other.getFontWidth(), other.getFontHeight());
        setColorMode(other.getColorMode());
        setBold(other.isBold());
        setLineSpacing(other.getLineSpacing());
        setDefaultLineSpacing(other.isDefaultLineSpacing());
    }

    public Stylizable(){

    }

    private void defaultStylizable(){
        // reset code
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
        return Bold;
    }

    public void setBold(boolean bold) {
        Bold = bold;
    }

    public int getLineSpacing() {
        return lineSpacing;
    }

    public void setLineSpacing(int lineSpacing) {
        this.lineSpacing = lineSpacing;
    }

    public boolean isDefaultLineSpacing() {
        return defaultLineSpacing;
    }

    public void setDefaultLineSpacing(boolean defaultLineSpacing) {
        this.defaultLineSpacing = defaultLineSpacing;
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
