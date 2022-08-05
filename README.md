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
Pré-condição:
    * existe jogador_autenticado:Jogador corrente
Pós-condição:
    *Seja t:Torneio tal que
           *existe associação entre t e CatálogoDeTorneios
           *t.nome = nome
           *t.dataFim > dataAtual
             *torneio_pertencente:Torneio = t
    *existe i:Inscrição tal que
           *existe associação "faz" entre i e jogador_autenticado
           *existe associação "em" entre i e torneio_pertencente
```
```sh
indicaEloRange(ΔElo)
Pré-condição:
    *existe jogador_autenticado:Jogador corrente
Pós-condição:
    *Seja j:Jogador[] tal que
           *exista associação entre j[i] e CatálogoDeJogadores
           *j[i].ELO < jogador_autenticado.ELO + ΔElo
           *j[i].ELO > jogador_autenticado.ELO - ΔElo
             *jogadores_elegiveis:Jogador[] = j

```
```sh
indicaJogador(username)
Pré-condição:
   *existe jogadores_elegiveis:Jogador[]
Pós-condição:
    *Seja j:Jogador tal que
           *existe associação entre j e CatálogoDeJogadores
           *j.username = username
           *existe j[i].username = j.username
             *j:Jogador = jogador_desafiado
    *se existir torneio_pertencente:Torneio
           *existe i:Inscrição tal que
                  *existe associação "faz" entre i e jogador_desafiado
                  *existe associação "em" entre i e torneio_pertencente

```
```sh
indicaHoraEData(hora, data)
Pré-condição:
    *existe jogador_desafiado:Jogador
    *existe jogador_autenticado:Jogador corrente
Pós-condição:
    *Seja ld:ListaDeDesafiosPendentes tal que
           *existe associação "tem" entre jogador_desafiado e ld
    *foi criada ep:Época tal que
           *e.hora = hora
           *e.data = data
    *foi criado d:Desafio tal que
           *foi criada associação "indica uma" entre d e ep
           *foi criada associação "faz" entre d e jogador_autenticado
           *foi criada associação "contem" entre d e ld
    *se existir torneio_pertencente:Torneio
           *d.torneio = torneio_pertencente.nome
           *ep.data <= torneio_pertencente.dataFim
           *ep.data >= torneio_pertencente.dataInicio
```
```sh
indicaMensagem(mensagem)
Pré-condição:
    *existe jogador_autenticado:Jogador corrente
    *existe d:Desafio
Pós-condição:
    *d.mensagem = mensagem
```

### 5. Class Diagrams

