package smu.it.ips2

data class MenuBook(
    val carbo: Double? = null,
    val cholesterol: Double? = null,
    val fat: Double? = null,
    val foodname: String? = null,
    val protein: Double? = null,
    val saturatedfat: Double? = null,
    var isSelected: Boolean = false,
    val sodium: Double? = null,
    val sugars: Double? = null
)

