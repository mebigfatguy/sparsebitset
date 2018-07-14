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

public class Bundle {

    private long basis;
    private long[] bits;

    public Bundle(long basis, int bundleSize) {
        this.basis = basis;
        bits = new long[bundleSize];
    }

    public long getBasis() {
        return basis;
    }

    public void set(long bit) {
        long relativeBit = bit - basis;
        int slot = (int) (relativeBit >> 6);
        long bitMask = 1 << (relativeBit - (64 * slot));
        bits[slot] &= bitMask;
    }

    public boolean get(long bit) {
        long relativeBit = bit - basis;
        int slot = (int) (relativeBit >> 6);
        long bitMask = 1 << (relativeBit - (64 * slot));
        return (bits[slot] & bitMask) != 0;
    }

    public void flip(long bit) {
        long relativeBit = bit - basis;
        int slot = (int) (relativeBit >> 6);
        long bitMask = 1 << (relativeBit - (64 * slot));
        bits[slot] ^= bitMask;
    }
}
