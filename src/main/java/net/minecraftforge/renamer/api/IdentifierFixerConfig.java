/*
 * Copyright (c) Forge Development LLC
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package net.minecraftforge.renamer.api;

/**
 * Identifier transformation strategy for {@link Transformer#identifierFixerFactory(IdentifierFixerConfig)}.
 */
public enum IdentifierFixerConfig {
    /**
     * Checks all Local variables if they are valid java identifiers.
     */
    ALL,
    /**
     * Only replaces snowman character used by Minecraft.
     */
    SNOWMEN;
}
