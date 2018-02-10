package com.example.HelloAppEngine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

@SpringBootApplication
@RestController
public class HelloAppEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloAppEngineApplication.class, args);
	}

    @RequestMapping("/")
    public String hello() {
        return "Hello Spring Boot!";
    }

	@RequestMapping("/get")
	public String get(@RequestParam("name") String value) {
		System.out.println(">>>>>>>>>>> @RequestParam = " + value);

		return value;
	}

	private static String webSource = "https://finance.yahoo.com/quote/";

	@RequestMapping(value = "/quote")
	public PriceQuote getQuote(@RequestParam(value="ticker") String ticker) {
		Document doc;
		try {
			doc = Jsoup.connect(webSource+ticker).get();
			Elements dataList = doc.select("#Lead-2-QuoteHeader-Proxy");
			float price=0;

			// We are looking for:
			// <span class="Trsdu(0.3s) Fw(b) Fz(36px) Mb(-4px) D(ib)" data-reactid="35">
			Elements dataRactID = dataList.select("[data-reactid='35']");

			System.out.println("dataReact Elements size = " + dataRactID.size());
			for (Element e :
					dataRactID) {
				System.out.println(e.toString());
				System.out.println("Data: " + e.data());
				System.out.println("Attributes: " + e.attributes());
				System.out.println("HTML: " + e.html());
				System.out.println("This is what you are looking for ---> " + e.text());
				price = Float.valueOf(e.text().replaceAll(",", ""));
			}
			//price = Float.parseFloat(dataRactID.text());

			return new PriceQuote(ticker, doc.title(), price);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
