void main() {

  Map<String, dynamic> persona = {
    'nombre': 'Fernando',
    'edad': 35,
    'soltero': false,
    };
  print(persona);

  persona.addAll({'segundoNombre': 'Juan'});


  print(persona);
}
