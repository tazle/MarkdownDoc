/* 
 * 
 * PROJECT
 *     Name
 *         MarkdownDocEditor
 *     
 *     Code Version
 *         1.3.8
 *     
 *     Description
 *         An editor that supports editing markdown with formatting preview.
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
 *         2014-10-12: Created!
 *         
 */
package se.natusoft.doc.markdowndoc.editor.functions.export

import groovy.transform.CompileStatic

@CompileStatic
public class ExportFileValue extends ExportDataValue {

    private String whatFile

    // This is provided later in constructor of subclass since it is provided to the constructor.
    DelayedServiceData delayedServiceData

    public ExportFileValue(String labelText, String whatFile) {
        super(labelText)
        this.whatFile = whatFile
    }

    public String getValue() {
        if (whatFile == null || delayedServiceData == null) {
            throw new IllegalStateException("'whatFile' and 'gui' properties must have been provided before this call" +
                    " can be made!")
        }
        if (valueComp == null) {
            valueComp = new FileSelector(this.whatFile, delayedServiceData)
        }
        return ((FileSelector)valueComp).getFile()
    }

    public void setValue(String value) {
        ((FileSelector)valueComp).setFile(value)
    }
}
