package com.hoopladigital.integration;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.junit.Test;

import static org.junit.Assert.*;

public class PersonEndpointIntegrationTest extends IntegrationHelperTest {

	// TODO: Improve tests once a test server is used (see TODO in IntegrationHelperTest)

	private String createPersonJson(final String firstName, final String middleName, final String lastName) {
		return createPersonJson(firstName, middleName, lastName, null);
	}

	private String createPersonJson(final String firstName, final String middleName, final String lastName, final Long id) {
		final StringBuilder builder = new StringBuilder("{");
		if (id != null) {
			builder.append("\"id\":").append(id).append(",");
		}
		return builder
			.append("\"firstName\":\"").append(firstName).append("\",")
			.append("\"middleName\":").append(middleName == null ? "null" : ("\"" + middleName + "\"")).append(",")
			.append("\"lastName\":\"").append(lastName).append("\"")
			.append("}")
			.toString();
	}

	@Test
	public void should_get_person_list_endpoint() throws IOException {
		final HttpURLConnection connection = makeRequest("people", "GET");
		final String returnedJson = readResponseJson(connection);

		assertEquals(200, connection.getResponseCode());
		// TODO: Test precise response once rollbacks working
		assertTrue(returnedJson.contains(createPersonJson("Thomas", null, "Jefferson", 3L)));
		assertTrue(returnedJson.contains(createPersonJson("John", "Quincy", "Adams", 6L)));
	}

	@Test
	public void should_get_person_by_id_endpoint() throws IOException {
		final Long id = 6L;
		final HttpURLConnection connection = makeRequest("people/" + id, "GET");
		final String returnedJson = readResponseJson(connection);
		final String expectedReturn = createPersonJson("John", "Quincy", "Adams", id);

		assertEquals(200, connection.getResponseCode());
		assertEquals(expectedReturn, returnedJson);
	}

	@Test
	public void should_get_person_by_id_invalid_id_endpoint() throws IOException {
		final Long id = 1_000L;
		final HttpURLConnection connection = makeRequest("people/" + id, "GET");
		final String returnedJson = readResponseJson(connection);

		assertEquals(404, connection.getResponseCode());
		assertNull(returnedJson);
	}

	@Test
	public void should_get_pet_list_by_person_id_endpoint() throws IOException {
		final Long personId = 3L;
		final HttpURLConnection connection = makeRequest("people/" + personId + "/pets", "GET");
		final String returnedJson = readResponseJson(connection);
		final String expectedJson = "[{\"id\":23,\"personId\":" + personId + ",\"name\":\"Dick\"}]";

		assertEquals(200, connection.getResponseCode());
		assertEquals(expectedJson, returnedJson);
	}

	@Test
	public void should_get_pet_list_by_person_id_invalid_id_endpoint() throws IOException {
		final Long personId = 1_000L;
		final HttpURLConnection connection = makeRequest("people/" + personId + "/pets", "GET");
		final String returnedJson = readResponseJson(connection);

		assertEquals(404, connection.getResponseCode());
		assertNull(returnedJson);
	}

	@Test
	public void should_create_person_endpoint() throws IOException {
		final String json = createPersonJson("John", "Jacob", "Smith");
		final HttpURLConnection connection = makeRequest("people", "POST", json);
		final String returnedJson = readResponseJson(connection);
		// TODO: Uncomment the following once rollbacks are working (and can delete startsWith, endsWith)
		// TODO: final String expectedJson = createPersonJson("John", "Jacob", "Smith", 11L);
		final String expectedJsonStart = "{\"id\":";
		final String expectedJsonEnd = ",\"firstName\":\"John\",\"middleName\":\"Jacob\",\"lastName\":\"Smith\"}";

		assertEquals(200, connection.getResponseCode());
		// TODO: Uncomment the following once rollbacks are working (and can delete startsWith, endsWith)
		// TODO: assertEquals(expectedJson, returnedJson);
		assertTrue(returnedJson.startsWith(expectedJsonStart));
		assertTrue(returnedJson.endsWith(expectedJsonEnd));
	}

	@Test
	public void should_create_person_large_first_name_endpoint() throws IOException {
		final String json = createPersonJson(over50name, "Jacob", "Smith");
		final HttpURLConnection connection = makeRequest("people", "POST", json);
		final String returnedJson = readResponseJson(connection);

		assertEquals(400, connection.getResponseCode());
		assertNull(returnedJson);
	}

	@Test
	public void should_create_person_large_middle_name_endpoint() throws IOException {
		final String json = createPersonJson("John", over50name, "Smith");
		final HttpURLConnection connection = makeRequest("people", "POST", json);
		final String returnedJson = readResponseJson(connection);

		assertEquals(400, connection.getResponseCode());
		assertNull(returnedJson);
	}

	@Test
	public void should_create_person_large_last_name_endpoint() throws IOException {
		final String json = createPersonJson("John", "Jacob", over50name);
		final HttpURLConnection connection = makeRequest("people", "POST", json);
		final String returnedJson = readResponseJson(connection);

		assertEquals(400, connection.getResponseCode());
		assertNull(returnedJson);
	}

	@Test
	public void should_update_person_endpoint() throws IOException {
		final Long id = 1L;
		final String firstName = "John";
		final String middleName = "Jacob";
		final String lastName = "Smith";
		final String json = createPersonJson(firstName, middleName, lastName);

		final HttpURLConnection connection = makeRequest("people/" + id, "PUT", json);
		final String returnedJson = readResponseJson(connection);
		final String expectedJson = createPersonJson(firstName, middleName, lastName, id);

		assertEquals(200, connection.getResponseCode());
		assertEquals(expectedJson, returnedJson);
	}

	@Test
	public void should_update_person_invalid_id_endpoint() throws IOException {
		final Long id = 1_000L;
		final String firstName = "John";
		final String middleName = "Jacob";
		final String lastName = "Smith";
		final String json = createPersonJson(firstName, middleName, lastName);

		final HttpURLConnection connection = makeRequest("people/" + id, "PUT", json);
		final String returnedJson = readResponseJson(connection);

		assertEquals(404, connection.getResponseCode());
		assertNull(returnedJson);
	}

	@Test
	public void should_update_person_large_first_name_endpoint() throws IOException {
		final Long id = 1L;
		final String json = createPersonJson(over50name, "Jacob", "Smith");

		final HttpURLConnection connection = makeRequest("people/" + id, "PUT", json);
		final String returnedJson = readResponseJson(connection);

		assertEquals(404, connection.getResponseCode()); // TODO: Improve - make 400 if invalid but 404 if id invalid
		assertNull(returnedJson);
	}

	@Test
	public void should_update_person_large_middle_name_endpoint() throws IOException {
		final Long id = 1L;
		final String json = createPersonJson("John", over50name, "Smith");

		final HttpURLConnection connection = makeRequest("people/" + id, "PUT", json);
		final String returnedJson = readResponseJson(connection);

		assertEquals(404, connection.getResponseCode()); // TODO: Improve - make 400 if invalid but 404 if id invalid
		assertNull(returnedJson);
	}

	@Test
	public void should_update_person_large_last_name_endpoint() throws IOException {
		final Long id = 1L;
		final String json = createPersonJson("John", "Jacob", over50name);

		final HttpURLConnection connection = makeRequest("people/" + id, "PUT", json);
		final String returnedJson = readResponseJson(connection);

		assertEquals(404, connection.getResponseCode()); // TODO: Improve - make 400 if invalid but 404 if id invalid
		assertNull(returnedJson);
	}

	// TODO: Uncomment once rollback is enabled (only works once as it deletes the entity)
//	@Test
//	public void should_delete_person_endpoint() throws IOException {
//		final Long id = 10L;
//		final HttpURLConnection connection = makeRequest("people/" + id, "DELETE");
//
//		assertEquals(204, connection.getResponseCode());
//	}

	@Test
	public void should_delete_person_invalid_id_endpoint() throws IOException {
		final Long id = 1_000L;
		final HttpURLConnection connection = makeRequest("people/" + id, "DELETE");

		assertEquals(404, connection.getResponseCode());
	}

}
