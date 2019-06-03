package org;

import org.escPosConst.Enum;
import org.jescpos.Jescpos;
import org.jescpos.JescposConf;

import java.io.IOException;

public interface JescposService {
    Jescpos text(String text) throws IOException;

    Jescpos bytes(byte[] bytes) throws IOException;

    Jescpos initialize() throws IOException;

    Jescpos printf(String text) throws IOException;

    Jescpos code(int b) throws IOException;

    Jescpos code(byte[] bytes, int off, int len) throws IOException;

    Jescpos feed(int n) throws IOException;

    Jescpos printf(JescposConf jescposConf, String text) throws IOException;

    Jescpos cut(Enum.CutMode cut) throws IOException;
}
