/* 
 * 
 * PROJECT
 *     Name
 *         MarkdownDoc Library
 *     
 *     Code Version
 *         1.3.8
 *     
 *     Description
 *         Parses markdown and generates HTML and PDF.
 *         
 * COPYRIGHTS
 *     Copyright (C) 2012 by Natusoft AB All rights reserved.
 *     
 * LICENSE
 *     Apache 2.0 (Open Source)
 *     
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *     
 *       http://www.apache.org/licenses/LICENSE-2.0
 *     
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *     
 * AUTHORS
 *     Tommy Svensson (tommy@natusoft.se)
 *         Changes:
 *         2012-11-21: Created!
 *         
 */
package se.natusoft.doc.markdown.util;

import groovy.transform.CompileStatic

/**
 * This handles a comma separated set of SourcePaths.
 */
@CompileStatic
public class SourcePaths {
    //
    // Private Members
    //

    /** All the source paths. */
    List<SourcePath> sourcePaths = new LinkedList<SourcePath>()

    //
    // Constructors
    //

    /**
     * Creates a new SourcePaths.
     *
     * @param sourcePaths The comma separated path specifications to parse.
     */
    public SourcePaths(String sourcePaths) {
        StringTokenizer pathTokenizer = new StringTokenizer(sourcePaths, ",")
        while (pathTokenizer.hasMoreTokens()) {
            this.sourcePaths.add(new SourcePath(pathTokenizer.nextToken().trim()))
        }
    }

    /**
     * Creates a new SourcePaths.
     *
     * @param projRoot The root dir that all source paths are relative to.
     * @param sourcePaths The comma separated path specifications to parse.
     */
    public SourcePaths(File projRoot, String sourcePaths) {
        StringTokenizer pathTokenizer = new StringTokenizer(sourcePaths, ",")
        while (pathTokenizer.hasMoreTokens()) {
            String path = pathTokenizer.nextToken().trim()
            if (path.startsWith(projRoot.getAbsolutePath())) {
                int projRootLength = projRoot.getAbsolutePath().length()
                path = path.substring(projRootLength)
            }
            this.sourcePaths.add(new SourcePath(projRoot, path))
        }
    }

    //
    // Methods
    //

    /**
     * Returns all specified source paths.
     */
    public List<SourcePath> getSourcePaths() {
        return this.sourcePaths;
    }

    /**
     * Returns all files from all source paths (in the order they were specified).
     */
    public List<File> getSourceFiles() {
        List<File> all = new LinkedList<File>()
        for (SourcePath sourcePath : this.sourcePaths) {
            for (File file : sourcePath.getSourceFiles()) {
                all.add(file)
            }
        }

        return all;
    }

    /**
     * @return true if there are source files.
     */
    public boolean hasSourceFiles() {
        return !getSourceFiles().isEmpty()
    }
}
