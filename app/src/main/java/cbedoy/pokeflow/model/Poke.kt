package cbedoy.pokeflow.model

data class Poke(
    val name: String = "",
    val amount: Float = 0.0f,
    val description: String = "",
    val secondaryDescription: String = "",
    val image: String = "",
    val color: String = "",
    val type: String = "",
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