/*
 * Copyright (c) Forge Development LLC
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package net.minecraftforge.renamer.gradle;

import org.codehaus.groovy.runtime.StringGroovyMethods;
import org.gradle.api.Action;
import org.gradle.api.artifacts.Dependency;
import org.gradle.api.file.FileCollection;
import org.gradle.api.provider.Provider;
import org.gradle.api.provider.ProviderConvertible;
import org.gradle.api.tasks.TaskProvider;
import org.gradle.api.tasks.bundling.AbstractArchiveTask;

/// The extension interface for the Renamer Gradle plugin.
public interface RenamerExtension {
    /// The name for this extension when added to [projects][org.gradle.api.Project].
    String NAME = "renamer";

    default void mappings(String channel, String version) {
        mappings("net.minecraft:mappings_" + channel + ':' + version + "@tsrg.gz");
    }

    void mappings(String artifact);

    void mappings(Dependency dependency);

    void mappings(Provider<? extends Dependency> dependency);

    default void mappings(ProviderConvertible<? extends Dependency> dependency) {
        this.mappings(dependency.asProvider());
    }

    void setMappings(FileCollection files);

    default void classes(AbstractArchiveTask input) {
        this.classes(input, it -> it.from(input));
    }

    default void classes(AbstractArchiveTask input, Action<? super RenameJar> action) {
        this.classes("rename" + StringGroovyMethods.capitalize(input.getName()), input, action);
    }

    default void classes(TaskProvider<? extends AbstractArchiveTask> input) {
        this.classes(input, it -> it.from(input));
    }

    default void classes(TaskProvider<? extends AbstractArchiveTask> input, Action<? super RenameJar> action) {
        this.classes("rename" + StringGroovyMethods.capitalize(input.getName()), input, action);
    }

    default void classes(String name, AbstractArchiveTask input) {
        this.classes(input, it -> it.from(input));
    }

    void classes(String name, AbstractArchiveTask input, Action<? super RenameJar> action);

    default void classes(String name, TaskProvider<? extends AbstractArchiveTask> input) {
        this.classes(input, it -> it.from(input));
    }

    void classes(String name, TaskProvider<? extends AbstractArchiveTask> input, Action<? super RenameJar> action);
}
