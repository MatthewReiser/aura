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
package org.auraframework.impl.css.parser.omakase;

import org.auraframework.Aura;
import org.auraframework.http.AuraBaseServlet;
import org.auraframework.system.AuraContext.Mode;

import com.salesforce.omakase.ast.declaration.UrlFunctionValue;
import com.salesforce.omakase.broadcast.annotation.Rework;
import com.salesforce.omakase.plugin.Plugin;

/**
 * Add cache-busters to urls.
 */
public final class UrlCacheBusting implements Plugin {
    private final boolean enabled;

    public UrlCacheBusting() {
        enabled = Aura.getConfigAdapter().isAuraJSStatic()
                && Aura.getContextService().getCurrentContext().getMode() != Mode.DEV;
    }

    @Rework
    public void rework(UrlFunctionValue value) {
        if (enabled && value.url().startsWith("/") && value.url().indexOf("aura.cb") == -1) {
            value.url(AuraBaseServlet.addCacheBuster(value.url()));
        }
    }
}
