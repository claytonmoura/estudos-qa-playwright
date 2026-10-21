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

### 4. Próximos Aprendizados
- Validações (Assertions)
- Page Object Model
- Melhor organização dos testes
- Testes com usuário inválido
- Testes de API com Playwright

---

## Projetos

- [playwright-java-tests](./projetos/playwright-java-tests) → Projeto de estudos com Playwright + Java