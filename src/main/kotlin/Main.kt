fun main() {
    // ======== test texts:========
    // <blank>
    // 大九半挑六
    // da jiu tiao liu
    // !@#$
    // é

    var tokens : List<String>
    var input = ""

    while (input != "exit") {
        input = inputJianzi()
        if (input.isNotBlank() && input != "exit") {
            tokens = tokenize(input)
            if (tokens.isEmpty()) continue
            val unifiedTokens = unifyHanziTokens(tokens)
            parseTokens(unifiedTokens)
        }
    }
}

fun inputJianzi() : String {
    print("Input: ")
    val input: String? = readLine()
    // return input if it's not blank or null
    return if (input != "" && input != null) {
        input
    } else ""
}