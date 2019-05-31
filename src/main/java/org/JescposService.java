package org;

import org.jescpos.Jescpos;
import org.jescpos.Stylizable;

import java.io.IOException;

public interface JescposService {
    Jescpos text(String text) throws IOException;

    Jescpos bytes(byte[] bytes) throws IOException;

    Jescpos printf(String text) throws IOException;

    Jescpos code(int b) throws IOException;

    Jescpos code(byte[] bytes, int off, int len) throws IOException;

    Jescpos feed(int n) throws IOException;

    Jescpos printf(Stylizable stylizable, String text) throws IOException;

    Jescpos code(Stylizable stylizable, int b) throws IOException;

    Jescpos feed(Stylizable stylizable, int n);
}
