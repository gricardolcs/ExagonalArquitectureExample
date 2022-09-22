package com.jalasoft.bootcamp.becommon.infrastructure.fileparser;

import java.util.Map;

public class Record
{
    private final Map<String, String> values;

    public Record(final Map<String, String> values)
    {
        this.values = values;
    }

    public Map<String, String> getValues()
    {
        return this.values;
    }
}
