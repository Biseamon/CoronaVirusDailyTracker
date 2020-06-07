package com.example.coronavirusglobaldailytracker.services;

import com.example.coronavirusglobaldailytracker.models.DataCounts;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusDataService {

    private static String VIRUS_DATA = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    List<DataCounts> dataCounts = new ArrayList <>();

    public List <DataCounts> getDataCounts() {
        return dataCounts;
    }

    @PostConstruct
    @Scheduled(cron = "* * * 1 * *")
    public void fetchCoronaData() throws IOException, InterruptedException {

        List<DataCounts> newDataCounts = new ArrayList <>();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA)).build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
       // System.out.println(httpResponse.body());

        StringReader stringReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(stringReader);
        for (CSVRecord record : records) {
            DataCounts dataCounts = new DataCounts();
            dataCounts.setState(record.get("Province/State"));
            dataCounts.setCountry(record.get("Country/Region"));
            int lastReportedCases = Integer.parseInt(record.get(record.size() - 1));
            int previousRepCases = Integer.parseInt(record.get(record.size() - 2));

            dataCounts.setTotalCases(lastReportedCases);
            dataCounts.setDiffFromPrevDay(lastReportedCases - previousRepCases);
            System.out.println(dataCounts);
            newDataCounts.add(dataCounts);

        }
        this.dataCounts = newDataCounts;
    }

}
