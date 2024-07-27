package ua.oshevchuk.flagchallenge.ui.entities

data class Country(
    val name: String,
    val flagUrl: String
){
    companion object{
        fun getCountries() = listOf(
            Country("United States", "https://flagcdn.com/w320/us.png"),
            Country("China", "https://flagcdn.com/w320/cn.png"),
            Country("India", "https://flagcdn.com/w320/in.png"),
            Country("Ukraine", "https://flagcdn.com/w320/ua.png"),
            Country("Germany", "https://flagcdn.com/w320/de.png"),
            Country("United Kingdom", "https://flagcdn.com/w320/gb.png"),
            Country("France", "https://flagcdn.com/w320/fr.png"),
            Country("Japan", "https://flagcdn.com/w320/jp.png"),
            Country("Brazil", "https://flagcdn.com/w320/br.png"),
            Country("Canada", "https://flagcdn.com/w320/ca.png"),
            Country("Italy", "https://flagcdn.com/w320/it.png"),
            Country("South Korea", "https://flagcdn.com/w320/kr.png"),
            Country("Australia", "https://flagcdn.com/w320/au.png"),
            Country("Mexico", "https://flagcdn.com/w320/mx.png"),
            Country("Spain", "https://flagcdn.com/w320/es.png"),
            Country("Indonesia", "https://flagcdn.com/w320/id.png"),
            Country("Turkey", "https://flagcdn.com/w320/tr.png"),
            Country("Saudi Arabia", "https://flagcdn.com/w320/sa.png"),
            Country("Netherlands", "https://flagcdn.com/w320/nl.png"),
            Country("Switzerland", "https://flagcdn.com/w320/ch.png"),
            Country("Argentina", "https://flagcdn.com/w320/ar.png"),
            Country("South Africa", "https://flagcdn.com/w320/za.png"),
            Country("Nigeria", "https://flagcdn.com/w320/ng.png"),
            Country("Egypt", "https://flagcdn.com/w320/eg.png"),
            Country("Thailand", "https://flagcdn.com/w320/th.png"),
            Country("Sweden", "https://flagcdn.com/w320/se.png"),
            Country("Poland", "https://flagcdn.com/w320/pl.png"),
            Country("Belgium", "https://flagcdn.com/w320/be.png"),
            Country("Norway", "https://flagcdn.com/w320/no.png"),
            Country("Greece", "https://flagcdn.com/w320/gr.png")
        )
    }
}


