/*
 * Copyright 2017 eagle.jfaster.org.
 * <p>
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
 * </p>
 */

package eagle.jfaster.org.listener;

import com.google.common.base.Charsets;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;

/**
 * Created by fangyanpeng1 on 2017/8/4.
 */
public abstract class AbstractChildrenDataListener implements PathChildrenCacheListener {
    @Override
    public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
        ChildData childData = event.getData();
        if (null == childData) {
            return;
        }
        String path = childData.getPath();
        if (path.isEmpty()) {
            return;
        }
        dataChanged(path, event.getType(), null == childData.getData() ? "" : new String(childData.getData(), Charsets.UTF_8));
    }

    protected abstract void dataChanged(final String path, final PathChildrenCacheEvent.Type eventType, final String data);
}
