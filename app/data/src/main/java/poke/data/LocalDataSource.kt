package poke.data

import poke.data.database.PokeDao
import poke.data.model.Poke

class LocalDataSource(
    private val dao: PokeDao
) {
    fun save(poke : Poke) {
        dao.insert(poke)
    }

    val allPokes
        get() = dao.getAll()

    val allTypes
        get() = dao.getAllTypes()

    fun pokesFilterBy(type: String): List<Poke> {
        return dao.getPokesFilterBy(type)
    }
}