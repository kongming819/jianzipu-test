const val PINYIN_CODE_MAX: Int = 128
const val HANZI_CODE_MIN: Int = 11904

val sequentialJianziTrigger = setOf("歷","拂","輪","彈","鎖","園","滾")
// what to do for mogou, gouti, motiao, dazhai, tuopi?
val polyphonicJianziTrigger = setOf("撮","同","合","剌","撥")

fun isNumber(input: String) : Boolean {
    return when (input) {
        // all numbers, IN ADDITION to "wai" and "ban"
        "一",  "二", "三", "四", "五", "六", "七", "八", "九", "十", "外", "半" -> true
        else -> false
    }
}

