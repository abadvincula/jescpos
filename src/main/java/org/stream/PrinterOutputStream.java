/*
 * The MIT License
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
package org.stream;

import org.escPosConst.Enum;

import javax.print.*;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashAttributeSet;
import javax.swing.JOptionPane;

import org.views.ExceptionDialog;

import java.io.*;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Anderson Silva
 */
public class PrinterOutputStream extends PipedOutputStream {
    private final PipedInputStream pipedInputStream;
    private final Thread thread;

    public PrinterOutputStream(PrintService printService) throws IOException {
        pipedInputStream = new PipedInputStream();
        super.connect(pipedInputStream);

        Runnable runnable = () -> {
            try {
                DocFlavor df = DocFlavor.INPUT_STREAM.AUTOSENSE;
                DocPrintJob job = printService.createPrintJob();
                Doc doc = new SimpleDoc(pipedInputStream, df, null);
                job.print(doc, null);
            } catch (PrintException e) {
                showException(null, e);
            }
        };

        thread = new Thread(runnable);
        thread.start();
    }

    public static PrintService getServicePrinterByName(String printer) {
        final String print = printer.toLowerCase();
        try {
            AttributeSet attributeSet = new HashAttributeSet();
            DocFlavor df = DocFlavor.INPUT_STREAM.AUTOSENSE;
            Stream<PrintService> printServiceStream = Arrays.stream(PrintServiceLookup.lookupPrintServices(df, attributeSet));
            Optional<PrintService> optional = printServiceStream.filter(printService -> printService.getName().toLowerCase().contains(print)).findFirst();

            if (optional.isPresent()) {
                return optional.get();
            }

        } catch (Exception e) {
            showException(null, e);
        }
        return null;
    }

    public static PrintService getDefaultPrintService() {
        PrintService service = PrintServiceLookup.lookupDefaultPrintService();
        if (service == null) {
            throw new IllegalArgumentException("Default Print Service is not found");
        }
        return service;
    }

    /**
     * Show an exception dialog with two buttons, okay (closes the dialog) and
     * detail (shows another dialog detailing the exception).
     *
     * @param parent the calling frame of this dialog
     * @param e      the exception which this dialog is showing
     */
    public static void showException(java.awt.Frame parent, Exception e) {
        showException(parent, e.getMessage(), e);
    }

    public static void showException(java.awt.Frame parent, String message, Exception e) {
        Object[] choices = {"Ok", "Detalhes >>>"};
        Object defaultChoice = choices[1];

        if (JOptionPane.showOptionDialog(null, message, "JEscPos Error!", JOptionPane.YES_NO_OPTION,
                JOptionPane.ERROR_MESSAGE, null, choices, defaultChoice) == 1) {
            new ExceptionDialog(parent, true, e).setVisible(true);
        }
    }
}
