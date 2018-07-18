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
    private Bundle[] bundles;

    public SparseBitSet() {
        this(DEFAULT_BUNDLE_SIZE);
    }

    public SparseBitSet(int bundleSize) {
        this.bundleSize = bundleSize;
        this.bundleBits = bundleSize * 64;
    }

    public void set(long bit) {
        Bundle bundle = getBundle(bit, true);
        bundle.set(bit);
    }

    public boolean get(long bit) {
        Bundle bundle = getBundle(bit, false);
        return bundle == null ? false : bundle.get(bit);
    }

    public void flip(long bit) {
        Bundle bundle = getBundle(bit, true);
        bundle.flip(bit);
    }

    private Bundle getBundle(long bit, boolean create) {
        if (bundles == null) {
            if (!create) {
                return null;
            } else {
                long modulo = 64 * bundleSize;
                Bundle bundle = new Bundle((bit / modulo) * modulo, bundleSize);
                bundles = new Bundle[] { bundle };
                return bundle;
            }
        }

        int low = 0;
        int high = bundles.length - 1;
        int mid = 0;
        Bundle bundle = bundles[0];
        while (low <= high) {
            mid = (low + high) >> 1;

            bundle = bundles[mid];
            if (bundle.getBasis() > bit) {
                high = mid - 1;
            } else if ((bundle.getBasis() + bundleBits) <= bit) {
                low = mid + 1;
            } else {
                break;
            }
        }

        if ((bundle.getBasis() <= bit) && ((bundle.getBasis() + bundleBits) > bit)) {
            return bundle;
        }

        if (!create) {
            return null;
        }

        long modulo = 64 * bundleSize;
        Bundle newBundle = new Bundle((bit / modulo) * modulo, bundleSize);
        if (newBundle.getBasis() > bundle.getBasis()) {
            mid++;
        }

        Bundle[] newBundles = new Bundle[bundles.length + 1];
        System.arraycopy(bundles, 0, newBundles, 0, mid);
        newBundles[mid] = newBundle;
        System.arraycopy(bundles, mid, newBundles, mid + 1, bundles.length - mid);
        bundles = newBundles;

        return newBundle;
    }

    public long cardinality() {
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
        return bundles == null ? "[]" : Arrays.toString(bundles);
    }
}
