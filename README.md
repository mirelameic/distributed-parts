# Distributed Parts

Projeto da disciplina de Sistemas Distribuidos (EACH-USP).  Sistema de informações sobre peças ou componentes (parts) usando Remote Method Invocation (RMI) e Java.

## Estrutura

- `src`: pasta com arquivos .java
- `bin`: pasta com arquivos .class
- `lib`: pasta para as dependências

## Arquivos
- `Part`:
  Interface chamada "Part" que estende a interface "Remote" da biblioteca Java RMI. Ela define métodos remotos que podem ser invocados em objetos distribuídos. A interface possui métodos para obter o código, nome e descrição de uma parte, bem como uma lista de seus subcomponentes. Essa interface permite a comunicação remota entre objetos que implementam a interface "Part" e os clientes que desejam acessar esses objetos remotamente.
- `PartImpl`:
  Implementa os métodos definidos na interface "Part" e retorna os valores dos campos correspondentes quando esses métodos são invocados.
- `PartRepository`:
  Interface que estende a interface "Remote" da biblioteca Java RMI. Ela define métodos remotos que podem ser invocados para manipular um repositório de partes. A interface possui métodos para adicionar uma parte, obter uma parte com base em um código e obter todas as partes armazenadas no repositório. Essa interface permite a comunicação remota para gerenciar um repositório de partes através do Java RMI.
- `PartRepositoryImpl`:
  Implementa os métodos definidos na interface "PartRepository" e retorna os valores dos campos correspondentes quando esses métodos são invocados.
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
  - Abra outro terminal e troque <repository_name> pelo nome do repositóirio:
  ~~~
  $ java -cp bin Server <repository_name>
  ~~~
  - Abra outro terminal e troque <repository_name> pelo mesmo nome usado acima:
  ~~~
  $ java -cp bin Client <repository_name>
  ~~~