/*
 * Copyright 2021 Red Hat, Inc. and/or its affiliates.
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
package org.jbpm.process.longrest;

import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActiveTasks {

    private final Logger logger = LoggerFactory.getLogger(ActiveTasks.class);

    private Semaphore semaphore = new Semaphore(Integer.MAX_VALUE);

    public void started() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void completed() {
        semaphore.release();
    }

    public void waitAllCompleted() throws InterruptedException {
        logger.debug("Waiting all completed...");
        semaphore.acquire(Integer.MAX_VALUE);
        logger.debug("All completed.");
    }
}