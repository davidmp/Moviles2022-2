void main() {
    final wolverine = new Heroe(nombre: 'Logan', poder:'Regeneración',dato: "");
    //wolverine.nombre = 'LoganX';
    //wolverine.poder = 'RegeneraciónX';
    print(wolverine);

    var wolverinex = new Heroe(nombre: 'Logan', poder:'Regeneración', dato: "");
    wolverinex.mostraDatos();

}


class Heroe {
  String nombre;
  String poder;
  String dato;

  String telefono="875454";
  String? genero;


  Heroe({required this.nombre, required this.poder, required this.dato});
  //Heroe({required this.nombre, required this.poder, required this.dato, required this.telefono});
  
  
  String toString() {
    return 'Heroe: nombre: ${this.nombre}, poder: ${this.poder}';
  }

  void mostraDatos(){
    print('Heroe: nombre: $nombre ');
  }
}
