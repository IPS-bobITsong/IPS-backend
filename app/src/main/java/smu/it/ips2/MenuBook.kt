package smu.it.ips2

data class MenuBook(
    val carbo: Double? = 0.0,
    val cholesterol: Double? = 0.0,
    val fat: Double? = 0.0,
    val foodname: String? = "",
    val protein: Double? = 0.0,
    val saturatedfat: Double? = 0.0,
    var isSelected: Boolean = false,
    val sodium: Double? = 0.0,
    val sugars: Double? = 0.0
)

