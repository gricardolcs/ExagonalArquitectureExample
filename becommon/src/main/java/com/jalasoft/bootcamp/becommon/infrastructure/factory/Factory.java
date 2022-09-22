package com.jalasoft.bootcamp.becommon.infrastructure.factory;

public interface Factory<T, C>
{
    T create(final C context);
}
