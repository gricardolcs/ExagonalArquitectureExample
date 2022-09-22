package com.jalasoft.bootcamp.becommon.infrastructure.fileparser;

import java.io.InputStreamReader;
import java.util.List;

/**
 * @param <T> Indicates the Type in which the
 *            {@link Record Record} type will be converted,
 *            Typically it will be an Entity class
 */
public interface FileParser<T>
{
    List<T> parse(InputStreamReader file);
}
