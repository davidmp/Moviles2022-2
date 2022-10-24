void main() {
    final nombrex = 'Fernando';
    
    saludar(nombrex);
    
    saludar(nombrex, "Holas");

    saludar2(nombre: nombrex, mensaje: "Saludos");
    saludar2(nombre:nombrex,mensaje:"Holas");

    int valor=sumarx(87, 3);
    print(valor);

  }

  void saludar(String nombre, [String mensaje = 'Hi']){
    print('$mensaje $nombre');
  }

  void saludar2({ required String nombre, required String mensaje, }) {
    print('$mensaje $nombre');
  }

  int sumarx(int a, int b){
    return a+b;
  }