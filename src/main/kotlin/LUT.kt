const val PINYIN_CODE_MAX: Int = 128

const val HANZI_CODE_MIN: Int = 11904

val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 1)

// conflicting pinyin's:
/*
    san - both 三， 散
    shi - 食， 十
    chuo -
 */

val LHLUT = mapOf(
    "san" to "LH0",
    "da" to "LH1",
    "shi" to "LH2",
    "zhong" to "LH3",
    "ming" to "LH4"
)

// 1 thumb -- 4 ring
// 0 no RH
val RHLUT = mapOf(
    "mo" to "RH2i",
    "tiao" to "RH2o",
    "gou" to "RH3i",
    "ti" to "RH3o",
    "da" to "RH4i",
    "zhai" to "RH4o",
    "tuo" to "RH1o",
    "pi" to "RH1i"
)