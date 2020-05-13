package br.com.zupflix.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.zupflix.data.database.model.User

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    fun getUser(email: String, password: String) : LiveData<User>
}

/*
*
* As funções de suspensão estão no centro de tudo as corotinas.
* Uma função de suspensão é simplesmente uma função que pode ser pausada e
* retomada posteriormente. Eles podem executar uma operação de execução longa
* e aguardar a conclusão sem bloquear.

A sintaxe de uma função de suspensão é semelhante à de uma função regular,
* exceto pela adição da suspendpalavra - chave. Pode levar um parâmetro e ter um tipo
* de retorno. No entanto, as funções de suspensão só podem ser invocadas por outra
* função de suspensão ou dentro de uma corotina.
* */