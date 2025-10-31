package com.horo.unscramble.data

object DataSourceTest {
    val wordList = setOf(
        "at",
        "sea",
        "home",
        "arise",
        "banana",
        "android",
        "birthday",
        "briefcase",
        "motorcycle",
        "cauliflower"
    )

    private val wordLengthMap: Map<Int, String> = wordList.associateBy({ it.length }, { it })

    fun getUnscrambledWord(scrambledWord: String) = wordLengthMap[scrambledWord.length] ?: ""
}
