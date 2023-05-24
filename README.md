# Distributed Parts

Projeto da disciplina de Sistemas Distribuidos (EACH-USP).  Sistema de informações sobre peças ou componentes (parts) usando Remote Method Invocation (RMI) e Java.

## Estrutura

- `src`: pasta com arquivos .java
- `bin`: pasta com arquivos .class

## Arquivos
- `Part`:
  Interface chamada "Part" que estende a interface "Remote" da biblioteca Java RMI. Ela define métodos remotos que podem ser invocados em objetos distribuídos. A interface possui métodos para obter o código, nome e descrição de uma parte, bem como uma lista de seus subcomponentes. Essa interface permite a comunicação remota entre objetos que implementam a interface "Part" e os clientes que desejam acessar esses objetos remotamente.
- `PartImpl`:
  Implementa os métodos definidos na interface "Part" e retorna os valores dos campos correspondentes quando esses métodos são invocados.
- `PartRepository`:
  Interface que estende a interface "Remote" da biblioteca Java RMI. Ela define métodos remotos que podem ser invocados para manipular um repositório de partes. A interface possui métodos para adicionar uma parte, obter uma parte com base em um código e obter todas as partes armazenadas no repositório. Essa interface permite a comunicação remota para gerenciar um repositório de partes através do Java RMI.
- `PartRepositoryImpl`:
  Implementa os métodos definidos na interface "PartRepository" e retorna os valores dos campos correspondentes quando esses métodos são invocados.
- `UserInterface`:
  Responsável por interagir com o usuário, exibir mensagens no console e obter comandos de entrada do usuário. Ela fornece métodos simples para exibir mensagens, ler comandos e lidar com erros de exceção.
- `Server`:
  Representa um servidor que cria e disponibiliza um repositório de partes remotamente através do Java RMI.
- `Client`:
  Representa um cliente que se conecta a um repositório de partes remoto e executa operações como adicionar partes, recuperar partes específicas e obter todas as partes do repositório.


#
## Compilar
- dentro da pasta `distributed-partes`, execute o comando:
  ~~~ 
  $ javac -d bin src/*.java
  ~~~
## Executar
- dentro da pasta `distributed-partes`, execute os seguintes comandos:
  ~~~ 
  $ cd bin; rmiregistry
  ~~~
  - Abra outro terminal. Troque <server_name> pelo nome do servidor e <port> pelo número da porta que irá se conectar:
  ~~~
  $ java -cp bin Server <server_name> <port>
  ~~~
  - Abra outro terminal. Troque <server_name> pelo nome do server que deseja se conectar:
  ~~~
  $ java -cp bin Client <server_name>
  ~~~