package CodeSoft;

import java.util.Scanner;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

class CurrencyConverter {
    private String baseCurrency;
    private String targetCurrency;
    private double exchangeRate;

    public CurrencyConverter(String baseCurrency, String targetCurrency) {
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.exchangeRate = fetchExchangeRate(baseCurrency, targetCurrency);
    }

    private double fetchExchangeRate(String baseCurrency, String targetCurrency) {
        try {
            String apiKey = "YOUR_API_KEY";
            String apiUrl = "https://v6.exchangeratesapi.io/latest?base=" + baseCurrency + "&symbols=" + targetCurrency;

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                Scanner scanner = new Scanner(url.openStream());
                StringBuilder response = new StringBuilder();

                while (scanner.hasNext()) {
                    response.append(scanner.nextLine());
                }

                scanner.close();

                JSONObject jsonResponse = new JSONObject(response.toString());
                return jsonResponse.getJSONObject("rates").getDouble(targetCurrency);
            } else {
                System.out.println("Failed to fetch exchange rate. Please check your API key and try again.");
                System.exit(1);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return 0.0;
    }

    public double convert(double amount) {
        return amount * exchangeRate;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }
}

public class CurrencyConvertorProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the base currency code (e.g., USD): ");
        String baseCurrency = scanner.next().toUpperCase();

        System.out.print("Enter the target currency code (e.g., EUR): ");
        String targetCurrency = scanner.next().toUpperCase();

        CurrencyConverter converter = new CurrencyConverter(baseCurrency, targetCurrency);

        System.out.print("Enter the amount to convert: ");
        double amount = scanner.nextDouble();

        double convertedAmount = converter.convert(amount);

        System.out.printf("%.2f %s is equal to %.2f %s\n", amount, baseCurrency, convertedAmount,
                converter.getTargetCurrency());
    }
}
