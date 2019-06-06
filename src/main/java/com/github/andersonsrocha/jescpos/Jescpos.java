/*
 * The MIT License
 *
 * Copyright 2019 Anderson Silva.
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
package com.github.andersonsrocha.jescpos;

import com.github.andersonsrocha.jescposImage.JescposImage;
import com.github.andersonsrocha.jescposImage.RasterBitImageWrapper;
import com.github.andersonsrocha.jescposStream.PrinterOutputStream;
import com.github.andersonsrocha.jescposConst.JescposEnum;

import java.io.*;

/**
 * @author Anderson Silva
 */
public class Jescpos extends JescposEnum implements Closeable, Flushable, JescposService {
    private final OutputStream outputStream;
    private final JescposConf jescposConf;

    public Jescpos(OutputStream outputStream) {
        this.jescposConf = new JescposConf();
        this.outputStream = outputStream;
    }

    /**
     * Sends a raw byte array to the printer.
     *
     * @param bytes byte array
     * @return {@link Jescpos}
     */
    @Override
    public Jescpos bytes(byte[] bytes) throws IOException {
        this.outputStream.write(bytes);
        return this;
    }

    /**
     * Sends a string to the printer.
     *
     * @param text string
     * @return {@link #bytes(byte[])}
     */
    @Override
    public Jescpos text(String text) throws IOException {
        return bytes((" " + text).getBytes());
    }

    /**
     * Close output stream
     *
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        this.outputStream.close();
    }

    /**
     * Clear stream
     *
     * @throws IOException
     */
    @Override
    public void flush() throws IOException {
        this.outputStream.flush();
    }

    /* PRINTING COMMANDS */

    /**
     * Initialize printer
     *
     * @return {@link Jescpos}
     * @throws IOException
     */
    @Override
    public Jescpos initialize() throws IOException {
        code(ESC);
        code('@');
        return this;
    }

    /**
     * Sends a standard style string to the printer
     *
     * @param text (String) to print
     * @return {@link #print(JescposConf, String)}
     * @throws IOException
     */
    @Override
    public Jescpos print(String text) throws IOException {
        return print(jescposConf, text);
    }

    /**
     * Sends a standard style string to the printer and feed one line
     *
     * @param text (String) to print
     * @return {@link #printf(JescposConf, String)}
     * @throws IOException
     */
    @Override
    public Jescpos printf(String text) throws IOException {
        return printf(jescposConf, text);
    }

    /**
     * Sends one byte to print
     *
     * @param b (byte) to print
     * @return {@link Jescpos}
     * @throws IOException
     */
    @Override
    public Jescpos code(int b) throws IOException {
        this.outputStream.write(b);
        return this;
    }

    /**
     * Writes <code>len</code> bytes from the specified byte array
     * starting at offset <code>off</code> to this output stream.
     * The general contract for <code>write(bytes, off, len)</code> is that
     * some of the bytes in the array <code>bytes</code> are written to the
     * output stream in order; element <code>bytes[off]</code> is the first
     * byte written and <code>bytes[off+len-1]</code> is the last byte written
     * by this operation.
     * <p>
     * The <code>write</code> method of <code>OutputStream</code> calls
     * the write method of one argument on each of the bytes to be
     * written out. Subclasses are encouraged to override this method and
     * provide a more efficient implementation.
     * <p>
     * If <code>bytes</code> is <code>null</code>, a
     * <code>NullPointerException</code> is thrown.
     * <p>
     * If <code>off</code> is negative, or <code>len</code> is negative, or
     * <code>off+len</code> is greater than the length of the array
     * <code>bytes</code>, then an <tt>IndexOutOfBoundsException</tt> is thrown.
     *
     * @param bytes the data.
     * @param off   the start offset in the data.
     * @param len   the number of bytes to write.
     * @throws IOException if an I/O error occurs. In particular,
     *                     an <code>IOException</code> is thrown if the output
     *                     stream is closed.
     */
    @Override
    public Jescpos code(byte[] bytes, int off, int len) throws IOException {
        this.outputStream.write(bytes, off, len);
        return this;
    }

    /**
     * feeds <code>nlines</code> lines and prepares to write next.
     *
     * write on output: ESC => 27, 'd', <codie>nlines</codie>.
     *
     * @param nlines amount of lines to jump
     * @return {@link Jescpos}
     * @throws IOException
     */
    @Override
    public Jescpos feed(int nlines) throws IOException {
        if (nlines < 1 || nlines > 255) {
            PrinterOutputStream.showException(null, new RuntimeException("(nlines) lines are not supported!"));
        }

        code(ESC);
        code('d');
        code(nlines);
        return this;
    }

    /**
     * Writes a collection of bytes from the layout {@link #code(byte[], int, int)}.
     * Sends text to print {@link #text(String)}.
     *
     * @param jescposConf setting the print layout
     * @param text to print
     * @return {@link Jescpos}
     * @throws IOException
     */
    @Override
    public Jescpos print(JescposConf jescposConf, String text) throws IOException {
        code(jescposConf.getConfigBytes(), 0, jescposConf.getConfigBytes().length);
        text(text);
        return this;
    }

    @Override
    public Jescpos printf(JescposConf jescposConf, String text) throws IOException {
        code(jescposConf.getConfigBytes(), 0, jescposConf.getConfigBytes().length);
        text(text);
        feed(1);
        return this;
    }

    @Override
    public Jescpos cut(CutMode cut) throws IOException {
        feed(7);
        code(GS);
        code('V');
        code(cut.value);
        return this;
    }

    @Override
    public Jescpos image(RasterBitImageWrapper imageWrapper, JescposImage image) throws IOException {
        byte[] bytes = imageWrapper.getBytes(image);
        code(bytes, 0, bytes.length);
        return this;
    }

}
