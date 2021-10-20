fun tokenize(inputLine: String) : List<String> {
    // the test of whether it is Chinese hanzi or Pinyin is very rudimentary.
    // Hanzi checks if it is above a certain codepoint.
    // Otherwise, assume it is Pinyin if Kotlin detects it is a letter or digit.
    // This is problematic because what if it is neither?
    // todo: check if the entire input is valid, i.e. entirely Chinese char or entirely pinyin.

    // if it is Chinese hanzi, tokenize every character
    var tokens = mutableListOf<String>()
    if (inputLine[0].code >= HANZI_CODE_MIN) {
        for (character in inputLine) {
            tokens.add(character.toString())
        }
    }
    // if it is Pinyin, tokenize by space and translate into hanzi
    else if (inputLine[0].isLetterOrDigit()) {
        tokens = pinyinToHanzi(inputLine.split(" ")) as MutableList<String>
    }
    else {
        println("Input is invalid. Please retry")
    }
    // return tokens that aren't blank
    return tokens.filter { it.isNotBlank() }
}

fun pinyinToHanzi(tokensList: List<String>) : List<String> {
    // todo, this could be a real, real doozy
    return tokensList
}

fun unifyHanziTokens(tokensList: List<String>) : List<String> {
    val unifiedTokens = mutableListOf<String>()
    for (token in tokensList) {
        if (token in unifyHanziMap) {
            unifiedTokens.add(unifyHanziMap[token].toString())
        } else {
            unifiedTokens.add(token)
        }
    }
    return unifiedTokens
}

fun parseTokens(tokenizedInput: List<String>) {
    // todo: make a look up table, and lots of other shit
    // one possible algo is to start with the initial hanzi to determine what type it is

    // todo: ambiguous case handler - 急, 泛, 绰, 注, numbers, 大, 小

    for (token in tokenizedInput) println(token)
    when (tokenizedInput[0]) {
        "散", "大", "食", "中", "名", "跪", "就" -> println("head") // run head processor
        "托", "擘", "抹", "勾", "剔", "打", "摘" -> println("body") // run body processor
        else -> println("aux")
    }
}