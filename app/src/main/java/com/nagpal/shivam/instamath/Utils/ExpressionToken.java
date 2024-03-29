/*
 * Copyright 2018 Shivam Nagpal
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

package com.nagpal.shivam.instamath.Utils;

public class ExpressionToken {
    private final String token;
    private final boolean isFunction;

    public ExpressionToken(String token, boolean isFunction) {
        this.token = token;
        this.isFunction = isFunction;
    }

    public String getToken() {
        return token;
    }

    public boolean isFunction() {
        return isFunction;
    }
}
