/** sparsebitset - a Sparse BitSet
 * Copyright 2017-2018 MeBigFatGuy.com
 * Copyright 2017-2018 Dave Brosius
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations
 * under the License.
 */
package com.mebigfatguy.sparsebitset;

import java.util.Arrays;

public class SparseBitSet {

    private static final int DEFAULT_BUNDLE_SIZE = 8;

    private int bundleSize;
    private int bundleBits;
    private Bundle[] positiveBundles;
    private Bundle[] negativeBundles;

    public SparseBitSet() {
        this(DEFAULT_BUNDLE_SIZE);
    }

    public SparseBitSet(int bundleSize) {
        this.bundleSize = bundleSize;
        this.bundleBits = bundleSize * 64;
    }

    public void set(long bit) {
        Bundle bundle = getBundle(bit, true);
        bundle.set(Math.abs(bit));
    }

    public void clear(long bit) {
        Bundle bundle = getBundle(bit, false);
        if (bundle != null) {
            bundle.clear(Math.abs(bit));
        }
    }

    public void flip(long bit) {
        Bundle bundle = getBundle(bit, true);
        bundle.flip(Math.abs(bit));
    }

    public boolean get(long bit) {
        Bundle bundle = getBundle(bit, false);
        return bundle == null ? false : bundle.get(Math.abs(bit));
    }

    private Bundle getBundle(long bit, boolean create) {
        long absBit = Math.abs(bit);
        long modulo = 64 * bundleSize;
        Bundle[] activeBundles = bit >= 0 ? positiveBundles : negativeBundles;
        if (activeBundles == null) {
            if (!create) {
                return null;
            } else {
                Bundle bundle = new Bundle((absBit / modulo) * modulo, bundleSize);
                if (bit >= 0) {
                    positiveBundles = new Bundle[] { bundle };
                } else {
                    negativeBundles = new Bundle[] { bundle };
                }
                return bundle;
            }
        }

        int low = 0;
        int high = activeBundles.length - 1;
        int mid = 0;
        Bundle bundle = activeBundles[0];
        while (low <= high) {
            mid = (low + high) >> 1;

            bundle = activeBundles[mid];
            if (bundle.getBasis() > absBit) {
                high = mid - 1;
            } else if ((bundle.getBasis() + bundleBits) <= absBit) {
                low = mid + 1;
            } else {
                break;
            }
        }

        if ((bundle.getBasis() <= absBit) && ((bundle.getBasis() + bundleBits) > absBit)) {
            return bundle;
        }

        if (!create) {
            return null;
        }

        Bundle newBundle = new Bundle((absBit / modulo) * modulo, bundleSize);
        if (newBundle.getBasis() > bundle.getBasis()) {
            mid++;
        }

        Bundle[] newBundles = new Bundle[activeBundles.length + 1];
        System.arraycopy(activeBundles, 0, newBundles, 0, mid);
        newBundles[mid] = newBundle;
        System.arraycopy(activeBundles, mid, newBundles, mid + 1, activeBundles.length - mid);
        if (bit >= 0) {
            positiveBundles = newBundles;
        } else {
            negativeBundles = newBundles;
        }

        return newBundle;
    }

    public long cardinality() {
        return cardinality(positiveBundles) + cardinality(negativeBundles);
    }

    private long cardinality(Bundle[] bundles) {
        if (bundles == null) {
            return 0;
        }

        long cardinality = 0;
        for (Bundle bundle : bundles) {
            cardinality += bundle.getCardinality();
        }

        return cardinality;
    }

    @Override
    public String toString() {
        return (positiveBundles == null ? "[]" : Arrays.toString(positiveBundles)) + "/" + (negativeBundles == null ? "[]" : Arrays.toString(negativeBundles));
    }
}
