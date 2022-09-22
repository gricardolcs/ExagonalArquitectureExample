package com.jalasoft.bootcamp.becommon.infrastructure.fileparser;

import java.io.InputStreamReader;
import java.util.List;

public interface FileReader
{
    List<Record> read(final InputStreamReader file);
}
