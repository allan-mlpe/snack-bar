# Snack Bar
![Snack Bar CI/CD](https://github.com/allan-mlpe/snack-bar/workflows/Snack%20Bar%20CI/CD/badge.svg)

### Execução da aplicação

#### 1. Instalação das dependências
    
- [Docker](https://docs.docker.com/get-docker/).
- [Docker Compose](https://docs.docker.com/compose/install/).

#### 2. Iniciar os containers

    docker-compose up

* Podemos também utilizar `docker-compose up -d` para rodar em background.

#### 3. Acessar a aplicação

Por padrão, a aplicação está configurada para rodar na porta 80, então você pode acessá-la em: http://localhost.

### Questões de design

#### 1. Tecnologias

- Backend (Java): requisito do problema. Como suporte, foram utilizados alguns frameworks e tecnologias populares do ecossistema da linguagem como o [Spring Boot](https://spring.io/projects/spring-boot) e o [Maven](). O primeiro foi utilizado para abstrair questões relacionadas a API Rest, injeção de dependências e o acesso aos dados. O Maven foi utilizado para gestão de dependências e construção do projeto. Uma outra dependência utilizada foi o [Flyway](https://flywaydb.org/) para versionamento e gerenciamento da evolução do modelo de dados da aplicação por meio de migrações.

- Frontend (Angular): sugestão do problema. Além do Angular, foi utilizado o [Bootstrap](https://ng-bootstrap.github.io/#/home) para tornar a interface aplicação mais amigável ao usuário e um pouco mais interativa.

- Banco de dados (MySQL): embora não fosse um requisito do problema, foi utilizado um banco de dados relacional que pareceu mais adequado para construção de uma solução mais consistente do que seria um NoSQL. A opção pelo MySQL foi totalmente pessoal, justificada apenas por maior familiaridade.

- Questões relacionadas ao ambiente de execução e infraestrutura serão abordadas [aqui](#ambiente).

#### Regras de negócio

- Como o valor dos lanches pode ser afetado por altas/baixas nos preços dos ingredientes, o atributo preço não pertence ao lanche (entidade  `Snack.java`). Para calcular o valor do produto, nós realizamos a soma de todos os seus ingredientes, garantindo que o valor não fique "defasado" frente às variações do mercado. 

- Cada item do lanche (entidade `SnackItem.java`) contém uma referência para o ingrediente (entidade `Ingredient.java`) e sua quantidade. Esse mapeamento também é importante para que a lista de itens não cresça exponencialmente, melhorando a performance de busca e cálculo de preço utilizando essa estrutura.

- Há ainda a entidade `Order.java` que abstrai o pedido de um lanche. Ela contém referências para um lanche, bem como o valor total do mesmo e o valor de desconto (se aplicável). Por questões de simplificação do problema, dado o deadline, optou-se por definir que um pedido só pode conter um lanche. Porém, obviamente um pedido pode ter n itens no "mundo real".

- Acerca dos descontos, foi criada uma interface (`IDiscountCalculator.java`) que define um contrato para que as promoções possam definir seus critérios de elegibilidade e calcular o valor final do desconto.

- Para o cálculo do desconto, foi criado o `DiscountCalculatorManager.java` que contém um `Set` com promoções registradas. Quando o cliente finaliza o pedido, essa classe é responsável por fazer o cálculo total do desconto, uma vez que os descontos são cumulativos.

- As regras de alteração de preço dos lanches do cardápio fixo da lanchonete, regras de promoções existentes e o valor dos lanches customizados pelo cliente foram cobertos por testes unitários, conforme solicitado no problema.

- No frontend foi criado um módulo a parte para lidar com os pedidos, além do módulo "global" da aplicação. Nesse módulo isolado, há componentes para fazer um pedido do cardápio, um pedido customizado e outro para mostrar o detalhe do pedido finalizado.

- Como citado anteriormente, para fins de simplificação do problema, o cliente só pode pedir um lanche por vez.

- As promoções existentes foram criadas diretamente como um componente, sem qualquer consulta ao backend para dispensar a criação de novas entidades, serviços, repositórios, etc. Mais uma decisão com fins de tornar o problema mais simples.

### CI/CD

- Para executar os testes automáticos da aplicação foi configurado um `runner` do [Github](https://github.com/actions/runner). A configuração desse estágio leva em consideração os commits feitos na branch `master`, de modo que a cada novo commit os testes são executados.

- De maneira análoga, os commits na `master` também disparam testes automáticos no [Docker Hub](https://hub.docker.com/), entretanto, de maneira levemente diferente. Lá estamos considerando apenas as modificações feitas no subdiretório `backend/`. Se todos os testes forem executados com sucesso, é feito o upload de uma nova versão da imagem `allanmlpe/snackbar-api`.

- Para a imagem `allanmlpe/snackbar-server` não foi possível utilizar os builds automáticos no Docker Hub. O Dockerfile dessa imagem utiliza [multi-stage](https://docs.docker.com/develop/develop-images/multistage-build/), que ainda não é suportado pelo Docker Hub. A solução encontrada foi criar um novo estágio no runner do Github, que tem a responsabilidade de realizar o build dos estágios `builder` e `deploy` e fazer o upload da imagem resultante para o Docker Hub. 

### Ambiente

- O ambiente de execução da aplicação é composto por 3 containers Docker:

    - `db`: banco de dados MySQL 5.7 para persistência dos dados do sistema.

    - `api`: servidor rodando a API que contém as regras de negócio da aplicação, intermedia as requisições do cliente e o acesso ao banco de dados.

    - `web`: servidor web Nginx que, além de prover os arquivos estáticos da aplicação construída em Angular, serve como um proxy reverso para o servidor da API nas rotas que tem um padrão com `http://<HOST_NAME>/api`.

- As imagens base `allanmlpe/snackbar-api` e `allanmlpe/snackbar-server` usadas pelos containers `api` e `web`, respetivamente, tem objetivo de otimizar o tempo do build inicial da aplicação. Deste modo não é preciso esperar o build do Maven e do Angular. 

- Há ainda, na raiz do projeto, um diretório chamado `docker-config` que contém arquivos utilizados na criação dos containers, como as variáveis de ambiente e a configuração do nginx.