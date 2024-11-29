# Documentação
### Introdução

### Projeto com provisionamento de infraestrutura para utilização de recursos AWS

Em um cenário que é preciso provisionar um banco de dados (RDS), rabbitMQ (EKS) e ambiente de backend (EKS)

```
https://github.com/ThiagoLuizS/terraform-ntconsult
```

### Regras de Negócio

Para estrutura de pastas do projeto está sendo utilizado o MVC. Separados com os seguintes nomes

<ol>
    <li>Resource(interfaces)</li>
    <li>Controller</li>
    <li>Service</li>
    <li>Repository</li>
    <li>Annotation</li>
    <li>Exception</li>
    <li>Configuration</li>
    <li>Route</li>
</ol>

- <b>Resource</b>

    Interfaces com os métodos definidos com anotações spring.web (métodos HTTP). As interfaces nessa pasta são implementadas nas classes de controler.

- <b>Controller</b>

    Classes que implementam a resource (interface) e tem como anotação em sua assinatura definir seu objetivo de criação de métodos de entrada e saída de dados. (Endpoints).

- <b>Service</b>

    Classes de serviço que contemplam todas as regras de negócio do projeto. Essas classes injetam repositórios, mapeamentos e validações. 

- <b>Repository</b>

    Interfaces que extendem a JPA e tem como objetivo construir uma ponte para camadas de persitências de dados utilizando a lib do Hibernate ou SpringData

- <b>Annotation</b>

    Interfaces de anotação criadas para auxiliar na validação de métodos e atributos. Como validações e injeções de Beans.

- <b>Exception</b>

    Classes que agregam todas as exceções não tratadas por meio de um interceptor advance e convertida em um retorno padrão. Por meio dela é possivel controlar o tipo de exceção, mensagem e corpo do response nas requisições HTTP.

- <b>Configuration</b>

    Classes com objetivo de injetar uma biblioteca no scopo de inicialização de uma aplicação, parametrizar o funcionamento destas libs, configurar partes de segurança e replicar funcionalidades para diversos componentes.

- <b>Route</b>

    Classe responsável por configurar o Apache Camel e mapear a execução dos metodos. Como se trata de uma classe de configuracao/regra fica indefinido se a classe dentro desta pasta deve pertencer as classes de configuração. Então em alguns projetos fica por critério deixar claro sua definição e escopo.

#### Apache Camel

O batch está configurado para ser executado a cada 60 segundos conforme definido o cron no application.yml:

```
camel:
  component:
    quartz2:
      cron:
        v1: cron:tab?schedule=0/60+*+*+*+*+?
```
O batch a cada 60 segundos tem como objetivo chamar o método para buscar as pautas, cuja a sessão já expirou. E esse método ao buscar no banco de dados as pautas que estão expiradas irá postar a pauta em uma fila de mensageria (RabbitMQ).

#### RabbitMQ

O rabbit tem como objetivo consumir as pautas que foram postadas na fila pelo Apache Camel, contabilizar os votos desta, postar um LOG no console e em seguida atualizar uma coluna no banco de dados (true/false) para dizer que esta pauta já foi encerrada e calculada. Esta pauta que sofreu a modificação não entrará mais na fila.

#### Cacheable

A implementação do cache no método de salvar o voto, é uma boa pratica para reduzir o consumo e processamento de memória tanto da aplicação quanto do banco de dados. Em cenários que ocorrem duplos clickes ou tentativas de salvar ou obter dados repetidos. No entanto o cacheamento não substitui totalmente as regras de validação, sendo essas necessárias quando o cache expirar ou por algum momento não funcionar.

#### CompletableFuture

No método de salvar votos está sendo utilizado o CompletableFuture que se extende até a classe de controller. A idéia é que o retorno do método seja assincrono (Future). Pois sua função é paralelisar a execução em Threads. Sua funcionalidade pode ser mais aproveitada quando em um cenário existe várias etapas de chamadas demoradas que podem ser assincronas. Então nesses cenários pode utilizar multiplos CompletableFuture e no final utilizar o Join para unir todos os resultados em um retorno apenas.

#### Scheduled

De forma mais simplória, foi utilizado o Scheduler via anotação na classe CacheConfig. O objetivo é demonstrar como podemos chamar um método para que outras anotações injetadas/definidas na sua assinatura possam ser executadas por baixo. Nesse cenário utilizei o Schedule que será executado de tempos em tempos para que a anotação CacheEvict faça a limpeza dos históricos do cache.

#### Entidades

Estudando conforme o desafio. Eu vi uma melhoria significativa para diminuir a verbosidade e as implementações de código ao criar apenas as entiades <b>Pauta e Voto</b>. Com um relacionamento e definição das colunas de forma simples foi possivel atender o objetivo.

### NOTAS

<i>O histórico de evolução da criação do projeto pode ser visto nos commits. Comentei de forma clara e objetiva o que foi feito por etapas. </i>