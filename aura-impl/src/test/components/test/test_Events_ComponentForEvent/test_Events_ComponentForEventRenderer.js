/*
 * Copyright (C) 2013 salesforce.com, inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
({
    render : function(component){
        var ret = document.createElement('div');
        ret.id = component.getGlobalId();
        var label = component.getAttributes().getValue('label').getValue();
        ret.innerHTML = label;
        var classIdentifier = component.getAttributes().getValue('classIdentifier').getValue();
        ret.className = classIdentifier;
        aura.util.on(ret,"click", function(ev) {
            var e = component.getEvent("testPress");
            e.fire();
        }, true);


        aura.util.on(ret,"mouseover", function(ev) {
            var e = component.getEvent("testMouseOver");
            e.fire();
        }, true);

        return [ret];
    },

    rerender: function(component) {
        var div = component.getElement();
        div.innerHTML = component.getAttributes().getValue('label').getValue();
    }
})
