package pe.edu.upeu.proyectovcmjc.data.remote

import pe.edu.upeu.proyectovcmjc.modelo.Matricula
import pe.edu.upeu.proyectovcmjc.modelo.MsgModelMatricula
import pe.edu.upeu.proyectovcmjc.modelo.User
import pe.edu.upeu.proyectovcmjc.modelo.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface RestMatricula {
    @GET("/api/matricula")
    suspend fun reportarMatricula(@Header("Authorization") token:String): Response<MsgModelMatricula>

    @POST("/api/matricula")
    suspend fun insertarMatricula(@Header("Authorization") token:String, @Body matricula: Matricula): Response<MsgModelMatricula>

    @POST("/api/auth/login")
    suspend fun login(@Body user: User): UserResponse
}