# OOChess

## About

This project was developed in the Object Oriented Development class during my bachelors at FCUL. The idea behind the project was to implement a few functionalities of a chess tournament management app, in Java, with greater attention to the design behind the application, learning how to make full use of object-oriented languages and object-oriented analysis and design. Not only did this help me learn how to plan the implementation of an object-oriented app, through the construction of use-case models, domain models and class diagrams, for example, but also thaught me about software design patterns and how to apply them to an object-oriented language such as Java, further increasing my knowledge about the language. Although it wasn't a requirement of the project, it also gave me a great opportunity to learn functional programming in java and its uses in combination with an object-oriented design.

The final grade for this project was 17.6/20

## Design & Development

### 1. Use-Case Diagram

To begin the project, a use-case diagram was built with all the seven functionalities we were told the app should have. Although only two of the seven were meant to be implemented, it was necessary to have a full scope of what information the app would require to perform each operation.

<p align="center">
  <img src="https://github.com/JoaoAAnjos/OOChess/blob/main/rsc/casosUso.png">
</p>

### 2. Domain Model

Next up, the domain model was drawn. This is the essential skeleton of the app, and most of the following steps are based on this model, so great care and thought was given as to what objects should exist, and what association should exist between them, while maintaining the good practices of an object-oriented design.

<p align="center">
  <img src="https://github.com/JoaoAAnjos/OOChess/blob/main/rsc/modeloDominio.png">
</p>

### 3. System Sequence Diagrams

After the completion of the domain model, system sequence diagrams were built for each of the two use cases to be implemented. These were helpful in order to draft and better understand the system behavior associated with each operation. Since further design techniques that involve a single use case were used, only one of the two will be shown in this "walkthrough" (, so the design progression can be better understood.

<p align="center">
  <img src="https://github.com/JoaoAAnjos/OOChess/blob/main/rsc/cs5.png">
</p>

### 4. Operation Contracts

The next step was to write operation contracts for each of the operations created in the SSD, to have a more detailed description of how the system would execute what us asked, what objects in the Domain Model would it require, and what it would change.

```sh
indicaTorneio(nome)
Pr??-condi????o:
    * existe jogador_autenticado:Jogador corrente
P??s-condi????o:
    *Seja t:Torneio tal que
           *existe associa????o entre t e Cat??logoDeTorneios
           *t.nome = nome
           *t.dataFim > dataAtual
             *torneio_pertencente:Torneio = t
    *existe i:Inscri????o tal que
           *existe associa????o "faz" entre i e jogador_autenticado
           *existe associa????o "em" entre i e torneio_pertencente
```
```sh
indicaEloRange(??Elo)
Pr??-condi????o:
    *existe jogador_autenticado:Jogador corrente
P??s-condi????o:
    *Seja j:Jogador[] tal que
           *exista associa????o entre j[i] e Cat??logoDeJogadores
           *j[i].ELO < jogador_autenticado.ELO + ??Elo
           *j[i].ELO > jogador_autenticado.ELO - ??Elo
             *jogadores_elegiveis:Jogador[] = j

```
```sh
indicaJogador(username)
Pr??-condi????o:
   *existe jogadores_elegiveis:Jogador[]
P??s-condi????o:
    *Seja j:Jogador tal que
           *existe associa????o entre j e Cat??logoDeJogadores
           *j.username = username
           *existe j[i].username = j.username
             *j:Jogador = jogador_desafiado
    *se existir torneio_pertencente:Torneio
           *existe i:Inscri????o tal que
                  *existe associa????o "faz" entre i e jogador_desafiado
                  *existe associa????o "em" entre i e torneio_pertencente

```
```sh
indicaHoraEData(hora, data)
Pr??-condi????o:
    *existe jogador_desafiado:Jogador
    *existe jogador_autenticado:Jogador corrente
P??s-condi????o:
    *Seja ld:ListaDeDesafiosPendentes tal que
           *existe associa????o "tem" entre jogador_desafiado e ld
    *foi criada ep:??poca tal que
           *e.hora = hora
           *e.data = data
    *foi criado d:Desafio tal que
           *foi criada associa????o "indica uma" entre d e ep
           *foi criada associa????o "faz" entre d e jogador_autenticado
           *foi criada associa????o "contem" entre d e ld
    *se existir torneio_pertencente:Torneio
           *d.torneio = torneio_pertencente.nome
           *ep.data <= torneio_pertencente.dataFim
           *ep.data >= torneio_pertencente.dataInicio
```
```sh
indicaMensagem(mensagem)
Pr??-condi????o:
    *existe jogador_autenticado:Jogador corrente
    *existe d:Desafio
P??s-condi????o:
    *d.mensagem = mensagem
```

### 5. Class Diagram

Having completed the contracts it was now necessary to have a class diagram, that would list all the methods the objects created in the Domain Model would require, besides new objects, the handlers, whose existence was now necessary to, as the name says, handle each of the operations/use-cases included in the system.

<p align="center">
  <img src="https://github.com/JoaoAAnjos/OOChess/blob/main/rsc/diagramaClasses.png">
</p>

### 6. Interaction Diagrams

Finally, as the last step before the beginning of the implementation of the app in Java, Interaction Diagrams were drawn up to have an even bigger detail of the system's inner workings, more specifically of each method belonging to the use case. This step was crucial in understanding and applying GRASP Patterns and principles.

<p align="center">
  <img src="https://github.com/JoaoAAnjos/OOChess/blob/main/rsc/diagrams-ID%231.png">
</p>
<p align="center">
  <img src="https://github.com/JoaoAAnjos/OOChess/blob/main/rsc/diagrams-ID%232.png">
</p>
<p align="center">
  <img src="https://github.com/JoaoAAnjos/OOChess/blob/main/rsc/diagrams-ID%233.png">
</p>
<p align="center">
  <img src="https://github.com/JoaoAAnjos/OOChess/blob/main/rsc/diagrams-ID%234.png">
</p>
<p align="center">
  <img src="https://github.com/JoaoAAnjos/OOChess/blob/main/rsc/diagrams-ID%235.png">
</p>

## Implementation

A few comments regarding the project's implementation: Inside the source folder, you will find three packages, alongside with a properties file. Two of these packages (com/chavetasfechadas and net/padroesfactory) as well as the properties file "format" were given beforehand to the students; the two packages represent two different mock APIs for java discord bots, and it is decided in the properties file which of the two will be used by the program, as well as which algorithm will be used to calculate the player's elo, for which two choices also exist.




