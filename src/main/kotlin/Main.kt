fun main(args: Array<String>) {
    // test text
    // 大九挑六 da jiu tiao liu
    parseTokens(inputAndTokenize())
}

fun inputAndTokenize() : List<String> {
    print("Input: ")
    val tokens: List<String>
    val input = readLine()!!
    if (isChinese(input)) {
        tokens = mutableListOf<String>()
        for (character in input) {
            tokens.add(character.toString())
        }
    }
    else {
        tokens = input.split(" ")
    }
    return tokens
}

fun isChinese(inputLine: String) : Boolean {
    return inputLine[0].code >= ENG_ASCII_MAX
    // assume if code value of first character is > 128, then it is probably Chinese

    //anything below 11904 is probably invalid, but should it be checked?
}

fun parseTokens(tokenizedInput: List<String>) {
    //todo: make a look up table
    for (token in tokenizedInput) println(token)
}