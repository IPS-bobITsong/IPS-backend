package smu.it.ips2

data class MenuBook(
    val foodname: String? = null,
    val carbo: Double? = null,
    val cholesterol: Double? = null,
    val fat: Double? = null,
    val saturatedfat: Double? = null,
    val protein: Double? = null,
    val sodium: Double? = null,
    val sugars: Double? = null,
    var isSelected: Boolean = false
)

