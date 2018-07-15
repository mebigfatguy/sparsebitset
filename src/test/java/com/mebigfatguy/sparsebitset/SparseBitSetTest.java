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

    @Test
    public void testSimple3BundleInsert() {
        SparseBitSet sbs = new SparseBitSet(3);

        sbs.set(64);
        sbs.set(1);
        sbs.set(130);
        Assertions.assertTrue(sbs.get(64));
        Assertions.assertFalse(sbs.get(63));
        Assertions.assertFalse(sbs.get(65));
        Assertions.assertTrue(sbs.get(1));
        Assertions.assertFalse(sbs.get(0));
        Assertions.assertFalse(sbs.get(2));
        Assertions.assertTrue(sbs.get(130));
        Assertions.assertFalse(sbs.get(129));
        Assertions.assertFalse(sbs.get(131));
    }

    @Test
    public void testSimpleDisjointBundleInsert() {
        SparseBitSet sbs = new SparseBitSet(1);

        sbs.set(1);
        sbs.set(128);
        Assertions.assertTrue(sbs.get(1));
        Assertions.assertFalse(sbs.get(0));
        Assertions.assertFalse(sbs.get(2));
        Assertions.assertTrue(sbs.get(128));
        Assertions.assertFalse(sbs.get(127));
        Assertions.assertFalse(sbs.get(129));
    }
}
