import java.lang.NullPointerException

fun main() {
    // test text
    // <blank>
    // 大九挑六
    // da jiu tiao liu
    // !@#$
    // é


    // get input, loop until you have a non-empty input
    var input = ""
    while (input != "exit") {
        input = inputJianzi()
        val tokens = tokenize(input)
        parseTokens(tokens)
    }
}

fun tokenize(inputLine: String) : List<String> {
    val tokens = mutableListOf<String>()
    // if it is Chinese hanzi, tokenize every character
    if (inputLine[0].code >= HANZI_CODE_MIN) {
        for (character in inputLine) {
            tokens.add(character.toString())
        }
    }
    // if it is Pinyin, tokenize by space
    else if (inputLine[0].isLetterOrDigit()) {
        return inputLine.split(" ")
    }
    else {
        println("Input is invalid. Please retry")
    }
    return tokens
}

fun inputJianzi() : String {
    var input = ""
    while (input == "") {
        print("Input: ")
        input = readLine()!!
    }
    return input
}

fun parseTokens(tokenizedInput: List<String>) {
    //todo: make a look up table
    for (token in tokenizedInput) println(token)
}