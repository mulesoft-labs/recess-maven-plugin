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

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.codehaus.plexus.util.Scanner;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Goal that offers Recess support in Maven builds.
 *
 * @goal recess
 * @phase generate-sources
 */
public class RecessMojo extends AbstractJavascriptMojo {
    /**
     * List of files to include. Specified as fileset patterns which are relative to the source directory. Default value is: { "**\/*.less",
     * "**\/*.css" }
     *
     * @parameter
     */
    protected String[] includes = new String[] { "**/*.less", "**/*.css"  };

    /**
     * List of files to exclude. Specified as fileset patterns which are relative to the source directory.
     *
     * @parameter
     */
    protected String[] excludes = new String[] {};

    /**
     * The source directory containing the LESS sources.
     *
     * @parameter expression="${recess.sourceDirectory}" default-value="${project.basedir}/src/main/less"
     */
    protected File sourceDirectory;

    /**
     * Where the resulting css files must be saved.
     *
     * @parameter expression="${recess.outputDirectory}" default-value="${project.build.directory}/generated-sources/recess"
     */
    private String outputDirectory;

    /**
     * Where the resulting css files must be saved.
     *
     * @parameter expression="${recess.outputFile}"
     */
    private String outputFile;

    /**
     * Removes color from output (useful when logging).
     *
     * @parameter @parameter expression="${recess.stripColors}" default-value="false"
     */
    private boolean stripColors;

    /**
     * Doesn't complain about using IDs in your stylesheets.
     *
     * @parameter expression="${recess.noIDs}" default-value="true"
     * @required
     */
    private boolean noIDs;

    /**
     * Doesn't complain about styling .js- prefixed classnames.
     *
     * @parameter expression="${recess.noJSPrefix}" default-value="true"
     * @required
     */
    private boolean noJSPrefix;

    /**
     * Doesn't complain about overqualified selectors. (ie: div#foo.bar)
     *
     * @parameter expression="${recess.noOverqualifying}" default-value="true"
     * @required
     */
    private boolean noOverqualifying;

    /**
     * Doesn't complain about using underscores in your class names.
     *
     * @parameter expression="${recess.noUnderscores}" default-value="true"
     * @required
     */
    private boolean noUnderscores;

    /**
     * Doesn't complain about using the universal * selector.
     *
     * @parameter expression="${recess.noUniversalSelectors}" default-value="true"
     * @required
     */
    private boolean noUniversalSelectors;

    /**
     * Adds whitespace prefix to line up vender prefixed properties.
     *
     * @parameter expression="${recess.prefixWhitespace}" default-value="true"
     * @required
     */
    private boolean prefixWhitespace;

    /**
     * Doesn't looking into your property ordering.
     *
     * @parameter expression="${recess.strictPropertyOrder}" default-value="true"
     * @required
     */
    private boolean strictPropertyOrder;

    /**
     * Doesn't complain if you add units to values of 0.
     *
     * @parameter expression="${recess.zeroUnits}" default-value="true"
     * @required
     */
    private boolean zeroUnits;

    /**
     * Whether to compress or not the result.
     *
     * @parameter expression="${recess.compress}" default-value="false"
     * @required
     */
    private boolean compress;

    private Object[] getIncludedFiles() {
        Scanner scanner = buildContext.newScanner(sourceDirectory, true);
        scanner.setIncludes(includes);
        scanner.setExcludes(excludes);
        scanner.scan();
        String[] includedFiles = scanner.getIncludedFiles();
        for (int i = 0; i < includedFiles.length; i++) {
            includedFiles[i] = scanner.getBasedir() + File.separator + includedFiles[i];
        }

        Object [] result = new Object[includedFiles.length];
        System.arraycopy(includedFiles, 0, result, 0, includedFiles.length);
        return result;
    }

    Map<String,Object> getRecessConfig() {
        Map<String, Object> mapConfig = new HashMap<String, Object>();

        mapConfig.put("compile", "true");
        mapConfig.put("compress", compress);
        mapConfig.put("noIDs", noIDs);
        mapConfig.put("noJSPrefix", noJSPrefix);
        mapConfig.put("noOverqualifying", noOverqualifying);
        mapConfig.put("noUnderscores", noUnderscores);
        mapConfig.put("noUniversalSelectors",noUniversalSelectors);
        mapConfig.put("prefixWhitespace",prefixWhitespace);
        mapConfig.put("strictPropertyOrder",strictPropertyOrder);
        mapConfig.put("stripColors",stripColors);
        mapConfig.put("zeroUnits",zeroUnits);

        return mapConfig;
    }

    public void execute() throws MojoExecutionException {

        Log log = getLog();

        if ( !sourceDirectory.exists() || !sourceDirectory.isDirectory() ) {
            throw new MojoExecutionException("Source directory does not exist.");
        }

        Object[] files = getIncludedFiles();

        new File(getJavascriptFilesDirectory()).mkdirs();

        File outputDirectoryFile = new File(outputDirectory);



        if ( outputFile != null ) {
            log.debug("outputFile parameter found. Using it to store the result.");
            new Recess(files, getRecessConfig(), outputFile, true, getJavascriptFilesDirectory(), outputDirectory).run();

        } else if ( outputDirectory != null  ) {

            log.debug("outputFile parameter not found. Using outputDirectory and changing the extension " +
                    "of the contained files to css.");

            new Recess(files, getRecessConfig(), null, false, getJavascriptFilesDirectory(), outputDirectory).run();

        } else {
            throw new MojoExecutionException("Error: Either outputFile or outputDirectory should be set.");
        }
    }
}
