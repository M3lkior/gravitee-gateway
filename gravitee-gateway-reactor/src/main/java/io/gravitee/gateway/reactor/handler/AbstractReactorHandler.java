/**
 * Copyright (C) 2015 The Gravitee team (http://gravitee.io)
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
package io.gravitee.gateway.reactor.handler;

import io.gravitee.common.component.AbstractLifecycleComponent;
import io.gravitee.gateway.reactor.Reactable;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.ClassUtils;

/**
 * @author David BRASSELY (david at gravitee.io)
 * @author GraviteeSource Team
 */
public abstract class AbstractReactorHandler extends AbstractLifecycleComponent<ReactorHandler> implements ReactorHandler, ApplicationContextAware {

    protected ApplicationContext applicationContext;

    private ClassLoader classLoader;

    @Override
    public ClassLoader classloader() {
        return (this.classLoader != null ? this.classLoader : ClassUtils.getDefaultClassLoader());
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public Reactable reactable() {
        return null;
    }

    @Override
    public String contextPath() {
        return "";
    }

    @Override
    protected void doStart() throws Exception {
        // Nothing to do there
    }

    @Override
    protected void doStop() throws Exception {
        if (applicationContext != null) {
            ((ConfigurableApplicationContext)applicationContext).close();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
