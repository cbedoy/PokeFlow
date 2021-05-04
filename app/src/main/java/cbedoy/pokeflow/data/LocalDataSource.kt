package cbedoy.pokeflow.data

import cbedoy.pokeflow.data.database.PokeDao
import cbedoy.pokeflow.model.Poke

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
}