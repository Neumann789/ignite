/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ignite.internal.processors.cache;

import com.google.common.collect.*;
import org.apache.ignite.*;
import org.apache.ignite.configuration.*;
import org.gridgain.grid.cache.*;

import javax.cache.*;
import javax.cache.spi.*;
import java.util.*;

/**
 *
 */
public class IgniteCachingProviderSelfTest extends IgniteCacheAbstractTest {
    /** {@inheritDoc} */
    @Override protected int gridCount() {
        return 0;
    }

    /** {@inheritDoc} */
    @Override protected GridCacheMode cacheMode() {
        return GridCacheMode.REPLICATED;
    }

    /** {@inheritDoc} */
    @Override protected GridCacheAtomicityMode atomicityMode() {
        return GridCacheAtomicityMode.TRANSACTIONAL;
    }

    /** {@inheritDoc} */
    @Override protected GridCacheDistributionMode distributionMode() {
        return GridCacheDistributionMode.PARTITIONED_ONLY;
    }

    /** {@inheritDoc} */
    @Override public String getTestGridName(int idx) {
        assert idx == 0;

        return null;
    }

    /** {@inheritDoc} */
    @Override protected IgniteConfiguration getConfiguration(String gridName) throws Exception {
        assert gridName == null;

        IgniteConfiguration cfg = super.getConfiguration(gridName);

        GridCacheConfiguration cache1 = cacheConfiguration(null);
        cache1.setName("cache1");

        GridCacheConfiguration cache2 = cacheConfiguration(null);
        cache2.setName("cache2");

        cfg.setCacheConfiguration(cacheConfiguration(null), cache1, cache2);

        return cfg;
    }

    /** {@inheritDoc} */
    @Override protected void beforeTestsStarted() throws Exception {
        // No-op. Disabling start of ignite.
    }

    /** {@inheritDoc} */
    @Override protected void afterTest() throws Exception {
        stopAllGrids();
    }

    /**
     *
     */
    public void testStartIgnite() {
        CachingProvider cachingProvider = Caching.getCachingProvider();

        assert cachingProvider instanceof IgniteCachingProvider;

        CacheManager cacheMgr = cachingProvider.getCacheManager();

        assertEquals(Collections.<String>emptySet(), Sets.newHashSet(cacheMgr.getCacheNames()));

        Cache<Integer, String> cacheA = cacheMgr.createCache("a", new GridCacheConfiguration());

        cacheA.put(1, "1");

        assertEquals("1", cacheA.get(1));

        cacheMgr.createCache("b", new GridCacheConfiguration());

        assertEquals(Sets.newHashSet("a", "b"), Sets.newHashSet(cacheMgr.getCacheNames()));

        cacheMgr.destroyCache("a");
        cacheMgr.destroyCache("b");

        assertEquals(Collections.<String>emptySet(), Sets.newHashSet(cacheMgr.getCacheNames()));
    }

    /**
     *
     */
    public void testCloseManager() throws Exception {
        startGridsMultiThreaded(1);

        CachingProvider cachingProvider = Caching.getCachingProvider();

        assert cachingProvider instanceof IgniteCachingProvider;

        CacheManager cacheMgr = cachingProvider.getCacheManager();

        cachingProvider.close();

        assertNotSame(cacheMgr, cachingProvider.getCacheManager());
    }
}
