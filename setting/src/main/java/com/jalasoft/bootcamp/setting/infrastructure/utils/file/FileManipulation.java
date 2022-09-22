package com.jalasoft.bootcamp.setting.infrastructure.utils.file;

import java.io.IOException;

public interface FileManipulation<T>
{
    T getFileObject() throws IOException;

    void updateFile(T data) throws IOException;
}
