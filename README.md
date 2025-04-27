


🧾 Java Google Sheets API

Este projeto em Java permite registrar despesas em uma planilha do Google Sheets por meio da API oficial.
Ideal para quem busca automatizar o controle financeiro pessoal ou empresarial.

🚀 Funcionalidades

    Autenticação via OAuth 2.0 utilizando credenciais do Google Cloud.

    Leitura e escrita de dados em planilhas do Google Sheets.

    Registro de despesas com data, descrição, valor e categoria.

    Estrutura modular e de fácil manutenção.

⚙️ Tecnologias Utilizadas

    Java 11 ou superior

    Google Sheets API v4

    Maven para gerenciamento de dependências

    Biblioteca Google API Client

📥 Pré-requisitos

    Conta no Google Cloud Platform

    Credenciais OAuth 2.0 com acesso à API do Google Sheets

    Java 11 ou superior instalado

    Maven instalado​

📦 Instalação

    1-Clone este repositório:
      git clone https://github.com/matheusdevelloper/java-google-sheets-api.git
    2-Navegue até o diretório do projeto:
      cd java-google-sheets-api
    3-Instale as dependências com o Maven: 
      mvn install
    4-Crie um arquivo credentials.json com suas credenciais do Google Cloud e coloque-o na raiz do projeto.

🛠️ Uso

   Para registrar uma despesa, execute o seguinte código:

   package com.despesas.api.service;

import com.despesas.api.model.Despesa;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;


@Service
public class PlanilhaService {
    private static final String SPREADSHEET_ID = "";
    private static final String RANGE = "A2"; // Começa da linha 2

    public void registrarDespesa(Despesa despesa) {
        try {
            FileInputStream serviceAccountStream = new FileInputStream("");

            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccountStream)
                    .createScoped(Collections.singleton(""));

            Sheets sheetsService = new Sheets.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    GsonFactory.getDefaultInstance(),
                    new HttpCredentialsAdapter(credentials)
            ).setApplicationName("Despesas API").build();

            ValueRange appendBody = new ValueRange()
                        .setValues(Arrays.asList(
                                Arrays.asList(
                                        despesa.getData(),
                                        despesa.getCategoria(),
                                        despesa.getDescricao(),
                                        despesa.getValor()
                                )
                        ));

            sheetsService.spreadsheets().values()
                    .append(SPREADSHEET_ID, RANGE, appendBody)
                    .setValueInputOption("USER_ENTERED")
                    .execute();

        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

}

Descriçaõ como ficionar o codigo
Claro! Abaixo está uma descrição detalhada do código fornecido, explicando cada parte e sua funcionalidade:

---

## 🧾 Descrição do Código: Serviço de Registro de Despesas

O código implementa um serviço em Java utilizando o Spring Framework para registrar despesas em uma planilha do Google Sheets por meio da API oficial. A classe `PlanilhaService` é responsável por autenticar a aplicação, estabelecer a conexão com a API do Google Sheets e inserir os dados da despesa na planilha especificada.

### 📦 Dependências Importadas

- **Pacotes do Google API Client**: Fornecem classes para autenticação, transporte HTTP e manipulação de JSON.
- **Pacotes do Google Sheets API**: Permitem interagir com a API do Google Sheets para leitura e escrita de dados.
- **Pacote do Spring Framework**: Facilita a criação de serviços e a injeção de dependências.

### 🛠️ Componentes da Classe `PlanilhaService`

#### 1. **Constantes de Configuração**

- `SPREADSHEET_ID`: Identificador único da planilha do Google Sheets onde os dados serão registrados.
- `RANGE`: Intervalo da célula onde os dados serão inseridos. Neste caso, a partir da célula "A2". ([Method: spreadsheets.values.append | Google Sheets](https://developers.google.com/sheets/api/reference/rest/v4/spreadsheets.values/append?utm_source=chatgpt.com))

#### 2. **Método `registrarDespesa(Despesa despesa)`**

Este método é responsável por registrar uma nova despesa na planilha:

- **Autenticação**: Carrega as credenciais da conta de serviço a partir de um arquivo JSON e cria um objeto `GoogleCredentials`.
- **Criação do Serviço Sheets**: Utiliza as credenciais para construir um cliente da API do Google Sheets.
- **Preparação dos Dados**: Cria um objeto `ValueRange` contendo os dados da despesa a serem inseridos na planilha.
- **Inserção dos Dados**: Utiliza o método `append` da API para adicionar os dados na planilha, começando na célula especificada em `RANGE`.

#### 3. **Tratamento de Exceções**

- O método captura exceções do tipo `IOException` e `GeneralSecurityException`, que podem ocorrer durante a leitura do arquivo de credenciais ou na comunicação com a API.
- Em caso de erro, a exceção é impressa no console para diagnóstico.

### 🔐 Autenticação com Conta de Serviço

A autenticação é realizada utilizando uma conta de serviço do Google Cloud:

- **Arquivo de Credenciais**: O arquivo JSON contendo as credenciais da conta de serviço deve ser fornecido.
- **Escopos de Acesso**: As credenciais são configuradas com o escopo necessário para acessar e modificar planilhas do Google Sheets.

### 📋 Estrutura dos Dados da Despesa

A classe `Despesa` deve conter os seguintes atributos:

- `data`: Data da despesa.
- `categoria`: Categoria da despesa (ex: Alimentação, Transporte).
- `descricao`: Descrição detalhada da despesa.
- `valor`: Valor monetário da despesa.

Esses dados são passados para o método `registrarDespesa`, que os insere na planilha.

### ✅ Conclusão

Este serviço permite a integração entre uma aplicação Java e o Google Sheets, facilitando o registro automatizado de despesas em uma planilha. É uma solução prática para controle financeiro, especialmente em sistemas que já utilizam o Spring Framework.



   
