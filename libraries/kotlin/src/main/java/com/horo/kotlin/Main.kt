package com.horo.kotlin

fun main() {
    val string = """
    """.trimIndent()
    print(createTableWith3Column(tableString = string))
}

fun createTableWith3Column(tableString: String): String {
    return (("| $tableString").replace("\t", " | ")
        .replace("\n", " |\n| ") + " |")
        .replaceFirst("\n", "\n|---|---|---|\n")
}
