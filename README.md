# Distributed Parts

    Projeto da disciplina de Sistemas Distribuidos (EACH-USP)
Sistema de informações sobre componentes (parts) usando Remote Method Invocation (RMI) e Java.
PartRepository é um objeto distribuı́do, que será exposto por um processo servidor e que implementa um repositório de informações sobre peças. Os clientes se conectam a servidores que possuem uma instância de PartRepository. O servidor, por sua vez, implementa 2 tipos de objetos: PartRepository (que é uma interface de acesso) e Parts (vários objetos remotos armazenados no servidor).

## Estrutura

- `src`: pasta com arquivos .java
- `bin`: pasta com arquivos .class

## Classes
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


## Comandos do cliente
O processo do cliente será usado para exercitar o sistema. Ele deve permitir que o usuário estabeleça uma conexão com um servidor e interaja com seu repositório de parts.

- `bind` Faz o cliente se conectar a outro servidor e muda o repositório corrente. Este comando recebe o nome de um repositório e obtém do serviço de nomes uma referência para esse repositório, que passa a ser o repositório corrente.

- `listp` Lista as peças do repositório corrente.
  
- `lists` Lista os servidores disponíveis para conexão.

- `getp` Busca uma peça por código. A busca é efetuada no repositório corrente. Se encontrada, a peça passa a ser a nova peça corrente.
  
- `showp` Mostra atributos da peça corrente.
  
- `showsub` Mostra atributos da lista de subpeças corrente.
  
- `clearlist` Esvazia a lista de sub-peças corrente.
  
- `addsubpart` Adiciona à lista de sub-peças corrente N unidades da peça corrente.
  
- `addp` Adiciona uma peça ao repositório corrente. A lista de sub-peças corrente é usada como lista de subcomponentes diretos da nova peça. (É só para isto que existe a lista de sub-peças corrente).

- `showinfo` Mostra o nome e a quantidade de peças do repositório corrente.
  
- `quit` Encerra a execução do cliente.


#
## Compilar
- Dentro da pasta `distributed-parts`, execute o comando:
  ~~~ 
  $ javac -d bin src/*.java
  ~~~
## Executar
- Dentro da pasta `distributed-parts`, execute os seguintes comandos:
  ~~~ 
  $ cd bin; rmiregistry
  ~~~
  - Abra outro terminal. Troque `<server_name>` pelo novo nome do servidor que será criado e `<port>` pelo número da porta que deseja conectar:
  ~~~
  $ java -cp bin Server <server_name> <port>
  ~~~
  - Abra outro terminal:
  ~~~
  $ java -cp bin Client
  ~~~