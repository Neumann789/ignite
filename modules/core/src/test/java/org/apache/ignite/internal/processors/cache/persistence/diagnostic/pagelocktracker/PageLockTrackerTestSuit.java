/*
 * Copyright 2019 GridGain Systems, Inc. and Contributors.
 *
 * Licensed under the GridGain Community Edition License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.gridgain.com/products/software/community-edition/gridgain-community-edition-license
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ignite.internal.processors.cache.persistence.diagnostic.pagelocktracker;

import org.apache.ignite.internal.processors.cache.persistence.diagnostic.pagelocktracker.dumpprocessors.ToFileDumpProcessorTest;
import org.apache.ignite.internal.processors.cache.persistence.diagnostic.pagelocktracker.log.HeapArrayLockLogTest;
import org.apache.ignite.internal.processors.cache.persistence.diagnostic.pagelocktracker.log.OffHeapLockLogTest;
import org.apache.ignite.internal.processors.cache.persistence.diagnostic.pagelocktracker.stack.HeapArrayLockStackTest;
import org.apache.ignite.internal.processors.cache.persistence.diagnostic.pagelocktracker.stack.OffHeapLockStackTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite for all tests ralated to {@link PageLockTracker}.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    PageLockTrackerManagerTest.class,
    SharedPageLockTrackerTest.class,
    ToFileDumpProcessorTest.class,
    HeapArrayLockLogTest.class,
    OffHeapLockLogTest.class,
    HeapArrayLockStackTest.class,
    OffHeapLockStackTest.class,
})

public class PageLockTrackerTestSuit {

}