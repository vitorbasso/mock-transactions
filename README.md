# mock-transactions

Bem vindo a esse projeto! Uma api com o objetivo de fornecer transações mockadas aleatórias dependentes do id do usuário, mês e ano da transação.

## Requisitos
Para esse projeto apenas o **Java 11** é necessário de ferramentas externas ao repositório.

* [Java 11](https://adoptopenjdk.net/) baiaxado e no path.

## Como rodar
Para rodar esse projeto basta utilizar o Gradle incluso rodando o comando correspondente ao seu sistema operacional em um terminal:

* Windows: `.\gradlew bootRun` se estiver usando o powershell ou apenas `gradlew bootRun` se estiver usando o prompt de comando;

* Linux/Mac: `./gradlew bootRun`.

Pronto, já pode começar a usar a api!

#### Docker
**IMPORTANTE: É necessário ter o docker instalado e rodando para usar esse método.**
Caso prefira usar o docker para rodar a aplicação, basta buildar uma imagem do projeto com o seguinte comando:
* Windows: `.\gradlew bootBuildImage` no powershell ou `gradlew bootBuildImage`no prompt de comando;
* Linux/Mac: `./gradlew bootBuildImage`.

Depois de criada a imagem é só subir um container com o comando

`docker run -p 8080:8080 mock-transactions:0.0.1-SNAPSHOT`

Pronto, já pode começar a usar a api!

## Usando a aplicação
Para usar a aplicação basta bater no endpoint `localhost:8080/{id}/transacoes/{ano}/{mes}` substituindo os valores em `{}` por inteiros correspondentes. Você pode utilizar um programa específico, como [Postman](https://www.postman.com/downloads/), ou simplesmente seu browser. Caso decida usar seu browser, recomendo o uso de uma extensão para melhorar a visualização de Json.

* O `id` deve ser entre 1.000 e 100.000;
* O `ano` deve ser entre 1900 e 2030 (Valores que "faziam sentido" como limite inferior e superior para uma transação);
* O `mes` deve ser entre 1 e 12.

A resposta virá no formato:

```json
[
  {
     "descricao": "string(10, 60)"
     "data": "long(timestamp)"
     "valor": "integer(-9.999.999, 9.999.999)"
  }  
]
```

Com mensages de erro e status HTTP correspondente caso os valores usado na requisição não se conformem com o esperado.