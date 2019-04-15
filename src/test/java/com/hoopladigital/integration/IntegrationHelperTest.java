package com.hoopladigital.integration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

class IntegrationHelperTest {

	final String over50name = "123456789012345678901234567890123456789012345678901";

	HttpURLConnection makeRequest(final String endpoint, final String httpMethod) throws IOException {
		return connect(endpoint, httpMethod, (connection -> {}));
	}

	HttpURLConnection makeRequest(final String endpoint, final String httpMethod, final String json) throws IOException {
		return connect(endpoint, httpMethod, connection -> {
			connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			connection.setRequestProperty("Accept", "application/json");
			connection.setDoOutput(true);
			try(final OutputStream os = connection.getOutputStream()) {
				final byte[] input = json.getBytes(StandardCharsets.UTF_8);
				os.write(input, 0, input.length);
			}
			catch (IOException ioException) {}
		});
	}

	private HttpURLConnection connect(final String endpoint, final String httpMethod, final Consumer<HttpURLConnection> additionalOperations) throws IOException {
		// TODO: Set up an actual test server versus relying on a local deployment
		final URL url = new URL("http://localhost:8080/dillon/" + endpoint);
		final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod(httpMethod);
		additionalOperations.accept(connection);
		return connection;
	}

	String readResponseJson(final HttpURLConnection connection) {
		try(final BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
			final StringBuilder response = new StringBuilder();
			String responseLine;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			return response.toString();
		}
		catch (IOException ioException) {
			return null;
		}
	}

}
