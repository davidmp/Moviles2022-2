package pe.edu.upeu.proyectovcmjc.modelo

data class MsgModelResponse(
    var success:Boolean,
    var data:List<Persona>,
    var message:String
)

/*data class MsgModelMatricula(
    var success:Boolean,
    var data:List<Matricula>,
    var message:String
)*/

data class MsgModelMatricula(
    var success:Boolean,
    var data:List<MatriculaResp>,
    var message:String
)