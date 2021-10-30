const val CDL_MAX = 128

fun generateCdl(parsedTokens: List<List<String>>) : String {
    // the algorithm for this will change depending on the type of jianzi passed in.

    // this is for a single note jianzi, with either head and body defined or only body defined.
    // getJianziType() // determine type of Jianzi
    val headTokens = parsedTokens[0]
    val bodyTokens = parsedTokens[1]

    // determine what the coordinates are based on the body tokens
    // this is for Vertical Config = "2.0"
    when (bodyTokens[0]) {
        "挑" -> {
            println("points='0,0 $CDL_MAX,$CDL_MAX'")
        }
    }
    return ""
}