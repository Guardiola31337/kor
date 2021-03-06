/*
 * Copyright (C) 2014 Saúl Díaz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sefford.kor.interactors;


import com.sefford.kor.common.interfaces.Loggable;
import com.sefford.kor.common.interfaces.Postable;
import com.sefford.kor.errors.ErrorInterface;
import com.sefford.kor.interactors.interfaces.NetworkDelegate;
import com.sefford.kor.responses.ResponseInterface;

/**
 * Standard Network Interactor that performs the Request Process in this order:
 * * <ul>
 * <li>Network Retrieval phase.</li>
 * <li>Post processing.</li>
 * <li>Saving to cache.</li>
 * <li>Notifying to the UI for success</li>
 * <p/>
 * </ul>
 * <p/>
 * In any moment the Strategy can notify of an error through {@link com.sefford.kor.interactors.interfaces.InteractorNotification#notifyError(com.sefford.kor.errors.ErrorInterface) NotifyError} interface.
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
public class StandardNetworkInteractor<R extends ResponseInterface, E extends ErrorInterface> extends NetworkInteractor<R, E> {
    /**
     * Creates a new instance of Standard Interactor
     *
     * @param bus      Notification Facility
     * @param log      Logging facilities
     * @param delegate Request to execute
     */
    public StandardNetworkInteractor(Postable bus, Loggable log, NetworkDelegate<R, E> delegate) {
        super(bus, log, delegate);
    }

    @Override
    public void run() {
        try {
            final R content = ((NetworkDelegate<R, E>) delegate).retrieveNetworkResponse();
            final R processedContent = ((NetworkDelegate<R, E>) delegate).postProcess(content);
            long start = System.currentTimeMillis();
            ((NetworkDelegate<R, E>) delegate).saveToCache(processedContent);
            log.d(TAG, delegate.getInteractorName() + "(Saving):" + (System.currentTimeMillis() - start) + "ms");
            notifySuccess(processedContent);
        } catch (Exception x) {
            log.e(TAG, delegate.getInteractorName(), x);
            notifyError(((NetworkDelegate<R, E>) delegate).composeErrorResponse(x));
        }
    }
}
