package cbedoy.pokeflow.model

import androidx.room.TypeConverter

class TypeStringConverters {
    @TypeConverter
    fun fromTypes(value: List<String>) = value.joinToString(separator = ",")

    @TypeConverter
    fun toTypes(value: String) = value.split(",")
}