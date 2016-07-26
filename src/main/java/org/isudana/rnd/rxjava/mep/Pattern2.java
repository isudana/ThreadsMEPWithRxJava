/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.isudana.rnd.rxjava.mep;

import org.isudana.rnd.rxjava.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

public class Pattern2 {

    private static final Logger log = LoggerFactory.getLogger(Pattern2.class);

    public static void main(String[] args) {

        org.apache.log4j.BasicConfigurator.configure();

        int workerThreadProcessingTime = 5000;       // indication of time takes to do some processing at worker thread

        // Step 1 - Starting main thread
        log.info("[TID:" + Thread.currentThread().getId() + "] " + System.currentTimeMillis() +
                 " Starting work of main worker");

        // Step 2 - Delegate a task to a new worker - Out-only MEP
        Observable observable = new Worker("worker1").submit(workerThreadProcessingTime, "worker1 request");

        log.info("[TID:" + Thread.currentThread().getId() + "] " + System.currentTimeMillis() +
                 " Main Worker - End");
    }

}
