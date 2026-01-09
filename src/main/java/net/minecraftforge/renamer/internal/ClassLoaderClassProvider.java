/*
 * Copyright (c) Forge Development LLC
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package net.minecraftforge.renamer.internal;

import org.jetbrains.annotations.Nullable;

import net.minecraftforge.renamer.api.ClassProvider;

import java.io.IOException;
import java.util.Optional;

public class ClassLoaderClassProvider implements ClassProvider {
    private final ClassLoader classLoader;

    public ClassLoaderClassProvider(@Nullable ClassLoader classLoader) {
        this.classLoader = classLoader == null ? this.getClass().getClassLoader() : classLoader;
    }

    @Override
    public Optional<? extends IClassInfo> getClass(String name) {
        try {
            Class<?> cls = Class.forName(name.replace('/', '.'), false, this.classLoader);
            return Optional.of(new ClassProviderImpl.ClassInfo(cls));
        } catch (ClassNotFoundException | NoClassDefFoundError ex) {
            return Optional.empty();
        }
    }

    @Override
    public void close() throws IOException {}
}
