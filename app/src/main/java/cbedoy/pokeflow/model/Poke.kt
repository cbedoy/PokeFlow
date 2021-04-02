package cbedoy.pokeflow.model

data class Poke(
    val name: String = "",
    val number: String = "",
    val description: String = "",
    val secondaryDescription: String = "",
    val image: String = "",
    val color: String = "",
    val type: List<String> = emptyList(),
    val moves: String = "",
    val abilities: String = "",
){
    val thumbnail: String
        get() {
            return if (image.isEmpty()){
                "https://dummyimage.com/300x300/${color.replace("#", "")}/FFF.png&text=${name}"
            }else image
        }
}