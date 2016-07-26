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

package org.isudana.rnd.rxjava;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.subjects.BehaviorSubject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Worker {

    private ExecutorService executorService = Executors.newFixedThreadPool(1);
    private String name;
    private static final Logger log = LoggerFactory.getLogger(Worker.class);

    public Worker(String name) {
        this.name = name;
    }

    public Observable submit(int processingTime, String message) {

        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();

        executorService.submit(() -> {

            log.info("[TID:" + Thread.currentThread().getId() + "] "
                     + System.currentTimeMillis() + " " + name + " " + " Starting work.. ");
            try {
                Thread.sleep(processingTime);      // This mimic some processing work
            } catch (InterruptedException e) {
                log.error("Process interrupted", e);
            }
            log.info("[TID:" + Thread.currentThread().getId() + "] " +
                     " " + System.currentTimeMillis() + " " + name + " Ending work..");

            behaviorSubject.onNext("Result for " + message);
        });

        return behaviorSubject;
    }

}
