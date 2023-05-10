package pe.edu.upeu.proyectovcmjc.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pe.edu.upeu.proyectovcmjc.data.local.DbDataSource
import pe.edu.upeu.proyectovcmjc.data.local.dao.MatriculaDao
import pe.edu.upeu.proyectovcmjc.data.local.dao.PersonaDao
import pe.edu.upeu.proyectovcmjc.data.remote.RestDataSource
import pe.edu.upeu.proyectovcmjc.data.remote.RestMatricula
import pe.edu.upeu.proyectovcmjc.util.TokenUtils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl()= TokenUtils.API_URL  //"http://192.168.1.124:8001/"

    @Singleton
    @Provides
    fun provideRetrofit(@Named("BaseUrl") baseUrl:String):Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl).build()
    }
    //Similar a estos
    @Singleton
    @Provides
    fun restDataSource(retrofit: Retrofit):RestDataSource{
        return retrofit.create(RestDataSource::class.java)
    }

    @Singleton
    @Provides
    fun dbDataSource(@ApplicationContext context:Context):DbDataSource{
        return Room.databaseBuilder(context, DbDataSource::class.java, "db_persona")
            .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun personaDao(db:DbDataSource):PersonaDao{
        return db.personaDao();
    }


    @Singleton
    @Provides
    fun restMatricula(retrofit: Retrofit):RestMatricula{
        return retrofit.create(RestMatricula::class.java)
    }

    @Singleton
    @Provides
    fun matriculaDao(db:DbDataSource):MatriculaDao{
        return db.matriculaDao();
    }


}