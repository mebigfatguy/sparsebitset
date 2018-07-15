package com.mebigfatguy.sparsebitset;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SparseBitSetTest {

    @Test
    public void testSimple1BundleInsert() {
        SparseBitSet sbs = new SparseBitSet(1);

        sbs.set(64);
        Assertions.assertTrue(sbs.get(64));
        Assertions.assertFalse(sbs.get(63));
        Assertions.assertFalse(sbs.get(65));
    }
}
