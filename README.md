# Enunciat

Com a tercera PAC, os proposem que desenvolupeu en Java la mateixa aplicació de la prova PAC2. D’aquesta forma, podreu reutilitzar l’anàlisi de la PAC2, bastant codi i fins i tot millorar (si així ho desitgeu ) el disseny realitzat a la PAC anterior. A més, us permetrà valorar millor les diferències entre el codi font C++ i Java, que podeu comentar breument al fitxer llegiume.txt

Només per aquells que ja domineu Java i com OPCIONAL per ajudar a obtenir la màxima nota, us proposo la generació de llistats també en pdf (de la forma més simple possible). Amb aquesta funció, es proporciona una referència que us pot ser útil.

[http://www.1t3xt.com/about/](http://www.1t3xt.com/about/)

# Resposta


## Entorn de desenvolupament

- IDE: NetBeans 7.0.1 (Java SE)
- SO: Ubuntu 11.10 + OpenJDK 1.6

```sh
$: java -version
java version "1.6.0_23"
OpenJDK Runtime Environment (IcedTea6 1.11pre) (6b23~pre11-0ubuntu1.11.10)
OpenJDK Client VM (build 20.0-b11, mixed mode, sharing)
```

### Per afegir o modificar els arguments del programa

En propietats del projecte, en la secció "Execució" establir:

```
2010
```

on 2010 és l'any que es passa com paràmetre

## Gestió de tasques amb l'IDE

Afegint patrons ToDo


## Diferències de Java / C++

- En java les variables que són objectes s'accedeixen per referència i els de tipus senzills per valor.
- En C++ a no ser que s'indiqui, sempre s'accedeix per valor, tant per objectes com per variables senzilles.


## Millores

- S'han aplicat modificadors private, public i protected, aïllant les funcionalitats de l'aplicació en paquets, per tal de restringir l'ambit de les variables/mètodes.
- En gairebé totes les classes s'han implementat mètodes getter i setter per accedir a les variables de les classes, generalment private.
- S'han corregit error en el joc de proves.
- S'ha modelitzat de nou l'estructura del programa per aprofitar les notorietats de Java.
- S'han utilitzat llistes genèriques de Java (LinkedList) amb iteradors genèrics de java (ListIterator).
