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
package org.jescpos;

import org.JescposService;
import org.escPosConst.Enum;
import org.stream.PrinterOutputStream;

import java.io.*;

/**
 * @author Anderson Silva
 */
public class Jescpos implements Enum, Closeable, Flushable, JescposService {
    private final OutputStream outputStream;
    private final Stylizable stylizable;

    public Jescpos(OutputStream outputStream) {
        this.stylizable = new Stylizable();
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
     * @return {@link Jescpos}
     */
    @Override
    public Jescpos text(String text) throws IOException {
        return bytes(text.getBytes());
    }

    @Override
    public void close() throws IOException {
        this.outputStream.close();
    }

    @Override
    public void flush() throws IOException {
        outputStream.flush();
    }

    ////////////////////////////////////////////////////////

    @Override
    public Jescpos printf(String text) throws IOException {
        text(text);
        feed(1);
        return this;
    }

    @Override
    public Jescpos code(int b) throws IOException {
        this.outputStream.write(b);
        return this;
    }

    @Override
    public Jescpos code(byte[] bytes, int off, int len) throws IOException {
        this.outputStream.write(bytes, off, len);
        return this;
    }

    @Override
    public Jescpos feed(int nlines) throws IOException {
        if(nlines < 1 || nlines > 255){
            PrinterOutputStream.showException(null, new RuntimeException("(nlines) lines are not supported!"));
        }

        code(ESC);
        code('d');
        code(nlines);
        return this;
    }

    @Override
    public Jescpos printf(Stylizable stylizable, String text) {
        return null;
    }

    @Override
    public Jescpos code(Stylizable stylizable, int b) {
        return null;
    }

    @Override
    public Jescpos feed(Stylizable stylizable, int n) {
        return null;
    }
}
