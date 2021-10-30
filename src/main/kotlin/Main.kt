fun main() {
    // ======== test texts:========
    // <blank>
    // 大九半绰挑六
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
            val finalParsedTokens = parseTokens(unifiedTokens)
            println("Final parsed tokens are $finalParsedTokens") //debug
        }
    }
}

fun inputJianzi() : String {
    print("Input: ")
    // return "" if null
    return readLine() ?: ""
}