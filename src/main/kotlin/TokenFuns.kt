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
    // This function takes a list of hanzi tokens and translates variants to the internal unified list
    // as defined in LUT.kt
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

    // debug purposes:
    for (token in tokenizedInput) println(token)

    val finalParsedTokens = mutableListOf<List<String>>()

    // todo: taoqisansheng handler and other special cases

    if (tokenizedInput.any{ it in sequentialJianziTrigger }) {
        //todo
        println("This is a sequential jianzi")
    }
    if (tokenizedInput.any{ it in polyphonicJianziTrigger }) {
        //todo
        println("This is a polyphonic jianzi")
    }
    // todo: the following is for single note jianzi, need to implement sequential and polyphonic
    val headTokens = mutableListOf<String>()
    val bodyTokens = mutableListOf<String>()
    when (tokenizedInput[0]) {
        "散", "大", "食", "中", "名", "跪", "就", "反", -> {
            // collect all head tokens
            val nextIndex = headTokensCollector(tokenizedInput, headTokens)
            println("Head tokens are $headTokens and the next index to process is $nextIndex")
            finalParsedTokens.add(headTokens)
            bodyTokensCollector(nextIndex, tokenizedInput, bodyTokens)
            println("Body tokens are $bodyTokens")
            finalParsedTokens.add(bodyTokens)
            println("Final parsed tokens are $finalParsedTokens")
        }
        "托", "擘", "抹", "挑", "勾", "剔", "打", "摘", -> {
            finalParsedTokens.add(listOf(""))
            bodyTokensCollector(0, tokenizedInput, bodyTokens)
            println("Body tokens are $bodyTokens")
            finalParsedTokens.add(bodyTokens)
            println("Final parsed tokens are $finalParsedTokens")
        }
        else -> println("aux") // todo
    }
}

fun headTokensCollector(tokensList: List<String>, headTokens: MutableList<String>) : Int {
    for ((i, token) in tokensList.withIndex()) {
        if (i == 0) {
            // the first character is obviously not a number, so continue
            headTokens.add(token)
            continue
        }
        if ( isNumber(token) ) {
            headTokens.add(token)
        }
        else return i
    }
    return 0
}

fun bodyTokensCollector(index : Int = 0, tokensList: List<String>, bodyTokens: MutableList<String>) {
    for (i in index until tokensList.size) {
        // we assume that the rest of the tokens belong to the body of the character
        bodyTokens.add(tokensList[i])
    }
}