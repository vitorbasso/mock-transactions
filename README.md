# mock-transactions

Bem vindo a esse projeto! Uma api com o objetivo de fornecer transações mockadas aleatórias dependentes do id do usuário, mês e ano da transação.

## Requisitos
Para esse projeto apenas o **Java 11** é necessário de ferramentas externas ao repositório.

* [Java 11](https://adoptopenjdk.net/) baiaxado e no path;

## Como rodar
Para rodar esse projeto basta utilizar o Gradle incluso rodando o comando correspondente ao seu sistema operacional em um terminal:

* Windows: `.\gradlew bootRun` se estiver usando o powershell ou apenas `gradlew bootRun` se estiver usando o prompt de comando.

* Linux/Mac: `./gradlew bootRun`.

### Docker
Se preferir usar o docker para rodar a aplicação, basta buildar uma imagem do projeto com o seguinte comando
* Windows: `.\gradlew bootBuildImage` no powershell ou `gradlew bootBuildImage`no prompt de comando.
* Linux/Mac: `./gradlew bootBuildImage`

Depois de criada a imagem é só subir um container com o comando

