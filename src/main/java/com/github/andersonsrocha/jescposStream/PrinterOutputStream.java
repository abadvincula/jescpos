/*
 * Copyright 2019 Anderson Silva
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the main file or LICENSE file.
 */
package com.github.andersonsrocha.jescposStream;

import javax.print.*;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashAttributeSet;
import javax.swing.JOptionPane;

import com.github.andersonsrocha.jescposViews.ExceptionDialog;

import java.io.*;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Anderson Silva
 */
public class PrinterOutputStream extends PipedOutputStream {
    private final PipedInputStream pipedInputStream;

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

        Thread thread = new Thread(runnable);
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

            showException(null, new NullPointerException("Service is not found!"));
        } catch (Exception e) {
            showException(null, e);
        }
        return null;
    }

    public static PrintService getDefaultPrintService() {
        PrintService service = PrintServiceLookup.lookupDefaultPrintService();
        if (service == null) {
            showException(null, new IllegalArgumentException("Default Print Service is not found!"));
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

        if (JOptionPane.showOptionDialog(null, message, "Jescpos ERROR!", JOptionPane.YES_NO_OPTION,
                JOptionPane.ERROR_MESSAGE, null, choices, defaultChoice) == 1) {
            new ExceptionDialog(parent, true, e).setVisible(true);
        }
    }
}
