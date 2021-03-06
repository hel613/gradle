/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.configuration;

import groovy.lang.Script;
import org.gradle.api.internal.plugins.PluginManagerInternal;
import org.gradle.api.internal.project.ProjectInternal;
import org.gradle.api.internal.project.ProjectScript;
import org.gradle.groovy.scripts.BasicScript;
import org.gradle.groovy.scripts.DefaultScript;

public class ProjectScriptTarget implements ScriptTarget {
    private final ProjectInternal target;
    private final boolean topLevelScript;

    public ProjectScriptTarget(ProjectInternal target, boolean topLevelScript) {
        this.target = target;
        this.topLevelScript = topLevelScript;
    }

    @Override
    public PluginManagerInternal getPluginManager() {
        return target.getPluginManager();
    }

    @Override
    public String getClasspathBlockName() {
        return "buildscript";
    }

    @Override
    public boolean getSupportsPluginsBlock() {
        return topLevelScript;
    }

    @Override
    public Class<? extends BasicScript> getScriptClass() {
        return topLevelScript ? ProjectScript.class : DefaultScript.class;
    }

    @Override
    public void attachScript(Script script) {
        if (topLevelScript) {
            target.setScript(script);
        }
    }

    @Override
    public void addConfiguration(Runnable runnable, boolean deferrable) {
        if (deferrable) {
            target.addDeferredConfiguration(runnable);
        } else {
            runnable.run();
        }
    }
}
