# Distributed Parts

Projeto da disciplina de Sistemas Distribuidos (EACH-USP).  Sistema de informações sobre peças ou componentes (parts) usando Remote Method Invocation (RMI) e Java.

## Estrutura

- `src`: pasta com arquivos .java
- `bin`: pasta com arquivos .class
- `lib`: pasta para as dependências

## Arquivos
- `Part`:
- `PartImpl`:
- `PartRepository`:
- `PartRepositoryImpl`:
- `SubPart`:
- `Server`:
- `Client`: 


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