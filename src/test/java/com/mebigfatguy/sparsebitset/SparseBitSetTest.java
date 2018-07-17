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
    public void testNegativeSimple1BundleInsert() {
        SparseBitSet sbs = new SparseBitSet(1);

        sbs.set(-64);
        Assertions.assertTrue(sbs.get(-64));
        Assertions.assertFalse(sbs.get(-63));
        Assertions.assertFalse(sbs.get(-65));
    }

    @Test
    public void testSimpleFlip() {
        SparseBitSet sbs = new SparseBitSet(1);

        sbs.flip(64);
        Assertions.assertTrue(sbs.get(64));
        sbs.flip(64);
        Assertions.assertFalse(sbs.get(64));
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

    @Test
    public void testIncrementingBundleInsert() {
        SparseBitSet sbs = new SparseBitSet(2);

        for (int i = 0; i < 10000; i++) {
            sbs.set(i);
        }

        for (int i = 0; i < 10000; i++) {
            Assertions.assertTrue(sbs.get(i), "Expected " + i + " to be set but wasn't");
        }
    }

    @Test
    public void testRandomDisjointBundleInsert() {
        SparseBitSet sbs = new SparseBitSet(2);

        long[] inputs = new long[] { 2942, 45797, 44871, 878, 88508, 76175, 11029, 79707, 86876, 32463, 53777, 76818, 94825, 97298, 93488, 79747, 5356, 42610,
                36622, 76937, 71915, 25283, 36680, 90667, 51992, 36436, 58566, 63621, 56164, 81356, 27272, 56288, 94631, 63227, 16996, 64759, 55261, 56991,
                39293, 48376, 64366, 87407, 79410, 16437, 48468, 78060, 28214, 7112, 9218, 71800, 92039, 54258, 90032, 99493, 2851, 5454, 73555, 33383, 43566,
                21647, 96237, 85277, 18810, 58137, 77903, 76453, 72916, 34707, 88975, 98549, 13443, 36146, 97416, 87319, 9875, 90172, 72720, 2591, 46531, 98741,
                46759, 43459, 65909, 83050, 2569, 50456, 8538, 97160, 76106, 70284, 88305, 43433, 30711, 74797, 20760, 7190, 94372, 84295, 40073, 41293, 4346,
                36285, 9293, 5448, 78226, 28170, 21073, 40267, 98390, 64320, 9823, 39397, 94299, 23785, 98743, 46443, 43132, 8393, 2991, 71127, 23300, 54029,
                42650, 90206, 88069, 4416, 79387, 152, 11660, 33616, 56213, 60718, 13327, 61868, 54138, 46818, 82702, 40728, 84523, 54942, 45938, 40641, 99875,
                50419, 15588, 2311, 9934, 73356, 12545, 34505, 6820, 99970, 74372, 19759, 17803, 46252, 47839, 82200, 65485, 30840, 30492, 89327, 54346, 79369,
                64405, 23325, 47509, 46794, 78833, 79957, 80266, 23024, 48524, 59375, 48941, 36949, 45367, 39895, 22277, 18875, 91020, 30922, 13826, 87528,
                2795, 50732, 71279, 69408, 34616, 93617, 353, 18298, 71918, 97253, 15072, 12447, 58488, 29935, 86600, 80963, 32532, 81913, 52343, 92464, 12324,
                76, 97468, 79864, 59332, 39710, 35969, 19430, 62755, 67057, 88505, 17721, 73475, 12411, 21014, 31167, 77170, 74834, 3933, 96579, 55553, 89354,
                89008, 7806, 13630, 13811, 55258, 55164, 28039, 79942, 65879, 51540, 57597, 64683, 49742, 59683, 29254, 49974, 26327, 75310, 93609, 13657,
                85981, 74029, 822, 23518, 2360, 8917, 94700, 35876, 71786, 61667, 92546, 88986, 63727, 87616, 5738, 36302, 38824, 89870, 19469, 67346, 73815,
                62493, 98854, 95938, 22463, 31884, 77026, 4128, 31662, 68808, 67729, 29956, 86501, 91066, 38159, 78581, 2912, 28180, 37844, 24660, 19064, 31793,
                84222, 96394, 46777, 87944, 98339, 49026, 71520, 86536, 83016, 26329, 54898, 21762, 25411, 61765, 78739, 41438, 84540, 86920, 1583, 30270,
                56285, 41429, 54243, 83796, 44935, 31059, 89016, 16917, 77311, 85930, 5640, 5906, 16129, 78877, 39016, 99866, 47464, 95141, 62905, 28084, 53454,
                33746, 35687, 85520, 22797, 31901, 16478, 37402, 54804, 62042, 2106, 37336, 59008, 14845, 97751, 88320, 11313, 24149, 72231, 70450, 92071,
                26992, 3060, 36252, 95257, 13485, 68275, 74730, 54972, 17278, 49837, 53462, 57922, 79704, 24502, 55108, 4241, 70449, 42218, 99692, 41838, 44235,
                3760, 47091, 92465, 64786, 36282, 81468, 93825, 78344, 36264, 54624, 60657, 52220, 57401, 45986, 17604, 49364, 46651, 24050, 93289, 79317, 688,
                52785, 58109, 86023, 58473, 19972, 30784, 86723, 11260, 90694, 95061, 939, 20203, 29788, 66430, 58308, 86432, 93806, 22793, 92330, 2614, 27130,
                19742, 86661, 91977, 85024, 58387, 72635, 12272, 56108, 32569, 46695, 14579, 64092, 73201, 29189, 65026, 19956, 41583, 67098, 33071, 2017,
                25176, 3662, 93268, 303, 90393, 24590, 91275, 3012, 65319, 55107, 48694, 98025, 77399, 45617, 98233, 7730, 36297, 40834, 42163, 10948, 25890,
                64662, 79718, 59229, 69390, 86676, 46513, 8756, 64794, 39327, 49454, 94686, 7668, 64296, 54113, 75134, 53500, 1539, 75838, 5625, 13533, 73743,
                20200, 89034, 44199, 12174, 34451, 39133, 17654, 25632, 73669, 74736, 59687, 72, 16442, 74162, 61764, 69835, 70452, 98476, 59562, 28074, 27184,
                99681, 69447, 62366, 6613, 50062, 64468, 46535, 87222, 20411, 28821, 38613, 26144, 46301, 15102, 93908, 71036, 10486, 37969, 34919, 21256,
                37895, 3967, 6197, 38183, 42972, 58117, 99720, 52863, 77713, 6682, 26503, 57834, 93099, 98806, 50454, 94542, 28716, 82632, 54832, 41117, 37057,
                16211, 44936, 88149, 40593, 5267, 35105, 55505, 61523, 31547, 59466, 65647, 34976, 56539, 4367, 20093, 24005, 5153, 74593, 44464, 85375, 7157,
                24144, 68434, 76971, 94895, 76291, 92429, 56599, 41627, 56202, 72330, 13050, 47715, 85123, 79982, 23925, 90523, 21357, 17515, 15212, 14297,
                31837, 77890, 89894, 3352, 93818, 92367, 63011, 9349, 73248, 82341, 63883, 71875, 25806, 43361, 61830, 89804, 13722, 21989, 75243, 92686, 96837,
                29340, 38724, 2365, 19977, 29854, 6952, 59728, 14525, 66766, 12033, 93686, 97732, 51178, 76381, 51047, 66414, 21947, 77231, 23605, 19923, 73951,
                71115, 80180, 317, 59282, 64692, 87652, 72418, 39440, 5919, 21314, 92801, 27764, 23819, 71097, 56703, 79004, 74900, 8369, 46088, 10266, 74788,
                27721, 62303, 18155, 58607, 68001, 56775, 75248, 36436, 46443, 10645, 7695, 57008, 53914, 49814, 51534, 50326, 19337, 2603, 22718, 80999, 68028,
                17515, 82100, 42072, 56345, 4698, 31393, 62296, 78773, 42092, 3750, 36964, 35080, 59513, 85798, 49742, 55695, 68611, 74759, 43579, 28039, 66218,
                89694, 95160, 11378, 8649, 91948, 20339, 58976, 52796, 2300, 66853, 58620, 55824, 15840, 7595, 93950, 32945, 86156, 49541, 72751, 64464, 18870,
                19720, 15570, 23477, 44580, 6233, 95222, 75198, 12818, 49159, 77569, 1199, 40477, 31390, 53031, 37991, 61114, 18368, 30825, 41136, 23757, 7072,
                24272, 7201, 88206, 51289, 2064, 6445, 707, 97832, 44260, 14456, 87263, 31395, 58915, 17585, 43717, 68654, 57021, 27432, 99900, 5303, 46947,
                20328, 79672, 80776, 27207, 67776, 33865, 55255, 10123, 66952, 88791, 76014, 62752, 59548, 25778, 47760, 29429, 3950, 28985, 70288, 106, 68127,
                75358, 78755, 85512, 32613, 7684, 20117, 24544, 54771, 3797, 38643, 80582, 25739, 48733, 41745, 77826, 88205, 76917, 7972, 63441, 87274, 15735,
                83214, 94687, 23942, 16291, 72630, 33787, 78802, 95388, 66422, 91854, 14797, 49782, 31646, 96234, 57074, 69280, 63286, 7870, 54172, 4373, 58252,
                6880, 55130, 68119, 40248, 54530, 52808, 88910, 15666, 42241, 92707, 15736, 71225, 16560, 12488, 32438, 55292, 50051, 64814, 47766, 88551,
                49710, 41763, 43147, 14228, 21624, 75748, 91942, 51811, 10486, 61852, 15213, 57115, 38597, 30696, 70891, 64403, 33418, 58398, 91375, 72085,
                77139, 1156, 92359, 66239, 19149, 76957, 90709, 16063, 15887, 69821, 52292, 15741, 51125, 73195, 74721, 93653, 83748, 29096, 82505, 14798,
                54945, 11462, 41658, 11863, 71097, 86645, 36704, 30862, 4252, 10595, 24049, 27863, 33379, 47966, 97643, 18904, 29427, 12565, 31448, 64296,
                92644, 39894, 17155, 93786, 75784, 92631, 49153, 96779, 35151, 61631, 46407, 23705, 41829, 40901, 83485, 31597, 6526, 92972, 79714, 45818,
                86922, 85189, 8437, 14949, 59421, 36713, 76796, 65747, 67266, 12148, 70254, 89905, 37579, 72639, 29313, 8735, 28324, 43400, 25470, 82272, 97111,
                91158, 70432, 57922, 764, 23261, 99040, 17511, 4849, 62964, 65400, 77272, 10462, 82789, 99, 4317, 40466, 45326, 97097, 15933, 89148, 88308,
                47979, 76668, 26190, 12527, 73913, 73008, 85282, 77770, 65112, 26458, 13885, 64823, 64721, 30346, 27683, 10173, 63, 2507, 29911, 51549, 86931,
                90119, 52111, 71992, 33796, 31959, 77842, 74387, 70434, 40536, 5429, 58491, 42299, 80816, 65165, 32780, 83402, 52954, 57171, 53647, 90719,
                52067, 39604, 11762, 32010, 86856, 59958, 66860, 54757, 24095 };

        for (int i = 0; i < 1000; i++) {
            sbs.set(inputs[i]);
        }

        for (int i = 0; i < 1000; i++) {
            Assertions.assertTrue(sbs.get(inputs[i]));
        }
    }
}
