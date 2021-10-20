fun main() {
    // ======== test texts:========
    // <blank>
    // 大九挑六
    // da jiu tiao liu
    // !@#$
    // é

    var tokens : List<String>
    var input = ""

    while (input != "exit") {
        input = inputJianzi()
        if (input.isNotBlank() && input != "exit") {
            tokens = tokenize(input)
            parseTokens(tokens)
            val unifiedTokens = unifyHanziTokens(tokens)
            println("Now these should be unified input")
            parseTokens(unifiedTokens)
        }
    }
}

fun inputJianzi() : String {
    print("Input: ")
    val input: String? = readLine()
    // return input if it's not blank or null
    if (input != "" && input != null) {
        return input
    }
    // default - this should not happen
    println("Input was blank or null")
    return ""
}