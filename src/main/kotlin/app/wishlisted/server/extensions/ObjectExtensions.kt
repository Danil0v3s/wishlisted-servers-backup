package app.wishlisted.server.extensions

fun Any.isNumeric(): Boolean {
    return this.toString().toIntOrNull() != null
}
