package pe.edu.upeu.proyectovcmjc.ui.presentation.screens.persona

import android.media.Image
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.google.gson.Gson
import com.valentinilk.shimmer.shimmer
import pe.edu.upeu.proyectovcmjc.modelo.Persona
import pe.edu.upeu.proyectovcmjc.R
import pe.edu.upeu.proyectovcmjc.ui.navigation.Destinations
import pe.edu.upeu.proyectovcmjc.ui.presentation.components.BottomNavigationBar
import pe.edu.upeu.proyectovcmjc.util.TokenUtils
import pe.edu.upeu.proyectovcmjc.util.isInternetAvailable


@Composable
fun PersonaUI (navegarEditarPer: (String) -> Unit, viewModel: PersonaViewModel= hiltViewModel()){
    val users by viewModel.users.observeAsState(arrayListOf())
    val usersmat by viewModel.usersmat.observeAsState(arrayListOf())
    val isLoading by viewModel.isLoading.observeAsState(false)

    Log.i("VERX", ""+users!!.size )
    Log.i("VERX2", ""+usersmat!!.size )

    MyApp(onAddClick = {
        //viewModel.addUser()
        navegarEditarPer((0).toString())
    }, onDeleteClick = {
        viewModel.deleteUser(it)
    }, users, isLoading,
        onEditClick = {
        val jsonString = Gson().toJson(it)
        navegarEditarPer(jsonString)
    }
    )
}

@Composable
fun MyApp(
    onAddClick: (() -> Unit)? = null,
    onDeleteClick: ((toDelete: Persona) -> Unit)? = null,
    personas: List<Persona>,
    isLoading: Boolean,
    onEditClick: ((toPersona: Persona)->Unit)?=null
) {
    val context = LocalContext.current
    val navController = rememberNavController()
    val navigationItems2 = listOf(
        Destinations.Pantalla1,
        Destinations.Pantalla2,
        Destinations.Pantalla3,
        Destinations.PantallaQR
    )
    val scaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(initialValue =
        DrawerValue.Closed)
    )
    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomNavigationBar(navController = navController, items = navigationItems2)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onAddClick?.invoke()
            }) {
                Icon(
                    imageVector = Icons.Default.Add, contentDescription =
                    "Fab Icon"
                )
            }
        },
        isFloatingActionButtonDocked = false,
        floatingActionButtonPosition = FabPosition.Center,
    ) {
        LazyColumn{
            var itemCount = personas.size
            if (isLoading) itemCount++
            items(count = itemCount) { index ->
                var auxIndex = index;
                if (isLoading) {
                    if (auxIndex == 0)
                        return@items LoadingCard()
                    auxIndex--
                }
                val persona = personas[auxIndex]
                Card(
                    shape = RoundedCornerShape(8.dp),
                    elevation = 1.dp,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .fillMaxWidth(),
                ) {
                    Row(modifier = Modifier.padding(8.dp)) {
                        Image(
                            modifier = Modifier.size(50.dp),
                            painter = rememberImagePainter(
                                data = persona.dni,
                                builder = {
                                    placeholder(R.drawable.bg)
                                    error(R.drawable.bg)
                                }
                            ),
                            contentDescription = null,
                            contentScale = ContentScale.FillHeight
                        )
                        Spacer()
                        Column(
                            Modifier.weight(1f),
                        ) {
                            Text("${persona.nombre} ${persona.apellido_paterno}")
                            Text(persona.telefono!!)
                        }
                        Spacer()
                        IconButton(onClick = {
                            onDeleteClick?.invoke(persona)
                        }) {
                            Icon(Icons.Filled.Delete, "Remove", tint = Color.Red)
                        }
                        IconButton(onClick = {
//onDeleteClick?.invoke(persona)
                            Log.i("VERTOKEN", "Holas")
                            Log.i("VERTOKEN", TokenUtils.TOKEN_CONTENT)
                            var estado = isInternetAvailable(context)
                            Log.i("CONEXION", "VEr: " + estado)
                            onEditClick?.invoke(persona)
                        }) {
                            Icon(Icons.Filled.Edit, "Editar", tint = Color.Blue)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Spacer(size: Int = 8) = Spacer(modifier = Modifier.size(size.dp))

@Composable
fun ImageLoading() {
    Box(modifier = Modifier.shimmer()) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color.Gray)
        )
    }
}

@Composable
fun LoadingCard() {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 1.dp,
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth()
            .testTag("loadingCard")
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            ImageLoading()
            Spacer()
            Column {
                Spacer()
                Box(modifier = Modifier.shimmer()) {
                    Column {
                        Box(
                            modifier = Modifier
                                .height(15.dp)
                                .fillMaxWidth()
                                .background(Color.Gray)
                        )
                        Spacer()
                        Box(
                            modifier = Modifier
                                .height(15.dp)
                                .fillMaxWidth()
                                .background(Color.Gray)
                        )
                    }
                }
            }
        }
    }
}