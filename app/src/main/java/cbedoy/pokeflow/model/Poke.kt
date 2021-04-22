package cbedoy.pokeflow.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Poke(
    @PrimaryKey val number: Long,
    val name: String = "",
    val description: String = "",
    val secondaryDescription: String = "",
    val image: String = "",
    val color: String = "",
    val type: String = "",
    val moves: String = "",
    val abilities: String = "",
)
