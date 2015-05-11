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

package org.gradle.tooling.events.internal.build;

import org.gradle.api.Incubating;
import org.gradle.tooling.Failure;
import org.gradle.tooling.events.FailureResult;

import java.util.List;

/**
 * Describes how a build operation finished with failures.
 *
 * @since 2.5
 */
@Incubating
public interface BuildFailureResult extends BuildOperationResult, FailureResult {
    /**
     * Returns the exceptions that occurred while running the build, if any.
     *
     * @return the exceptions, empty if the build failed without any exceptions
     */
    @Override
    List<? extends Failure> getFailures();
}