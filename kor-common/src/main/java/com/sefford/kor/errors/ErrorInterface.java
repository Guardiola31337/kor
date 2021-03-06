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

package com.sefford.kor.errors;

/**
 * Generic Error interface for delegates
 * <p/>
 * The Base Error encapsulates the bare information to identify an error, this is, an Error Code which
 * can be used for Error identification, a human-readable string error for information purposes and
 * an internal string in order to identify it to logger.
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
public interface ErrorInterface {

    /**
     * Returns the API info code
     *
     * @return Status code of the API,
     */
    int getStatusCode();

    /**
     * Returns the human readable error
     *
     * @return Human redeable error or generic string
     */
    String getUserError();

    /**
     * Returns the  inner message error.
     *
     * @return Message for logging purposes
     */
    String getMessage();
}
