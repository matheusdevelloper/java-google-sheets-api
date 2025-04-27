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
