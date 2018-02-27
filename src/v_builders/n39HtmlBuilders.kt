package v_builders

import util.TODO
import util.doc39
import v_builders.data.getProducts
import v_builders.htmlLibrary.*

fun getTitleColor() = "#b9c9fe"
fun getCellColor(row: Int, column: Int) = if ((row + column) %2 == 0) "#dce4ff" else "#eff2ff"

fun todoTask39(): Nothing = TODO(
    """
        Task 39.
        1) Fill the table with the proper values from products.
        2) Color the table like a chess board (using getTitleColor() and getCellColor() functions above).
        Pass a color as an argument to functions 'tr', 'td'.
        You can call the 'main' function in the 'htmlDemo.kt' to see the rendered table.
    """,
    documentation = doc39()
)

fun color(td: TD, row: Int, col: Int): Unit {
    td.set("color", getTitleColor())
    td.set("bgcolor", getCellColor(row, col))
}

fun renderProductTable(): String {
    return html {
        var row = 0
        table {
            row++
            tr {
                td {
                    text("Product")
                }.apply { color(this, row, 1) }
                td {
                    text("Price")
                }.apply { color(this, row, 2) }
                td {
                    text("Popularity")
                }.apply { color(this, row, 3) }
            }
            val products = getProducts()
            var row = 0
            products.forEach {
                row++
                tr {
                    td { text(it.description) }.apply { color(this, row, 1) }
                    td { text(it.price) }.apply { color(this, row, 2) }
                    td { text(it.popularity) }.apply { color(this, row, 3) }
                }
            }
            //todoTask39()
        }
    }.toString()
}
