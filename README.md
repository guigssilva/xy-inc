# Geração de Modelo Rest
Facilitador para criação de rest de modelos simples.

## Arquitetura
 * Java 8
 * MongoDB
 * SpringBoot

## Instalação
1. Instale o MongoDb, caso necessita especificar configurações diferentes, alterar arquivo [application.properties](https://github.com/guigssilva/xy-inc/blob/master/gerar-modelo/src/main/resources/application.properties)
2. Realize o clone deste projeto.
3. Executa a seguinte instrução mvn clean install
4. Rode java -jar gerar-modelo-0.0.1-SNAPSHOT.jar
5. Acesse a api na porta localhost:8081/api
