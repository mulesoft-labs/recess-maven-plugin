/**
 * Recess Maven Plugin
 * Copyright 2010-2011 (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mule.tools.recess;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.project.MavenProject;
import org.sonatype.plexus.build.incremental.BuildContext;

public abstract class AbstractJavascriptMojo extends AbstractMojo {

    /**
     *  @component
     */
    protected BuildContext buildContext;

    /**
     * Project being built.
     *
     * @parameter expression="${project}"
     * @required
     */
    private MavenProject project;

    /**
     * Where the .js files will be located for running.
     *
     * @parameter expression="${project.build.directory}/rhinodo"
     * @required
     */
    private String javascriptFilesDirectory;

    public String getJavascriptFilesDirectory() {
        return javascriptFilesDirectory;
    }

    public MavenProject getProject() {
        return project;
    }
}
