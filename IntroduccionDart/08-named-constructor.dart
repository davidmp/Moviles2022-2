import 'dart:convert';

void main() {
    final rawJson = {'nombre': 'Tony Stark', 'poder': 'Dinero'};
    // final ironman = new Heroe( nombre: rawJson['nombre']!, poder: rawJson['poder']! );
    final ironman = Heroe.fromJson(rawJson);
    var rawjsx= {'nombre': 'Tony Stark', 'poder': 'Dinero', "genero":"M"};
    var ironmanx = Heroe.fromJsonx(rawjsx);

    print(ironman);

    print(ironmanx);
    // final wolverine = new Heroe(nombre:'Logan', poder: 'Regeneración');
    // print( wolverine );
}

class Heroe {
  String nombre;
  String poder;
  String genero="";

  Heroe({required this.nombre, required this.poder});

  Heroe.fromJson(Map<String, String> json)
  : this.nombre = json['nombre']!,
    this.poder = json['poder'] ?? 'No tiene poder';

  Heroe.fromJsonx(Map<String, String> json)
  : this.nombre = json['nombre']!,
    this.poder = json['poder'] ?? 'No tiene poder',
    this.genero=json['genero']!;
  
  String toString() {
    return 'Heroe: nombre: ${this.nombre}, poder: ${this.poder}, genero:${this.genero}';
  }

}