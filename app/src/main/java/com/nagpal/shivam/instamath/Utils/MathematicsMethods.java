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

import java.util.ArrayList;

public class MathematicsMethods {
    public static Long[] getPrimeFactors(long n) {
        ArrayList<Long> factorsArrayList = new ArrayList<>();
        boolean included = false;
        long last = (long) Math.sqrt(n);
        while (n % 2 == 0) {
            n /= 2;
            if (!included) {
                factorsArrayList.add(2L);
                included = true;
            }
        }
        if (n != 1) {
            int i;
            for (i = 3; i <= last; i += 2) {
                included = false;
                while (n % i == 0) {
                    n /= i;
                    if (!included) {
                        factorsArrayList.add((long) i);
                        included = true;
                    }
                    if (n == 1) {
                        break;
                    }
                }
            }
            if (n > 1) {
                factorsArrayList.add(n);
            }
        }
        Long[] factors = new Long[factorsArrayList.size()];
        factors = factorsArrayList.toArray(factors);
        return factors;
    }
}
