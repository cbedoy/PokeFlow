package poke.data.database

import androidx.room.*
import poke.data.model.Poke

@Dao
interface PokeDao {
    @Query("SELECT * FROM poke ORDER BY number ASC")
    fun getAll(): List<Poke>

    @Query("SELECT DISTINCT type from poke")
    fun getAllTypes(): List<String>

    @Query("SELECT * FROM poke WHERE type LIKE :typeText")
    fun getPokesFilterBy(typeText: String): List<Poke>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(pokes: List<Poke>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(poke: Poke)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(poke: Poke)
}