// @dart=2.9
import 'package:flutter/material.dart';
import './comp/CalcButton.dart';
void main() => runApp(CalcApp());
class CalcApp extends StatefulWidget {
  const CalcApp({Key key}) : super(key: key);
  @override
  CalcAppState createState() => CalcAppState();
}

class CalcAppState extends State<CalcApp> {
  bool useMaterial3 = true;
  bool useLightMode = true;
  int colorSelected = 0;
  ThemeData themeData;
  String valorAnt = '';
  String operador = '';
  TextEditingController _controller = new TextEditingController();

  void numClick(String text) {
    setState(() => _controller.text += text);
    print(_controller);
  }

  void clear(String text) {
    setState(() {
      _controller.text = '';
    });
  }
  void opeClick(String text) {
    setState(() {
      valorAnt = _controller.text;
      operador = text;
      _controller.text = '';
    });
  }
//Aqui codigo
  void resultOperacion(String text) {
    setState(() {
      switch (operador) {
        case "/":
          _controller.text =
              (int.parse(valorAnt) / int.parse(_controller.text)).toString();
          break;
        case "*":
          _controller.text =
              (int.parse(valorAnt) * int.parse(_controller.text)).toString();
          break;
        case "+":
          _controller.text =
              (int.parse(valorAnt) + int.parse(_controller.text)).toString();
          break;
        case "-":
          _controller.text =
              (int.parse(valorAnt) - int.parse(_controller.text)).toString();
          break;
        case "%":
          _controller.text =
              (int.parse(valorAnt) % int.parse(_controller.text)).toString();
          break;
      }
    });
  }
  @override
  Widget build(BuildContext context) {

    List<List> labelList = [["AC","C","%","/"],["7","8","9","*"],["4","5","6","-"],["1","2","3","+"],[".","0","00","="]];
    List<List> funx=[[clear,clear, opeClick,opeClick ],
      [numClick,numClick, numClick,opeClick ],
      [numClick,numClick, numClick,opeClick ],
      [numClick,numClick, numClick,opeClick ],
      [numClick,numClick, numClick,resultOperacion ]];

    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Calculator',
      theme:ThemeData(
        primarySwatch: Colors.deepOrange,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: Scaffold(
        body: Container(
          padding: EdgeInsets.all(20),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Card(
                color:
                Theme.of(context).colorScheme.surfaceVariant,
                margin: EdgeInsets.fromLTRB(20, 0, 20, 0),
                child: TextField(
                  textAlign: TextAlign.end,
                  controller: _controller,
                ),
              ),
              SizedBox(height: 20),
              ...List.generate(labelList.length, (index) =>
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: [
                      ...List.generate(
                        labelList[index].length,
                            (indexx) => CalcButton(
                          text: labelList[index][indexx],
                          callback: funx[index][indexx] as Function,
                        ),
                      ),
                    ],
                  )
              )
//aqui codigo
            ],
          ),
        ),
      ),
    );
  }

}
