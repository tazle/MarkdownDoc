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
 *         2012-11-16: Created!
 *         
 */
package se.natusoft.doc.markdown.parser.markdown.model

import groovy.transform.CompileStatic
import se.natusoft.doc.markdown.model.DocItem
import se.natusoft.doc.markdown.model.Link

/**
 * This extends Link but provides internal markdown parsing of received data.
 */
@CompileStatic
class MDLink extends Link {

    //
    // Private Members
    //

    /** Keeps track of wich part of the link is being received. */
    private int part = 0

    //
    // Methods
    //

    /**
     * Provides the left shift operator.
     *
     * @param object The object to left shift in.
     */
    @Override
    public DocItem leftShift(Object object) {
        char c = (char)object

        if (part == 0 && c == '(') {
            part = 1
        }
        else if (part == 1 && c == ' ') {
            part = 2
        }
        else {
            if (part == 0) {
                addItem(c.toString())
            }
            else if (part == 1) {
                if (this.url == null) this.url = ""
                this.url = this.url + c
            }
            else if (part == 2) {
                if (this.title == null) this.title = ""
                this.title = this.title + c
            }
        }

        return this
    }
}
