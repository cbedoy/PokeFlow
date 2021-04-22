package cbedoy.pokeflow.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import cbedoy.pokeflow.model.Poke

@Database(entities = [Poke::class], version = 5)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokeDao(): PokeDao
}