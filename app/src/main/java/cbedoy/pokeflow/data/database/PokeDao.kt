package cbedoy.pokeflow.data.database

import androidx.room.*
import cbedoy.pokeflow.model.Poke

@Dao
interface PokeDao {
    @Query("SELECT * FROM poke ORDER BY number DESC")
    fun getAll(): List<Poke>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(pokes: List<Poke>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(poke: Poke)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(poke: Poke)
}