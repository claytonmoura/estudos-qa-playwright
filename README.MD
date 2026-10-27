# Estudos de Automação com Playwright

Estou retomando e aprofundando meus conhecimentos em automação de testes com **Playwright**.  
Já utilizei a ferramenta profissionalmente na GFT (2022–2024) e agora estou revisando os fundamentos, organizando melhor os estudos e construindo projetos próprios para retornar à área de QA.

### Foco atual
- Playwright com Java
- Page Object Model
- Testes de UI
- Testes de API
- Boas práticas de automação

---

## Aprendizados

### 1. Configuração do Ambiente
**Status:** Concluído

- Instalação do Maven no Windows
- Criação manual do projeto Maven
- Configuração do `pom.xml` com a dependência do Playwright
- Instalação dos navegadores (Chromium, Firefox e WebKit)
- Execução do primeiro script com sucesso

**Comandos principais:**
- `mvn dependency:resolve`
- `mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"`
- `mvn compile exec:java -D exec.mainClass="com.clayton.qa.PrimeiroTeste"`

### 2. Primeiro Script e Navegação
**Status:** Concluído

- Abertura do navegador com Playwright
- Navegação para uma URL (`page.navigate()`)
- Captura do título da página (`page.title()`)
- Uso do modo headed (navegador visível)
- Aguardo com `page.waitForTimeout()`

### 3. Interações Básicas e Login
**Status:** Concluído

- Preenchimento de campos com `locator().fill()`
- Clique em elementos com `locator().click()`
- Localização de elementos por seletor (ID)
- Realização de login com sucesso no site SauceDemo

**Dados utilizados no teste:**
- Usuário: `standard_user`
- Senha: `secret_sauce`

### 4. Assertions (Validações)
**Status:** Concluído

- Uso de `assertThat()` do Playwright
- Validação de URL após o login
- Validação de texto visível na página
- Validação de elemento visível
- Entendimento da diferença entre executar ações e validar resultados

### 5. Page Object Model (POM)
**Status:** Concluído

- Criação da classe `LoginPage`
- Separação de seletores e ações da página
- Reutilização do Page Object no teste
- Teste de login mais limpo e organizado
- Entendimento da importância do POM para manutenção
- Fluxo completo: Login → Adicionar produto → Validar carrinho

### 6. Organização do Projeto + JUnit
**Status:** Concluído

- Separação das pastas `pages` e `tests`
- Migração dos testes de `main` para JUnit 5
- Uso das anotações `@Test`, `@BeforeAll`, `@AfterAll`, `@BeforeEach`, `@AfterEach`
- Execução dos testes via `mvn test`
- Entendimento do papel do JUnit na automação e futura integração com CI/CD

### 7. Testes Negativos
**Status:** Concluído

- Login com usuário inválido
- Login com senha inválida
- Login com campos vazios
- Validação de mensagens de erro
- Suite com 4 testes passando (1 positivo + 3 negativos)

### 8. Massa de Dados (TestData)
**Status:** Concluído

- Criação da classe `TestData` para centralizar dados de teste
- Remoção de valores hardcoded dos testes
- Uso de constantes para usuários, senhas, produtos e mensagens de erro
- Melhoria na legibilidade e manutenção do código

### 9. Testes de API com Playwright
**Status:** Concluído

- Uso do `APIRequestContext` do Playwright
- Testes de GET, POST, PUT e DELETE
- Validação de status code e corpo da resposta
- 6 testes de API passando com a API ReqRes

### 10. CI/CD com GitHub Actions
**Status:** Concluído

- Configuração de pipeline para rodar testes automaticamente no push
- Execução em modo headless no CI
- 11 testes passando (Web + API) no GitHub Actions

---

## Projetos

- [playwright-java-tests](./projetos/playwright-java-tests) → Projeto de estudos com Playwright + Java