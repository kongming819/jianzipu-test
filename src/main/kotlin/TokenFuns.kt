fun tokenize(inputLine: String) : List<String> {
    // splits the input into tokens, depending on whether it is hanzi (by character) or by pinyin (space delimited)
    // returns a List<String> of tokens

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
    // need to think of a couple different ways to do this
    // 1) translate pinyin locally, let the user choose which character, or
    // 2) the "smart" way, algorithmically determine what the possibility is
    // the latter is harder with words like fu
    return tokensList
}

fun unifyHanziTokens(tokensList: List<String>) : List<String> {
    // This function takes a list of hanzi tokens and translates variants to the internal unified list
    // as defined in LUT.kt
    // returns the revised list of tokens
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

fun parseTokens(tokenizedInput: List<String>) : List<List<String>> {
    // one possible algo is to start with the initial hanzi to determine what type it is
    // below is the implementation for this algo

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
    // todo: the following is for single note jianzi, after ambiguous cases handled.
    //  need to implement sequential and polyphonic
    val headTokens = mutableListOf<String>()
    val bodyTokens = mutableListOf<String>()
    val alterationTokens = mutableListOf<String>()
    when (tokenizedInput[0]) {
        // This is the implementation for single jianzi, but could start a polyphonic one too
        "散", "大", "食", "中", "名", "跪", "就", "反", -> {
            // collect all head tokens and get the index for the body collector
            var nextIndex = headTokensCollector(tokenizedInput, headTokens)
            println("Head tokens are $headTokens and the next index to process is $nextIndex") //debug
            finalParsedTokens.add(headTokens)

            // add on any alterations like chuo or zhu (note also fan if cuo)
            nextIndex = alterationTokensCollector(nextIndex, tokenizedInput, alterationTokens)
            println("Alteration tokens are $alterationTokens and the next index to process is $nextIndex")
            finalParsedTokens.add(alterationTokens)

            // resume with body tokens collector
            bodyTokensCollector(nextIndex, tokenizedInput, bodyTokens)
            println("Body tokens are $bodyTokens") //debug
            finalParsedTokens.add(bodyTokens)

            // all tokens should be split into three lists and combined into a single list
            return finalParsedTokens
        }
        // This is always a SINGLE jianzi
        "托", "擘", "抹", "挑", "勾", "剔", "打", "摘", -> {
            finalParsedTokens.add(listOf(""))
            bodyTokensCollector(0, tokenizedInput, bodyTokens)
            println("Body tokens are $bodyTokens") //debug
            finalParsedTokens.add(bodyTokens)
            return finalParsedTokens
        }
        else -> {
            println("aux")
            finalParsedTokens.add(tokenizedInput)
            return finalParsedTokens
        } // todo
    }
}

fun headTokensCollector(tokensList: List<String>, headTokens: MutableList<String>) : Int {
    for ((i, token) in tokensList.withIndex()) {
        if (i == 0) {
            // the first character is obviously not a number, so continue
            headTokens.add(token)
            continue
        }
        // add all subsequent numbers, stop if there are no more numbers
        if ( isNumber(token) ) {
            headTokens.add(token)
        }
        else return i
    }
    return 0
}

fun bodyTokensCollector(index : Int = 0, tokensList: List<String>, bodyTokens: MutableList<String>) {
    // we assume that the rest of the tokens belong to the body of the jianzi
    for (i in index until tokensList.size) {
        bodyTokens.add(tokensList[i])
    }
}

fun alterationTokensCollector(index: Int, tokensList: List<String>, alterationTokens: MutableList<String>) : Int {
    return when (tokensList[index]) {
        "綽", "注", "泛" -> {
            alterationTokens.add(tokensList[index])
            index + 1
        }
        else -> {
            alterationTokens.add("")
            index
        }
    }
}