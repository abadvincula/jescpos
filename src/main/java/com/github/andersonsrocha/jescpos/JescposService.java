/*
 * Copyright 2019 Anderson Silva
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the main file or LICENSE file.
 */
package com.github.andersonsrocha.jescpos;

import com.github.andersonsrocha.jescposConst.JescposEnum;
import com.github.andersonsrocha.jescposImage.JescposImage;
import com.github.andersonsrocha.jescposImage.RasterBitImageWrapper;

import java.io.IOException;

public interface JescposService {
    Jescpos text(String text) throws IOException;

    Jescpos bytes(byte[] bytes) throws IOException;

    Jescpos initialize() throws IOException;

    Jescpos print(String text) throws IOException;

    Jescpos printf(String text) throws IOException;

    Jescpos code(int b) throws IOException;

    Jescpos code(byte[] bytes, int off, int len) throws IOException;

    Jescpos feed(int n) throws IOException;

    Jescpos print(JescposConf jescposConf, String text) throws IOException;

    Jescpos printf(JescposConf jescposConf, String text) throws IOException;

    Jescpos cut(JescposEnum.CutMode cut) throws IOException;

    Jescpos image(RasterBitImageWrapper imageWrapper, JescposImage image) throws IOException;
}
