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

public class Pattern3 {

    private static final Logger log = LoggerFactory.getLogger(Pattern3.class);

    public static void main(String[] args) {

        org.apache.log4j.BasicConfigurator.configure();

        int mainThreadProcessingTime = 5000;        // indication of time takes to do some processing at main thread
        int workerThreadProcessingTime = 10000;       // indication of time takes to do some processing at worker thread

        // Step 1 - Starting main thread
        log.info("[TID:" + Thread.currentThread().getId() + "] " + System.currentTimeMillis() +
                 " Starting work of main worker");

        // Step 2 - Delegate a task to a new worker
        Observable observable = new Worker("worker1").submit(workerThreadProcessingTime, "worker1 request");

        log.info("[TID:" + Thread.currentThread().getId() + "] " + System.currentTimeMillis() +
                 " Main worker - Before subscribing");

        // Step 3 - Continue to do work on main thread
        try {
            Thread.sleep(mainThreadProcessingTime);             // mimic some work for main thread
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 4 - Subscribe to responses of spawned worker thread - When we subscribe, result is not available
        // as mainThreadProcessingTime < workerThreadProcessingTime
        observable.subscribe(v -> {
            log.info("[TID:" + Thread.currentThread().getId() + "] " + System.currentTimeMillis() +
                     " Main worker received value : " + v);
        });

        log.info("[TID:" + Thread.currentThread().getId() + "] " + System.currentTimeMillis() +
                 " Main Worker - After subscribing");
    }

}
