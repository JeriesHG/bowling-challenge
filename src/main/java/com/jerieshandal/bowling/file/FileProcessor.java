package com.jerieshandal.bowling.file;

import com.jerieshandal.bowling.pojo.Roll;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FileProcessor {

    default Map<String, List<Roll>> process(String path) throws IOException {
        return process(new File(path));
    }

    Map<String, List<Roll>> process(File path) throws IOException;
}
