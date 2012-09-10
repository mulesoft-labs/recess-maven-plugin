/**
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
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
